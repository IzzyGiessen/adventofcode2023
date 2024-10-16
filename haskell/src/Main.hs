import System.IO
import Control.Monad

import Day02

main :: IO ()
main = do
    file <- openFile "src/input/day2" ReadMode
    lns <- hGetContents file 
    print $ solve $ lines lns

    hClose file