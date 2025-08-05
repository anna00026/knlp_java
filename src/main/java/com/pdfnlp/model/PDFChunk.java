package com.pdfnlp.model;

public class PDFChunk {
    private String content;
    private int pageNumber;
    private String fileName;
    private int chunkIndex;
    
    public PDFChunk(String content, int pageNumber, String fileName, int chunkIndex) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.fileName = fileName;
        this.chunkIndex = chunkIndex;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public int getPageNumber() {
        return pageNumber;
    }
    
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public int getChunkIndex() {
        return chunkIndex;
    }
    
    public void setChunkIndex(int chunkIndex) {
        this.chunkIndex = chunkIndex;
    }
    
    @Override
    public String toString() {
        return String.format("PDFChunk{content='%s', pageNumber=%d, fileName='%s', chunkIndex=%d}", 
            content, pageNumber, fileName, chunkIndex);
    }
} 