
loadFile :: IO String
loadFile =
    readFile "./data.txt"

main :: IO ()
main =
    putStrLn "Hello, World!"
