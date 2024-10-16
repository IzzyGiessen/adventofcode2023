module Day01 where

import Data.Char

solve :: [String] -> Int
solve = sum . map ((\xs -> read [head xs, last xs]) . findNumbers)

findNumbers :: String -> String
findNumbers [] = []
findNumbers ('o':'n':'e':xs) = '1' : findNumbers ('e':xs)
findNumbers ('t':'w':'o':xs) = '2' : findNumbers ('o':xs)
findNumbers ('t':'h':'r':'e':'e':xs) = '3' : findNumbers ('e':xs)
findNumbers ('f':'o':'u':'r':xs) = '4' : findNumbers xs
findNumbers ('f':'i':'v':'e':xs) = '5' : findNumbers ('e':xs)
findNumbers ('s':'i':'x':xs) = '6' : findNumbers xs
findNumbers ('s':'e':'v':'e':'n':xs) = '7' : findNumbers ('n':xs)
findNumbers ('e':'i':'g':'h':'t':xs) = '8' : findNumbers ('t':xs)
findNumbers ('n':'i':'n':'e':xs) = '9' : findNumbers ('e':xs)
findNumbers (x:xs) = if isDigit x then x : findNumbers xs else findNumbers xs