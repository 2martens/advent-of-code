package de.twomartens.adventofcode.day11.graph

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GraphWalkerTest {
    @ParameterizedTest
    @MethodSource(value = ["getGraph"])
    fun shouldSumAllDistancesBetweenGalaxies(graph: Graph, expectedSum: Int) {
        val walker = GraphWalker()

        val sum = walker.sumAllDistancesBetweenGalaxies(graph)

        Assertions.assertThat(sum).isEqualTo(expectedSum)
    }

    @ParameterizedTest
    @MethodSource(value = ["getIndices"])
    fun shouldCalculateDistance(firstIndex: Pair<Int, Int>, secondIndex: Pair<Int, Int>, expectedDistance: Int) {
        val walker = GraphWalker()

        val distance = walker.findDistanceBetweenGalaxies(firstIndex, secondIndex)

        Assertions.assertThat(distance).isEqualTo(expectedDistance)
    }

    companion object {
        @JvmStatic
        private fun getGraph(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(Graph.of("simple", listOf(
                            "...#......",
                            ".......#..",
                            "#.........",
                            "..........",
                            "......#...",
                            ".#........",
                            ".........#",
                            "..........",
                            ".......#..",
                            "#...#.....",
                    ), 2), 374),
                    Arguments.of(Graph.of("simple", listOf(
                            "...#......",
                            ".......#..",
                            "#.........",
                            "..........",
                            "......#...",
                            ".#........",
                            ".........#",
                            "..........",
                            ".......#..",
                            "#...#.....",
                    ), 10), 1030),
                    Arguments.of(Graph.of("simple", listOf(
                            "...#......",
                            ".......#..",
                            "#.........",
                            "..........",
                            "......#...",
                            ".#........",
                            ".........#",
                            "..........",
                            ".......#..",
                            "#...#.....",
                    ), 100), 8410),
            )
        }

        @JvmStatic
        private fun getIndices(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(Pair(0, 0), Pair(0, 1), 1),
                    Arguments.of(Pair(0, 0), Pair(5, 1), 6),
                    Arguments.of(Pair(0, 0), Pair(5, 7), 12),
            )
        }
    }
}