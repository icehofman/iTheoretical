@echo off
set currentPath=%cd%
set msTestLocation="C:\Program Files (x86)\Microsoft Visual Studio 14.0\Common7\IDE\MSTest.exe"
set dotNetLocation="C:\Windows\Microsoft.NET\Framework64\v4.0.30319\MsBuild.exe"

set solutionPath="%currentPath%\Ilegra.sln"
set unitTestDllLocation="%currentPath%\Ilegra.Tests\bin\release\Ilegra.Tests.dll"

echo %solutionPath%
echo %msTestLocation%

%dotNetLocation% %solutionPath% /t:Build /p:Configuration=Release /p:TargetFramework=v4.5

%msTestLocation% /testcontainer:%unitTestDllLocation%
