package com.pdfnlp.service;

import com.pdfnlp.model.PDFChunk;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFProcessor {
    private static final Logger logger = LoggerFactory.getLogger(PDFProcessor.class);
    private static final int CHUNK_SIZE = 1000; // Character-based chunk size setting
    private static final int OVERLAP_SIZE = 200; // Overlapping characters between chunks
    
    public List<PDFChunk> processPDF(File pdfFile) throws IOException {
        List<PDFChunk> chunks = new ArrayList<PDFChunk>();
        
        PDDocument document = null;
        try {
            document = PDDocument.load(pdfFile);
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);
            
            int totalPages = document.getNumberOfPages();
            logger.info("Processing {} pages from PDF file '{}'.", totalPages, pdfFile.getName());
            
            for (int pageNum = 1; pageNum <= totalPages; pageNum++) {
                stripper.setStartPage(pageNum);
                stripper.setEndPage(pageNum);
                
                String pageText = stripper.getText(document);
                if (pageText != null && !pageText.trim().isEmpty()) {
                    // Split page text into chunks
                    List<String> pageChunks = splitIntoChunks(pageText);
                    
                    for (int chunkIndex = 0; chunkIndex < pageChunks.size(); chunkIndex++) {
                        String chunkText = pageChunks.get(chunkIndex);
                        if (!chunkText.trim().isEmpty()) {
                            PDFChunk chunk = new PDFChunk(
                                chunkText.trim(),
                                pageNum,
                                pdfFile.getName(),
                                chunkIndex
                            );
                            chunks.add(chunk);
                        }
                    }
                }
            }
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    logger.warn("Error closing PDF document", e);
                }
            }
        }
        
        logger.info("Generated {} chunks from PDF file '{}'.", chunks.size(), pdfFile.getName());
        return chunks;
    }
    
    private List<String> splitIntoChunks(String text) {
        List<String> chunks = new ArrayList<String>();
        
        if (text.length() <= CHUNK_SIZE) {
            chunks.add(text);
            return chunks;
        }
        
        int start = 0;
        while (start < text.length()) {
            int end = Math.min(start + CHUNK_SIZE, text.length());
            
            // Try to split at sentence boundaries
            if (end < text.length()) {
                int lastPeriod = text.lastIndexOf('.', end);
                int lastQuestion = text.lastIndexOf('?', end);
                int lastExclamation = text.lastIndexOf('!', end);
                int lastNewline = text.lastIndexOf('\n', end);
                
                int bestBreak = Math.max(Math.max(lastPeriod, lastQuestion), 
                                       Math.max(lastExclamation, lastNewline));
                
                if (bestBreak > start + CHUNK_SIZE / 2) {
                    end = bestBreak + 1;
                }
            }
            
            chunks.add(text.substring(start, end));
            start = Math.max(start + 1, end - OVERLAP_SIZE);
        }
        
        return chunks;
    }
} 