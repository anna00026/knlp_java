package com.pdfnlp.model;

public class SearchResult {
    private String content;
    private int pageNumber;
    private float score;
    private String fileName;
    
    public SearchResult(String content, int pageNumber, float score, String fileName) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.score = score;
        this.fileName = fileName;
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
    
    public float getScore() {
        return score;
    }
    
    public void setScore(float score) {
        this.score = score;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public String toString() {
        return String.format("SearchResult{content='%s', pageNumber=%d, score=%.2f, fileName='%s'}", 
            content, pageNumber, score, fileName);
    }
} 