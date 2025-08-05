@echo off
echo Building and Running PDF AI Search System...
echo.

echo Building the project...
call mvn clean package

if %ERRORLEVEL% NEQ 0 (
    echo Build failed! Please check the error messages above.
    pause
    exit /b 1
)

echo.
echo Build successful! Starting the application...
echo.
java -jar target/pdf-ai-search-1.0.0.jar

pause 