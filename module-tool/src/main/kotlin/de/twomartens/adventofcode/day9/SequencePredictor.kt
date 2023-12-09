package de.twomartens.adventofcode.day9

class SequencePredictor {
    fun predictNextValue(sequence: List<Int>): Int {
        val differenceLists = mutableListOf(sequence)
        do {
            val differenceList = calculateListOfDifferences(differenceLists.last())
            differenceLists.add(differenceList)
        } while (differenceList.any { it != 0 })
        var lastValue = 0
        for (i in differenceLists.lastIndex - 1 downTo 0) {
            lastValue += differenceLists[i].last()
        }
        return lastValue
    }

    fun calculateListOfDifferences(sequence: List<Int>): List<Int> {
        return sequence.windowed(2, 1) {
            it[1] - it[0]
        }
    }

    fun calculateSumOfPredictedValues(lines: List<List<Int>>): Int {
        return lines.sumOf {
            predictNextValue(it)
        }
    }
}