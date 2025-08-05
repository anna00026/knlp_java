@echo off
echo Downloading KoalaNLP and all dependencies...
echo.

REM Create target directories
if not exist "target" mkdir "target"
if not exist "target\lib" mkdir "target\lib"
if not exist "target\classes" mkdir "target\classes"

echo Calling PowerShell script to download dependencies...
powershell -ExecutionPolicy Bypass -File "download_deps_koalanlp.ps1"

echo.
echo Dependency download completed!
echo You can now compile and run the project.
pause 