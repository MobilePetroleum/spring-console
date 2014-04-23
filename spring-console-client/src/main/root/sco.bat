@echo off

::set JAVA_HOME=C:\Program Files\Java\alternative_version\

set JAVA="java"

if not "%JAVA_HOME%" == "" (
    set JAVA="%JAVA_HOME%\bin\java"
)

%JAVA% -jar "%~dp0\spring-console-client.jar" %*