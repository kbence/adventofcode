#!/usr/bin/awk -f

BEGIN {
    codeBytes = 0
    stringBytes = 0
}

{
    codeBytes += length($0)

    for (c = 0; c < length($0); c++) {
        switch (substr($0,c,1)) {
            case "\\":
                c++;
                switch (substr($0,c,1)) {
                    case "x":
                        c += 2;
                        stringBytes++;
                        break;
                    case "\"":
                    case "\\":
                        stringBytes++;
                        break;
                }
            case "\"": break;
            default:
                stringBytes++;
        }
    }
}

END {
    printf("%d (full length) - %d (string bytes) = %d\n",
           codeBytes, stringBytes, codeBytes - stringBytes)
}
