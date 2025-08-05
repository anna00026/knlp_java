package com.pdfnlp;

import com.pdfnlp.service.SimplePDFSearchService;
import com.pdfnlp.model.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class PDFAISearchApp {
    private static final Logger logger = LoggerFactory.getLogger(PDFAISearchApp.class);
    private static final String INDEX_DIR = "pdf_index";
    
    public static void main(String[] args) {
        System.out.println("=== PDF AI Search System (TTTT Text Support) ===");
        System.out.println("Performing AI-based search in PDF files.");
        
        SimplePDFSearchService searchService;
        try {
            searchService = new SimplePDFSearchService();
        } catch (Exception e) {
            System.out.println("Failed to initialize search service: " + e.getMessage());
            return;
        }
        Scanner scanner = new Scanner(System.in, "UTF-8");
        
        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Index PDF File");
            System.out.println("2. Perform Search");
            System.out.println("3. Exit");
            System.out.print("Select (1-3): ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    indexPDF(scanner, searchService);
                    break;
                case "2":
                    performSearch(scanner, searchService);
                    break;
                case "3":
                    System.out.println("Exiting program.");
                    try {
                        searchService.close();
                    } catch (Exception e) {
                        System.out.println("Error closing search service: " + e.getMessage());
                    }
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }
    
    private static void indexPDF(Scanner scanner, SimplePDFSearchService searchService) {
        System.out.print("Enter PDF file path: ");
        String pdfPath = scanner.nextLine().trim();
        
        File pdfFile = new File(pdfPath);
        if (!pdfFile.exists()) {
            System.out.println("File not found: " + pdfPath);
            return;
        }
        
        try {
            System.out.println("Indexing PDF file...");
            searchService.indexPDF(pdfFile);
            System.out.println("Indexing completed!");
        } catch (Exception e) {
            logger.error("Error occurred during PDF indexing", e);
            System.out.println("Error occurred during indexing: " + e.getMessage());
        }
    }
    
    private static void performSearch(Scanner scanner, SimplePDFSearchService searchService) {
        System.out.print("Enter search query: ");
        String query = scanner.nextLine().trim();
        
        if (query.isEmpty()) {
            System.out.println("Please enter a search query.");
            return;
        }
        
        try {
            System.out.println("Searching...");
            List<SearchResult> results = searchService.search(query, 10);
            
            if (results.isEmpty()) {
                System.out.println("No search results found.");
            } else {
                System.out.println("\n=== Search Results ===");
                for (int i = 0; i < results.size(); i++) {
                    SearchResult result = results.get(i);
                    System.out.printf("%d. Score: %.2f, Page: %d\n", 
                        i + 1, result.getScore(), result.getPageNumber());
                    System.out.println("Content: " + result.getContent());
                    System.out.println("---");
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred during search", e);
            System.out.println("Error occurred during search: " + e.getMessage());
        }
    }
} 