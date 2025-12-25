@echo off
set MODULE=aoc-2025

REM Enable ANSI colors
for /f %%A in ('echo prompt $E ^| cmd') do set "ESC=%%A"

REM Colors
set GREEN=%ESC%[32m
set CYAN=%ESC%[36m
set YELLOW=%ESC%[33m
set RESET=%ESC%[0m

REM Check command
if "%1"=="gen" goto GEN

REM ===== RUN MODE =====
echo %CYAN%[AoC] Compiling...%RESET%
javac -cp out\production\%MODULE% -d out\production\%MODULE% src\aoc\Main.java >nul 2>&1

echo %GREEN%[AoC] Running Day %2%RESET%
java -cp out\production\%MODULE% aoc.Main %*
goto END

:GEN
java -cp out\production\%MODULE% aoc.Main %*
goto END

:END
