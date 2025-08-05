@echo off
echo Setting up directory structure for manual compilation...
echo.

REM Create necessary directories
if not exist "target" mkdir "target"
if not exist "target\classes" mkdir "target\classes"
if not exist "target\lib" mkdir "target\lib"

echo Directories created successfully!
echo.
echo Next steps:
echo 1. Download JAR files listed in MANUAL_DOWNLOAD_GUIDE.txt to target\lib\
echo 2. Run: compile_manual.bat
echo 3. Run: generate_pdf_manual.bat
echo 4. Run: run_manual.bat
echo.
echo OR use IntelliJ IDEA (easier):
echo 1. File → Settings → Build Tools → Maven
echo 2. Set "JDK for importer" to Java 8 or higher
echo 3. Right-click project → "Build Module 'pdf-ai-search'"
echo 4. Right-click PDFAISearchApp.java → "Run 'PDFAISearchApp.main()'"
echo.
pause 