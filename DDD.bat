REM     By: Zhean Ganituen
REM     Deletes all class files in src/com/HospitalDB

@echo off

set O_DIR=%CD%

cd src\com\HospitalDB

cd *.class

echo DEL_CLASS ::: all class files have been deleted

cd /d %O_DIR%

ECHO DEL_CLASS ::: Returned to O_DIR=%O_DIR%