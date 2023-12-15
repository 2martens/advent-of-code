package de.twomartens.adventofcode.day15

class HashAlgorithm {
    fun calculateSumOfSegmentHashes(segments: List<String>): Int {
        return segments.sumOf { calculateHash(it) }
    }

    fun calculateHash(input: String): Int {
        var currentValue = 0

        for (char in input.chars()) {
            currentValue += char
            currentValue *= 17
            currentValue %= 256
        }

        return currentValue
    }
}