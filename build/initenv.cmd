@rem set env bat

@echo off
if exist initdir.cmd call initdir.cmd

@rem set default variables
set ANT_HOME=D:\Java\ant
set JAVA_HOME=D:\Java\jdk\jdk1.5.0_03

set SERVER_CLASSES=%CORE_HOME%\deploy\resource;%CORE_HOME%\deploy\classes
set LIB_PATH=%CORE_HOME%\deploy\lib

rem :setpath
if "%OLD_PATH%" == "" set OLD_PATH=%PATH%
set PATH=.;%ANT_HOME%\bin;%JAVA_HOME%\bin;%OLD_PATH%

:end
