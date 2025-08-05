@echo off
echo Compiling PDF AI Search System with KoalaNLP...
echo.

REM Set Java 1.8 path
set JAVA_HOME=C:\Program Files\Java\jdk-1.8
set PATH=%JAVA_HOME%\bin;%PATH%

echo Using Java: %JAVA_HOME%
echo.

REM Check if dependencies are downloaded
if not exist "target\lib" (
    echo Dependencies not found. Downloading them first...
    call download_deps.bat
)

REM Create output directory
if not exist "target\classes" mkdir "target\classes"

echo Compiling Java sources with Java 1.8...
echo Using Java: %JAVA_HOME%

REM Compile with all JAR files in classpath using simplified approach
javac -encoding UTF-8 -cp "target\lib\*" -d target/classes -source 1.8 -target 1.8 src/main/java/com/pdfnlp/*.java src/main/java/com/pdfnlp/model/*.java src/main/java/com/pdfnlp/service/*.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo.
echo Compilation completed successfully!
echo You can now run the application with: run_with_koalanlp.bat
pause 