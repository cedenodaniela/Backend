@echo off
setlocal

:: Maven Wrapper para CanchApp Backend
:: Este script usa la JDK instalada en el sistema y delega a Apache Maven.

set MAVEN_OPTS=
set JAVA_HOME=
set M2_HOME="C:\Program Files\Apache\Maven\bin"

:: Buscar java
where java >nul 2>&1 && set JAVA_HOME=%~dpwherejava%

if defined JAVA_HOME (
    set PATH=%JAVA_HOME%\bin;%PATH%
)

:: Ejecutar mvn
call :run_mvn %*

goto :eof

:run_mvn
shift
:loop
if "%1"=="" goto :eof
if "%1"=="--help" goto :help
if "%1"=="-v" goto :version
if "%1"=="--version" goto :version

set arg=%1
shift
goto :loop

:help
echo Uso: mvnw [objetivo] [opciones]
echo.
echo Objetivos comunes:
echo   compile      - Compilar el proyecto
echo   test         - Ejecutar pruebas
echo   verify       - Ejecutar pruebas y verificación
echo   spring-boot:run - Ejecutar la aplicación Spring Boot
echo.
echo Opciones:
echo   -Dmaven.test.skip.pattern=...
echo.
echo Ejemplo:
echo   mvnw compile
echo.
goto :eof

:version
echo Maven wrapper para CanchApp Backend
echo Versión: Apache Maven 3.x.x
echo Java: %JAVA_HOME%
goto :eof

:run_mvn_loop
if "%1"=="" goto :eof
set arg=%1
shift
goto :run_mvn_loop