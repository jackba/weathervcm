echo off
echo *********************************
echo *** weathervcm build commands ***
echo *********************************

if "%1" == "help" goto ECHO_HELP

call .\initenv.cmd
call ant -buildfile build.xml %*
goto END

:ECHO_HELP
echo off
echo --------------------------------------------------------------------------
echo build [targetName]
echo       clean         -- clean all compiled classes
echo --------------------------------------------------------------------------
echo on

:END
