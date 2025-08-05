# Download dependencies for PDF AI Search with KoalaNLP
$ErrorActionPreference = "Stop"

Write-Host "Downloading JAR dependencies for PDF AI Search with KoalaNLP..." -ForegroundColor Green

# Create target directories
$libDir = "target/lib"
if (!(Test-Path $libDir)) {
    New-Item -ItemType Directory -Path $libDir -Force | Out-Null
}

# Function to download file if it doesn't exist
function Download-IfNotExists {
    param($url, $output)
    if (!(Test-Path $output)) {
        Write-Host "Downloading $([System.IO.Path]::GetFileName($output))..." -ForegroundColor Yellow
        try {
            $webClient = New-Object System.Net.WebClient
            $webClient.DownloadFile($url, $output)
            Write-Host "Downloaded: $([System.IO.Path]::GetFileName($output))" -ForegroundColor Green
        } catch {
            Write-Host "Failed to download: $([System.IO.Path]::GetFileName($output))" -ForegroundColor Red
            Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
        }
    } else {
        Write-Host "Already exists: $([System.IO.Path]::GetFileName($output))" -ForegroundColor Cyan
    }
}

# Core dependencies
$deps = @{
    # PDF Processing
    "https://repo1.maven.org/maven2/org/apache/pdfbox/pdfbox/2.0.29/pdfbox-2.0.29.jar" = "$libDir/pdfbox-2.0.29.jar"
    "https://repo1.maven.org/maven2/org/apache/pdfbox/fontbox/2.0.29/fontbox-2.0.29.jar" = "$libDir/fontbox-2.0.29.jar"
    "https://repo1.maven.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar" = "$libDir/commons-logging-1.2.jar"
    
    # PDF Generation
    "https://repo1.maven.org/maven2/com/itextpdf/itextpdf/5.5.13.3/itextpdf-5.5.13.3.jar" = "$libDir/itextpdf-5.5.13.3.jar"
    
    # Lucene Search Engine
    "https://repo1.maven.org/maven2/org/apache/lucene/lucene-core/8.11.2/lucene-core-8.11.2.jar" = "$libDir/lucene-core-8.11.2.jar"
    "https://repo1.maven.org/maven2/org/apache/lucene/lucene-analyzers-common/8.11.2/lucene-analyzers-common-8.11.2.jar" = "$libDir/lucene-analyzers-common-8.11.2.jar"
    "https://repo1.maven.org/maven2/org/apache/lucene/lucene-queryparser/8.11.2/lucene-queryparser-8.11.2.jar" = "$libDir/lucene-queryparser-8.11.2.jar"
    "https://repo1.maven.org/maven2/org/apache/lucene/lucene-queries/8.11.2/lucene-queries-8.11.2.jar" = "$libDir/lucene-queries-8.11.2.jar"
    "https://repo1.maven.org/maven2/org/apache/lucene/lucene-sandbox/8.11.2/lucene-sandbox-8.11.2.jar" = "$libDir/lucene-sandbox-8.11.2.jar"
    
    # JSON Processing
    "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.12.7.1/jackson-databind-2.12.7.1.jar" = "$libDir/jackson-databind-2.12.7.1.jar"
    "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.12.7/jackson-core-2.12.7.jar" = "$libDir/jackson-core-2.12.7.jar"
    "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.12.7/jackson-annotations-2.12.7.jar" = "$libDir/jackson-annotations-2.12.7.jar"
    
    # Logging
    "https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar" = "$libDir/slf4j-api-1.7.36.jar"
    "https://repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.2.12/logback-classic-1.2.12.jar" = "$libDir/logback-classic-1.2.12.jar"
    "https://repo1.maven.org/maven2/ch/qos/logback/logback-core/1.2.12/logback-core-1.2.12.jar" = "$libDir/logback-core-1.2.12.jar"
    
    # Math utilities
    "https://repo1.maven.org/maven2/org/apache/commons/commons-math3/3.6.1/commons-math3-3.6.1.jar" = "$libDir/commons-math3-3.6.1.jar"
    
    # Testing
    "https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar" = "$libDir/junit-4.13.2.jar"
    "https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar" = "$libDir/hamcrest-core-1.3.jar"
}

# Download standard dependencies
Write-Host "`nDownloading standard dependencies..." -ForegroundColor Cyan
foreach ($url in $deps.Keys) {
    Download-IfNotExists $url $deps[$url]
}

# Apache OpenNLP dependencies (Java 8 Compatible)
Write-Host "`nDownloading Apache OpenNLP dependencies..." -ForegroundColor Cyan

$opennlpUrls = @{
    "https://repo1.maven.org/maven2/org/apache/opennlp/opennlp-tools/1.9.4/opennlp-tools-1.9.4.jar" = "$libDir/opennlp-tools-1.9.4.jar"
}

foreach ($url in $opennlpUrls.Keys) {
    Download-IfNotExists $url $opennlpUrls[$url]
}

Write-Host "`nDependency download completed!" -ForegroundColor Green
Write-Host "All JAR files are in: $libDir" -ForegroundColor Cyan

# Check if all files exist
$missingFiles = @()
foreach ($file in $deps.Values + $opennlpUrls.Values) {
    if (!(Test-Path $file)) {
        $missingFiles += $file
    }
}

if ($missingFiles.Count -gt 0) {
    Write-Host "`nWARNING: Some files failed to download:" -ForegroundColor Yellow
    foreach ($file in $missingFiles) {
        Write-Host "  - $file" -ForegroundColor Red
    }
    Write-Host "`nYou may need to download these manually." -ForegroundColor Yellow
} else {
    Write-Host "`nAll dependencies downloaded successfully!" -ForegroundColor Green
} 