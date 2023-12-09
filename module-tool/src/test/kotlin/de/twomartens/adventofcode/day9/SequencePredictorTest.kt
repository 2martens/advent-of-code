package de.twomartens.adventofcode.day9

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SequencePredictorTest {
    @ParameterizedTest
    @MethodSource(value = ["predictionSequences"])
    fun shouldCalculateNextItem(sequence: List<Int>, expectedValue: Int) {
        val predictor = SequencePredictor()

        val nextValue = predictor.predictNextValue(sequence)

        Assertions.assertThat(nextValue).isEqualTo(expectedValue)
    }

    @ParameterizedTest
    @MethodSource(value = ["differenceLists"])
    fun shouldCalculateListOfDifferences(sequence: List<Int>, expected: List<Int>) {
        val predictor = SequencePredictor()

        val differenceList = predictor.calculateListOfDifferences(sequence)

        Assertions.assertThat(differenceList).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        private fun predictionSequences(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(listOf(0, 3, 6, 9, 12, 15), 18),
                    Arguments.of(listOf(1, 3, 6, 10, 15, 21), 28),
                    Arguments.of(listOf(10, 13, 16, 21, 30, 45), 68),
            )
        }

        @JvmStatic
        private fun differenceLists(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(listOf(0, 3, 6, 9, 12, 15), listOf(3, 3, 3, 3, 3)),
                    Arguments.of(listOf(1, 3, 6, 10, 15, 21), listOf(2, 3, 4, 5, 6)),
                    Arguments.of(listOf(10, 13, 16, 21, 30, 45), listOf(3, 3, 5, 9, 15)),
                    Arguments.of(listOf(3, 3, 3, 3, 3), listOf(0, 0, 0, 0)),
                    Arguments.of(listOf(2, 3, 4, 5, 6), listOf(1, 1, 1, 1)),
                    Arguments.of(listOf(3, 3, 5, 9, 15), listOf(0, 2, 4, 6)),
                    Arguments.of(listOf(1, 1, 1, 1), listOf(0, 0, 0)),
                    Arguments.of(listOf(0, 2, 4, 6), listOf(2, 2, 2)),
                    Arguments.of(listOf(2, 2, 2), listOf(0, 0)),
            )
        }
    }
}