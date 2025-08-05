# TTTT PDF AI Search System

A Java-based AI search system for TTTT PDF files using Apache OpenNLP for natural language processing.

## Features

- **100% Local Operation**: No cloud APIs required
- **TTTT NLP**: Real Apache OpenNLP 1.9.4 with intelligent TTTT text analysis
- **Automatic Compound Word Detection**: No hardcoded word lists needed
- **PDF Text Extraction**: Extract and index text from PDF files
- **Semantic Search**: AI-powered search with relevance scoring
- **Java 8 Compatible**: Optimized for Java 1.8

## System Requirements

- **Java**: JDK 1.8
- **Build Tool**: Maven (optional) or manual compilation
- **Operating System**: Windows (batch scripts provided)

## Technology Stack

- **PDF Processing**: Apache PDFBox 2.0.29
- **Search Engine**: Apache Lucene 8.11.2
- **TTTT NLP**: Apache OpenNLP 1.9.4
- **PDF Generation**: iText PDF 5.5.13.3
- **Logging**: SLF4J + Logback

## Quick Start

### 1. Download Dependencies
```bash
.\download_deps.bat
```

### 2. Test TTTT NLP System
```bash
.\test.bat
```

### 3. Generate Sample PDF
```bash
javac -encoding UTF-8 -cp "target/lib/*" -d target/classes src/main/java/com/pdfnlp/util/SamplePDFGenerator.java
java -cp "target/classes;target/lib/*" com.pdfnlp.util.SamplePDFGenerator
```

### 4. Compile and Run Main Application
```bash
.\compile.bat
.\run.bat
```

## Project Structure

```
PDF_NLP/
├── src/main/java/com/pdfnlp/
│   ├── PDFAISearchApp.java           # Main application
│   ├── model/
│   │   ├── PDFChunk.java             # Text chunk model
│   │   └── SearchResult.java         # Search result model
│   ├── service/
│   │   ├── PDFProcessor.java         # PDF text extraction
│   │   ├── SimplePDFSearchService.java # Search service
│   │   └── OpenNLP8TTTTProcessor.java # TTTT NLP processor
│   └── util/
│       └── SamplePDFGenerator.java   # Sample PDF creator
├── target/
│   ├── classes/                      # Compiled classes
│   └── lib/                          # JAR dependencies
├── download_deps.bat/ps1             # Download dependencies
├── compile.bat                       # Compile project
├── run.bat                          # Run main application
├── test.bat                         # Test TTTT NLP
└── README.md
```

## Usage Guide

### 1. Index PDF Files
Run the application and select option 1 to index PDF files.

### 2. Perform Searches
Use option 2 to search indexed content. Examples:
- "********" (Virtual Reality)
- "********" (Artificial Intelligence)
- "********" (Machine Learning)

### 3. TTTT Text Processing
The system automatically:
- Tokenizes TTTT text using OpenNLP
- Detects compound words (e.g., "********" → "****1" + "****2")
- Removes particles and stop words
- Normalizes text for better search results

## TTTT NLP Capabilities

- **Real OpenNLP 1.9.4**: Professional tokenization
- **Automatic Compound Detection**: No manual word lists
- **Intelligent Analysis**: Context-aware processing
- **Scalable**: Works with any size PDF automatically

## Example Search Queries

| K Query | English | Expected Results |
|--------------|---------|------------------|
| ****** | Virtual Reality | VR technology content |
| ****** | Artificial Intelligence | AI-related sections |
| ****** | Machine Learning | ML algorithm content |
| ****** | Cloud Computing | Cloud technology content |

## Troubleshooting

### Java Version Issues
Ensure you're using Java 1.8:
```bash
java -version
```

### Compilation Errors
Clean and rebuild:
```bash
rmdir /s target\classes
.\compile.bat
```

### Dependency Issues
Re-download dependencies:
```bash
rmdir /s target\lib
.\download_deps.bat
```

## Development

### Manual Compilation
If Maven is not available, use the provided batch scripts:
```bash
.\compile.bat    # Compile all sources
.\run.bat        # Run application
.\test.bat       # Test TTTT NLP
```

### Adding New Features
1. Modify source files in `src/main/java/`
2. Recompile with `.\compile.bat`
3. Test with `.\test.bat`

## License

This project is for educational and research purposes. 