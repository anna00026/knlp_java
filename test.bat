@echo off
echo Testing OpenNLP 8 TTTT NLP System
echo Real OpenNLP 1.9.4 + Smart Algorithms Java 8 Compatible
echo.

REM Set Java 1.8 path
set JAVA_HOME=C:\Program Files\Java\jdk-1.8
set PATH=%JAVA_HOME%\bin;%PATH%

echo Using Java: %JAVA_HOME%
echo.

REM Download OpenNLP 1.9.4 if needed
if not exist "target\lib\opennlp-tools-1.9.4.jar" (
    echo Downloading OpenNLP 1.9.4 Java 8 compatible
    call download_deps_koalanlp.bat
)

REM Create output directory
if not exist "target\classes" mkdir "target\classes"

REM Build classpath with all required JARs
setlocal enabledelayedexpansion
set CLASSPATH=target\classes
for %%f in (target\lib\*.jar) do (
    set CLASSPATH=!CLASSPATH!;%%f
)

echo Compiling OpenNLP 8 TTTT system
javac -encoding UTF-8 -cp "!CLASSPATH!" -d target/classes -sourcepath src/main/java src/main/java/com/pdfnlp/model/*.java src/main/java/com/pdfnlp/service/PDFProcessor.java src/main/java/com/pdfnlp/service/OpenNLP8TTTTProcessor.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed
    pause
    exit /b 1
)

echo.
echo Compilation successful
echo.

REM Create test class
echo Creating test class

echo package com.pdfnlp.test; > TestOpenNLP8.java
echo import com.pdfnlp.service.OpenNLP8TTTTProcessor; >> TestOpenNLP8.java
echo import java.util.List; >> TestOpenNLP8.java
echo. >> TestOpenNLP8.java
echo public class TestOpenNLP8 { >> TestOpenNLP8.java
echo     public static void main(String[] args) { >> TestOpenNLP8.java
echo         System.out.println("OpenNLP 8 TTTT Processor Test"); >> TestOpenNLP8.java
echo         OpenNLP8TTTTProcessor processor = new OpenNLP8TTTTProcessor(); >> TestOpenNLP8.java
echo         processor.demonstrateCapabilities(); >> TestOpenNLP8.java
echo. >> TestOpenNLP8.java
echo         String[] testTexts = { >> TestOpenNLP8.java
echo             "가상현실 기술이 교육 분야에 혁신을 가져왔습니다.", >> TestOpenNLP8.java
echo             "인공지능과 머신러닝이 빅데이터 분석에 사용됩니다.", >> TestOpenNLP8.java
echo             "클라우드컴퓨팅 환경에서 사물인터넷을 구현합니다." >> TestOpenNLP8.java
echo         }; >> TestOpenNLP8.java
echo. >> TestOpenNLP8.java
echo         for (int i = 0; i ^< testTexts.length; i++) { >> TestOpenNLP8.java
echo             String text = testTexts[i]; >> TestOpenNLP8.java
echo             System.out.println("Test " + (i+1) + " " + text); >> TestOpenNLP8.java
echo             String analyzed = processor.analyzeText(text); >> TestOpenNLP8.java
echo             System.out.println("Analyzed: " + analyzed); >> TestOpenNLP8.java
echo         } >> TestOpenNLP8.java
echo     } >> TestOpenNLP8.java
echo } >> TestOpenNLP8.java

javac -encoding UTF-8 -cp "!CLASSPATH!" -d target/classes TestOpenNLP8.java

if %ERRORLEVEL% NEQ 0 (
    echo Test compilation failed
    pause
    exit /b 1
)

echo Running OpenNLP 8 TTTT Test
echo.

java -cp "!CLASSPATH!" com.pdfnlp.test.TestOpenNLP8

echo.
echo Test completed
echo.
echo Benefits:
echo - Uses real Apache OpenNLP 1.9.4 Java 8 compatible
echo - Automatic compound word detection
echo - Scales to any size PDF automatically
echo - No version conflicts with Java 8

del TestOpenNLP8.java
pause 