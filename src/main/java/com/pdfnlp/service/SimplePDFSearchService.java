package com.pdfnlp.service;

import com.pdfnlp.model.PDFChunk;
import com.pdfnlp.model.SearchResult;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.index.IndexNotFoundException;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimplePDFSearchService {
    private static final Logger logger = LoggerFactory.getLogger(SimplePDFSearchService.class);
    
    private IndexWriter indexWriter;
    private IndexSearcher indexSearcher;
    private PDFProcessor pdfProcessor;
    private OpenNLP8KoreanProcessor textAnalyzer;
    
    public SimplePDFSearchService() throws IOException {
        this.pdfProcessor = new PDFProcessor();
        this.textAnalyzer = new OpenNLP8KoreanProcessor();
        initializeIndex();
    }
    
    private void initializeIndex() throws IOException {
        try {
            String indexDir = "target/index";
            
            File indexDirFile = new File(indexDir);
            if (!indexDirFile.exists()) {
                indexDirFile.mkdirs();
            }
            
            FSDirectory directory = FSDirectory.open(indexDirFile.toPath());
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            
            this.indexWriter = new IndexWriter(directory, config);
            refreshReader();
            
            logger.info("Index initialized: {}", indexDir);
        } catch (IOException e) {
            logger.error("Error occurred during index initialization", e);
            throw new RuntimeException("Index initialization failed", e);
        }
    }
    
    private void refreshReader() throws IOException {
        if (indexSearcher != null) {
            indexSearcher.getIndexReader().close();
        }
        try {
            indexSearcher = new IndexSearcher(DirectoryReader.open(indexWriter.getDirectory()));
        } catch (IndexNotFoundException e) {
            // Index doesn't exist yet, will be created when first document is added
            logger.info("Index not found, will be created when first document is indexed");
            indexSearcher = null;
        }
    }
    
    public void indexPDF(File pdfFile) throws IOException {
        logger.info("Starting PDF indexing: {}", pdfFile.getName());
        
        // Extract text chunks from PDF
        List<PDFChunk> chunks = pdfProcessor.processPDF(pdfFile);
        
        // Delete existing index for this file
        deleteExistingIndex(pdfFile.getName());
        
        // Add new chunks to index
        for (PDFChunk chunk : chunks) {
            Document doc = createDocument(chunk);
            indexWriter.addDocument(doc);
        }
        
        indexWriter.commit();
        refreshReader();
        
        logger.info("PDF indexing completed: {} ({} chunks)", pdfFile.getName(), chunks.size());
    }
    
    private void deleteExistingIndex(String fileName) throws IOException {
        Term term = new Term("fileName", fileName);
        indexWriter.deleteDocuments(term);
        indexWriter.commit();
    }
    
    private Document createDocument(PDFChunk chunk) {
        Document doc = new Document();
        
        // Full text (for search)
        doc.add(new TextField("content", chunk.getContent(), Field.Store.YES));
        
        // Korean processed text using OpenNLP
        String processedContent = textAnalyzer.analyzeText(chunk.getContent());
        doc.add(new TextField("processedContent", processedContent, Field.Store.NO));
        
        // Metadata
        doc.add(new StringField("fileName", chunk.getFileName(), Field.Store.YES));
        doc.add(new StringField("pageNumber", String.valueOf(chunk.getPageNumber()), Field.Store.YES));
        doc.add(new StringField("chunkIndex", String.valueOf(chunk.getChunkIndex()), Field.Store.YES));
        
        return doc;
    }
    
    public List<SearchResult> search(String query, int maxResults) throws IOException {
        logger.info("Performing search: '{}' (max {} results)", query, maxResults);
        
        if (indexSearcher == null) {
            logger.warn("No index available for search. Please index some PDF files first.");
            return new ArrayList<SearchResult>();
        }
        
        try {
            // Process query with Korean NLP
            String processedQuery = textAnalyzer.analyzeText(query);
            List<String> keywords = textAnalyzer.extractKeywords(query);
            
            // Create multi-field query
            String[] fields = {"content", "processedContent"};
            float[] boosts = {2.0f, 1.5f};
            
            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
            
            // Build combined query
            StringBuilder queryString = new StringBuilder();
            queryString.append("(").append(QueryParser.escape(query)).append(")");
            
            // Add processed query
            if (!processedQuery.trim().isEmpty()) {
                queryString.append(" OR (").append(QueryParser.escape(processedQuery)).append(")");
            }
            
            // Add keywords
            for (String keyword : keywords) {
                if (keyword.length() >= 2) {
                    queryString.append(" OR (").append(QueryParser.escape(keyword)).append(")");
                    // Add wildcard for compound words (avoid starting with *)
                    queryString.append(" OR (").append(QueryParser.escape(keyword)).append("*)");
                }
            }
            
            Query luceneQuery = parser.parse(queryString.toString());
            
            // Execute search
            TopDocs topDocs = indexSearcher.search(luceneQuery, maxResults);
            
            List<SearchResult> results = new ArrayList<SearchResult>();
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document doc = indexSearcher.doc(scoreDoc.doc);
                
                SearchResult result = new SearchResult(
                    doc.get("content"),
                    Integer.parseInt(doc.get("pageNumber")),
                    scoreDoc.score,
                    doc.get("fileName")
                );
                
                results.add(result);
            }
            
            logger.info("Search completed: {} results found", results.size());
            return results;
            
        } catch (ParseException e) {
            logger.error("Query parsing error", e);
            throw new IOException("Failed to parse search query", e);
        }
    }
    
    public int getIndexedDocumentCount() throws IOException {
        if (indexSearcher == null) {
            return 0;
        }
        return indexSearcher.getIndexReader().numDocs();
    }
    
    public void close() throws IOException {
        if (indexWriter != null) {
            indexWriter.close();
        }
        if (indexSearcher != null) {
            indexSearcher.getIndexReader().close();
        }
    }
} 