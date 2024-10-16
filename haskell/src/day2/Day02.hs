module Day02 where

import Data.Map (Map, unionWith, empty, fromList, elems)
import Data.List.Split (splitOn)

solve :: [String] -> Int
solve s = sum (map getProduct s)

getProduct :: String -> Int 
getProduct line = product $ elems maxCubes
    where
        games = concatMap (splitOn ", ") $ splitOn "; " $ tail $ dropWhile (/=':') line
        maxCubes = foldl (unionWith max) empty $ map getCubesMap games

getCubesMap :: String -> Map String Int
getCubesMap game = fromList [(color, count)]
    where
        color = last $ words game
        count = read $ last $ init $ words game