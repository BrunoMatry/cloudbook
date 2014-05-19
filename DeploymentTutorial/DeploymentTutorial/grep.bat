@echo off
if "%1" == "" goto :error
set jarpath="dist/AnotherGrep.jar"
set pattern="%1"
shift
  :loop
    if "%1" == "" goto :allprocessed
    set files=%1 %2 %3 %4 %5 %6 %7 %8 %9
    java -jar %jarpath% %pattern% %files%
    for %%i in (0 1 2 3 4 5 6 7 8) do shift
    goto :loop

  :allprocessed
    goto :end

:error
    echo grep: This program is supposed to be run from console.
    echo grep: usage: grep [pattern] [filelist]

:end
    pause		