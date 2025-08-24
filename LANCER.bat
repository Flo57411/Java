@echo off
chcp 65001 >nul
title Application Todo

echo.
echo LANCEMENT AUTOMATIQUE
echo ====================
echo.

REM Configuration Java
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo Java configure
echo Demarrage du serveur...
echo.

REM Demarrage avec le wrapper Maven
start /B .\mvnw.cmd spring-boot:run

echo Attente du demarrage...
timeout /t 20 /nobreak >nul

echo Ouverture du navigateur...
start http://localhost:8080

echo.
echo APPLICATION LANCEE !
echo http://localhost:8080
echo.
echo Pour arreter : fermez cette fenetre
echo.

pause >nul

echo.
echo Arret...
taskkill /f /im java.exe >nobreak >nul 2>&1
echo Arrete !
pause
