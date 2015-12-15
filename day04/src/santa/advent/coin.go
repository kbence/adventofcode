package main

import (
    "fmt"
    "crypto/md5"
)

func coin(secret string, x int) string {
    return fmt.Sprintf("%s%d", secret, x)
}

func isValidCoin(coinStr string) (bool, int, string) {
    x := 0
    hash := fmt.Sprintf("%x", md5.Sum([]byte(coinStr)))
    isValid := true

    for ; isValid && x < 5; x++ {
        if hash[x] != '0' {
            isValid = false;
            break;
        }
    }

    return isValid, x, hash
}

func findAdventCoin(secret string) (string, string) {
    x := 0
    match, matches := 0, 0
    isValid := false
    hash := ""

    for ; !isValid; x++ {
        coinStr := coin(secret, x)
        isValid, match, hash = isValidCoin(coinStr)

        if match > matches {
            matches = match
        }

        if x % 123 == 0 {
            fmt.Printf("%d (max matches so far: %d)\r", x, matches)
        }

        if isValid {
            return coinStr, hash
        }
    }

    return "", ""
}

func main() {
    secret := "yzbqklnj"
    coin, hash := findAdventCoin(secret)

    fmt.Printf("\nAdventCoin: %s (hash: %s)\n", coin, hash)
}
