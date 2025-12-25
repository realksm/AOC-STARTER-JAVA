@echo off
set MODULE=aoc-2025

echo Compiling...
javac -cp out\production\%MODULE% -d -cp out\production\%MODULE% src\aoc\Main.java >nul 2>&1

echo Running...
java -cp out\production\%MODULE% aoc.Main %*