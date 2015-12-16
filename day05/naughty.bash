#!/bin/bash

FILENAME="$(cd "$(dirname $BASH_SOURCE[0])"; pwd)/data.txt"
NICEWORDS=0

num_vowels() {
    local vowels="$(echo -n "$1" | tr -d 'b-df-hj-np-tv-z')"
    echo ${#vowels}
}

contains_double() {
    local contains="$(echo -n "$1" | awk '{ l=substr($0,1,1); for(i=2; i<length($0)+1; i++) {c=substr($0,i,1); if (c==l) { print c }; l=c } }')"

    [[ -n $contains ]] || return 1
}

while read word; do
    case "$word" in
        *ab*|*cd*|*pq*|*xy*) continue ;;
    esac

    if [[ $(num_vowels "$word") -lt 3 ]]; then continue; fi

    if ! contains_double "$word"; then continue; fi

    NICEWORDS=$(($NICEWORDS+1))
done < "$FILENAME"

echo "Number of nice words: $NICEWORDS"
