@echo off
echo Generating Sample PDF manually...
echo.

REM Check if classes exist
if not exist "target\classes\com\pdfnlp\util\SamplePDFGenerator.class" (
    echo Classes not found. Please run compile_manual.bat first.
    pause
    exit /b 1
)

REM Set Java 1.8 path
set JAVA_HOME=C:\Program Files\Java\jdk-1.8
set PATH=%JAVA_HOME%\bin;%PATH%

REM Run the PDF generator
echo Generating sample PDF with Java 1.8...
echo Using Java: %JAVA_HOME%
java -cp "target\classes;target\lib\*" com.pdfnlp.util.SamplePDFGenerator

if %ERRORLEVEL% NEQ 0 (
    echo PDF generation failed!
    pause
    exit /b 1
)

echo.
echo Sample PDF generated successfully!
echo File: sample_korean_tech.pdf
echo.
echo You can now run the search application using: run_manual.bat
pause 