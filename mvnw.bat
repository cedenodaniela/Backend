@echo off
cd /d %~dp0
:: Usar JDK 17 instalada en el sistema
set JAVA_HOME="C:\Program Files\Java\jdk-17"
if not exist "%JAVA_HOME%" (
    echo.
    echo ERROR: No se encuentra JDK 17 en %JAVA_HOME%
    echo Instálalo o ajusta la variable JAVA_HOME en mvnw.bat
    exit /b 1
)
echo Usando JDK 17 en %JAVA_HOME%
set PATH=%JAVA_HOME%\bin;%PATH%

:: Ejecutar mvn si está disponible
where mvn >nul && mvn %1 %2 %3 %4 %5
if not where mvn >nul (
    echo.
    echo "Maven no está instalado en el sistema."
    echo "Para compilar: 1. Instala Maven, 2. Asegúrate de que 'mvn' esté en PATH, 3. Ejecuta mvn compile"
    echo.
    echo "Alternativa: Usa 'java -jar' después de compilar con Maven externo."
)