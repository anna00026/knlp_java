@echo off
echo Running PDF AI Search System...
echo.

REM Check if classes exist
if not exist "target\classes\com\pdfnlp\PDFAISearchApp.class" (
    echo Classes not found. Please run compile_manual.bat first.
    pause
    exit /b 1
)

REM Set Java 1.8 path
set JAVA_HOME=C:\Program Files\Java\jdk-1.8
set PATH=%JAVA_HOME%\bin;%PATH%

REM Run the application
echo Starting the application with Java 1.8...
echo Using Java: %JAVA_HOME%
java -cp "target\classes;target\lib\*" com.pdfnlp.PDFAISearchApp

pause 