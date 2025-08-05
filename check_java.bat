@echo off
echo Checking Java installation...
echo.

REM Set Java 1.8 path
set JAVA_HOME=C:\Program Files\Java\jdk-1.8
set PATH=%JAVA_HOME%\bin;%PATH%

echo Using Java Home: %JAVA_HOME%
echo.

REM Check if Java directory exists
if not exist "%JAVA_HOME%" (
    echo ERROR: Java 1.7 not found at %JAVA_HOME%
    echo Please check your Java installation.
    pause
    exit /b 1
)

REM Check Java version
echo Checking Java version:
java -version

if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Java is not working properly
    pause
    exit /b 1
)

echo.
echo Checking Java compiler version:
javac -version

if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Java compiler is not working properly
    pause
    exit /b 1
)

echo.
echo Java 1.8 is properly configured and ready to use!
echo You can now run: compile_manual.bat
pause 