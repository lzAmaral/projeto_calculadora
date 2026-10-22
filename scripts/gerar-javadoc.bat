@echo off
setlocal
cd /d "%~dp0.."
javadoc -quiet -private -Xdoclint:all -Werror -notimestamp -encoding UTF-8 -docencoding UTF-8 -charset UTF-8 -author -version -windowtitle "FinancApp | Documentacao JavaDoc" -doctitle "FinancApp - Calculadora" -d docs -sourcepath src/main/java -subpackages calculadora
exit /b %errorlevel%
