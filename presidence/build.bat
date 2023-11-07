@echo off
@REM set PROJECT_DIR=F:\S5\archilog\cin\sante
@REM set APP_NAME=sante
set APP_NAME=foncier
set WILDFLY_DIR=F:\wildfly-29.0.1.Final
set DEPLOYMENT_DIR=%WILDFLY_DIR%\standalone\deployments

xcopy bin build\WEB-INF\classes /Y /E /I
jar -cvf presidence.war -C build . 
jar -cvf presidence.ear presidence.war
copy  presidence.ear %DEPLOYMENT_DIR%