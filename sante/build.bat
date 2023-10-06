@echo off
set PROJECT_DIR=F:\S5\archilog\cin\sante
set APP_NAME=sante
set WILDFLY_DIR=F:\wildfly-29.0.1.Final
set DEPLOYMENT_DIR=%WILDFLY_DIR%\standalone\deployments


jar -cvf build/%APP_NAME%.jar -C bin/ .
jar -cvf build/%APP_NAME%.war -C webapp . 
jar -cvf %APP_NAME%.ear -C build .
copy %APP_NAME%.ear %DEPLOYMENT_DIR%

