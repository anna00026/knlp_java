@echo off
echo Compiling PDF AI Search System manually...
echo.

REM Create output directories
if not exist "target\classes" mkdir "target\classes"
if not exist "target\lib" mkdir "target\lib"

REM Download dependencies manually (you'll need to do this once)
echo Downloading dependencies...
if not exist "target\lib\pdfbox-2.0.29.jar" (
    echo Please download the following JAR files to target\lib\ directory:
    echo - pdfbox-2.0.29.jar
    echo - lucene-core-4.10.4.jar
    echo - lucene-analyzers-common-4.10.4.jar
    echo - lucene-queryparser-4.10.4.jar
    echo - itextpdf-5.5.13.3.jar
    echo - jackson-databind-2.12.7.1.jar
    echo - slf4j-api-1.7.36.jar
    echo - logback-classic-1.2.12.jar
    echo - commons-math3-3.6.1.jar
    echo.
    echo You can download them from Maven Central Repository
    pause
    exit /b 1
)

REM Set Java 1.8 path
set JAVA_HOME=C:\Program Files\Java\jdk-1.8
set PATH=%JAVA_HOME%\bin;%PATH%

REM Compile the project
echo Compiling Java sources with Java 1.8...
echo Using Java: %JAVA_HOME%
javac -cp "target\lib\*" -d "target\classes" -source 1.8 -target 1.8 -encoding UTF-8 src\main\java\com\pdfnlp\*.java src\main\java\com\pdfnlp\model\*.java src\main\java\com\pdfnlp\service\*.java src\main\java\com\pdfnlp\util\*.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo You can now run the application using: run_manual.bat
pause 