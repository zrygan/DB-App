REM     By: Zhean Ganituen
REM     Runs the Java files

@echo off

set O_DIR=%CD%

cd src\com\HospitalDB

javac *.java

echo RUN ::: all java files have been compiled

java App

cd /d %O_DIR%

ECHO RUN ::: Returned to O_DIR=%O_DIR%