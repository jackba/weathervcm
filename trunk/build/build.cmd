echo off
echo *********************************
echo *** weathervcm build commands ***
echo *********************************

if "%1" == "" goto ECHO_HELP
if "%1" == "help" goto ECHO_HELP

call .\initenv.cmd
call ant -buildfile build.xml %*
goto END

:ECHO_HELP
echo off
echo --------------------------------------------------------------------------
echo build [targetName]
echo       cleanup          -- clean all compiled classes
echo       build            -- build vcm.jar and compile all source code
echo       deploy           -- call build, then deploy to web server
echo --------------------------------------------------------------------------
echo on

:END
