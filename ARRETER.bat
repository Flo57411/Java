@echo off
chcp 65001 >nul
title Arret Application Todo

echo.
echo ARRET DE L'APPLICATION
echo =====================
echo.

echo Arret de tous les processus Java...
taskkill /f /im java.exe >nul 2>&1

echo.
echo Application arretee !
echo.
pause
