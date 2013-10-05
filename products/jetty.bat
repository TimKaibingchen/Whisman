@ECHO OFF

set JETTYRUN=jetty:run
set USEYUI=-Dpom.use.yui.minified=false

for %%A in (%*) do (

if "%%A" == "debug" set MAVEN_OPTS=-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=n
if "%%A" == "minjs" set JETTYRUN=jetty:run-war
if "%%A" == "noyui" set USEYUI=-Dpom.use.yui.minified=false
if "%%A" == "yui" set USEYUI=-Dpom.use.yui.minified=false
)

mvn  %JETTYRUN% %USEYUI%  -Dmaven.test.skip=true
