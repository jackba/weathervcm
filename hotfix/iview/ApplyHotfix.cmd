@echo off
Set DST_HOME=C:\Program Files\Radvision\iVIEW Suite\iCM\jboss
if NOT EXIST "%DST_HOME%"  goto END_FOR_DST_HOME_NOT_BE_FOUND
Set SRC_HOME=.
set BAK_HOME=%DST_HOME%\backup
Set BackSubDir=D%date%T%time%
set BackSubDir=%BackSubDir:/=%
set BackSubDir=%BackSubDir::=%
set BackSubDir=%BackSubDir:.=%
set BackSubDir=%BackSubDir:,=%
Set BAK_HOME=%BAK_HOME%\%BackSubDir%
echo Backup directory is: "%BAK_HOME%"
if not exist "%BAK_HOME%\weather-hotfix" mkdir "%BAK_HOME%\weather-hotfix"
echo Backup old file:ResourceService.wsdl
Copy "%DST_HOME%\server\default\deploy\vcs.ear\icmservice.war\WEB-INF\wsdl\ResourceService.wsdl" "%BAK_HOME%\weather-hotfix"
echo Apply new file:ResourceService.wsdl
Copy "%SRC_HOME%\weather-hotfix\ResourceService.wsdl" "%DST_HOME%\server\default\deploy\vcs.ear\icmservice.war\WEB-INF\wsdl"
if not exist "%BAK_HOME%\weather-hotfix" mkdir "%BAK_HOME%\weather-hotfix"
echo Backup old file:ResourceService_schema1.xsd
Copy "%DST_HOME%\server\default\deploy\vcs.ear\icmservice.war\WEB-INF\wsdl\ResourceService_schema1.xsd" "%BAK_HOME%\weather-hotfix"
echo Apply new file:ResourceService_schema1.xsd
Copy "%SRC_HOME%\weather-hotfix\ResourceService_schema1.xsd" "%DST_HOME%\server\default\deploy\vcs.ear\icmservice.war\WEB-INF\wsdl"
if not exist "%BAK_HOME%\weather-hotfix" mkdir "%BAK_HOME%\weather-hotfix"
echo Backup old file:ResourceProvider.class
Copy "%DST_HOME%\server\default\deploy\vcs.ear\icmservice.war\WEB-INF\classes\com\radvision\icm\service\impl\ResourceProvider.class" "%BAK_HOME%\weather-hotfix"
echo Apply new file:ResourceProvider.class
Copy "%SRC_HOME%\weather-hotfix\ResourceProvider.class" "%DST_HOME%\server\default\deploy\vcs.ear\icmservice.war\WEB-INF\classes\com\radvision\icm\service\impl"
if not exist "%BAK_HOME%\weather-hotfix" mkdir "%BAK_HOME%\weather-hotfix"
echo Backup old file:GetResourceInfos.class
Copy "%DST_HOME%\server\default\deploy\vcs.ear\icmservice.war\WEB-INF\classes\com\radvision\icm\service\impl\jaxws\GetResourceInfos.class" "%BAK_HOME%\weather-hotfix"
echo Apply new file:GetResourceInfos.class
Copy "%SRC_HOME%\weather-hotfix\GetResourceInfos.class" "%DST_HOME%\server\default\deploy\vcs.ear\icmservice.war\WEB-INF\classes\com\radvision\icm\service\impl\jaxws"
if not exist "%BAK_HOME%\weather-hotfix" mkdir "%BAK_HOME%\weather-hotfix"
echo Backup old file:GetResourceInfosResponse.class
Copy "%DST_HOME%\server\default\deploy\vcs.ear\icmservice.war\WEB-INF\classes\com\radvision\icm\service\impl\jaxws\GetResourceInfosResponse.class" "%BAK_HOME%\weather-hotfix"
echo Apply new file:GetResourceInfosResponse.class
Copy "%SRC_HOME%\weather-hotfix\GetResourceInfosResponse.class" "%DST_HOME%\server\default\deploy\vcs.ear\icmservice.war\WEB-INF\classes\com\radvision\icm\service\impl\jaxws"
if not exist "%BAK_HOME%\weather-hotfix" mkdir "%BAK_HOME%\weather-hotfix"
echo Backup old file:McuResourceInfo.class
Copy "%DST_HOME%\server\default\deploy\vcs.ear\icmservice.war\WEB-INF\classes\com\radvision\icm\service\resource\McuResourceInfo.class" "%BAK_HOME%\weather-hotfix"
echo Apply new file:McuResourceInfo.class
Copy "%SRC_HOME%\weather-hotfix\McuResourceInfo.class" "%DST_HOME%\server\default\deploy\vcs.ear\icmservice.war\WEB-INF\classes\com\radvision\icm\service\resource"
if not exist "%BAK_HOME%\weather-hotfix" mkdir "%BAK_HOME%\weather-hotfix"
echo Backup old file:McuResourceResult.class
Copy "%DST_HOME%\server\default\deploy\vcs.ear\icmservice.war\WEB-INF\classes\com\radvision\icm\service\resource\McuResourceResult.class" "%BAK_HOME%\weather-hotfix"
echo Apply new file:McuResourceResult.class
Copy "%SRC_HOME%\weather-hotfix\McuResourceResult.class" "%DST_HOME%\server\default\deploy\vcs.ear\icmservice.war\WEB-INF\classes\com\radvision\icm\service\resource"
if EXIST "%DST_HOME%\bin" goto EXIST_BIN
mkdir "%DST_HOME%\bin"
:EXIST_BIN
echo     D%date%T%time% Hotfix: 5.7.0.0.1004 : add API for weather project   >> "%DST_HOME%\bin\ReleaseRecord.txt"
echo Hotfix has been applied successfully, please restart iVIEW service to take effect.
goto END
:END_FOR_DST_HOME_NOT_BE_FOUND
echo Can't find the target directory - '%DST_HOME', please modify DST_HOME variable to correct path.
:End
pause
