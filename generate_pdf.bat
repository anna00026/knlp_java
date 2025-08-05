@echo off
echo Generating Sample PDF with Korean Content...
echo.

echo Building the project...
call mvn clean compile

if %ERRORLEVEL% NEQ 0 (
    echo Build failed! Please check the error messages above.
    pause
    exit /b 1
)

echo.
echo Running PDF Generator...
java -cp "target/classes;target/dependency/*" com.pdfnlp.util.SamplePDFGenerator

if %ERRORLEVEL% NEQ 0 (
    echo PDF generation failed! Please check the error messages above.
    pause
    exit /b 1
)

echo.
echo Sample PDF generated successfully!
echo File: sample_korean_tech.pdf
echo.
echo You can now run the search application to test Korean text search.
pause 