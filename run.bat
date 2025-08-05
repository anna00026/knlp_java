@echo off
echo Running PDF AI Search System with KoalaNLP...
echo.

REM Set Java 1.8 path
set JAVA_HOME=C:\Program Files\Java\jdk-1.8
set PATH=%JAVA_HOME%\bin;%PATH%

echo Using Java: %JAVA_HOME%
echo.

REM Check if compiled
if not exist "target\classes\com\pdfnlp\PDFAISearchApp.class" (
    echo Application not compiled. Compiling first...
    call compile_with_koalanlp.bat
)

REM Build classpath with all JARs and compiled classes
setlocal enabledelayedexpansion
set CLASSPATH=target\classes
for %%f in (target\lib\*.jar) do (
    set CLASSPATH=!CLASSPATH!;%%f
)

echo Running PDF AI Search Application...
java -cp "!CLASSPATH!" com.pdfnlp.PDFAISearchApp

pause 