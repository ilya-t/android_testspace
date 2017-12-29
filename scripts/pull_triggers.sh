#!/usr/bin/env sh
CODE=0

loop() {
    read -p "Input key number to send or empty to use last(KEYCODE_${CODE}): " INPUT
    if [ "$INPUT" != "" ] ; then
        CODE=${INPUT}
    fi
    eval "adb shell input keyevent KEYCODE_"${CODE}
    echo Done!
    loop
}

loop
