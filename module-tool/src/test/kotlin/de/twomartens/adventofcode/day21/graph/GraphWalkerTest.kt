package de.twomartens.adventofcode.day21.graph

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GraphWalkerTest {
    @ParameterizedTest
    @MethodSource(value = ["getGraphStepsAndReachableNodes"])
    fun shouldCalculateReachableNodes(graph: Graph, numberOfSteps: Int, expectedReachableNodes: Int) {
        val walker = GraphWalker()

        val reachableNodes = walker.findNumberOfReachableNodes(graph, numberOfSteps)

        Assertions.assertThat(reachableNodes).isEqualTo(expectedReachableNodes)
    }

    @ParameterizedTest
    @MethodSource(value = ["getGraphStepsAndReachableNodesInfinity"])
    fun shouldCalculateReachableNodesToInfinity(graph: Graph, numberOfSteps: Long, expectedReachableNodes: Long) {
        val walker = GraphWalker()

        val reachableNodes = walker.findNumberOfReachableNodesToInfinity(graph, numberOfSteps)

        Assertions.assertThat(reachableNodes).isEqualTo(expectedReachableNodes)
    }

    companion object {
        @JvmStatic
        private fun getGraphStepsAndReachableNodes(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(
                            Graph.of(listOf(
                                    "...........",
                                    ".....###.#.",
                                    ".###.##..#.",
                                    "..#.#...#..",
                                    "....#.#....",
                                    ".##..S####.",
                                    ".##..#...#.",
                                    ".......##..",
                                    ".##.#.####.",
                                    ".##..##.##.",
                                    "..........."
                            )), 6, 16
                    )
            )
        }

        @JvmStatic
        private fun getGraphStepsAndReachableNodesInfinity(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(
                            Graph.of(listOf(
                                    "...........",
                                    ".....###.#.",
                                    ".###.##..#.",
                                    "..#.#...#..",
                                    "....#.#....",
                                    ".##..S####.",
                                    ".##..#...#.",
                                    ".......##..",
                                    ".##.#.####.",
                                    ".##..##.##.",
                                    "..........."
                            )), 10L, 50L
                    ),
                    Arguments.of(
                            Graph.of(listOf(
                                    "...........",
                                    ".....###.#.",
                                    ".###.##..#.",
                                    "..#.#...#..",
                                    "....#.#....",
                                    ".##..S####.",
                                    ".##..#...#.",
                                    ".......##..",
                                    ".##.#.####.",
                                    ".##..##.##.",
                                    "..........."
                            )), 50L, 1594L
                    ),
                    Arguments.of(
                            Graph.of(listOf(
                                    "...........",
                                    ".....###.#.",
                                    ".###.##..#.",
                                    "..#.#...#..",
                                    "....#.#....",
                                    ".##..S####.",
                                    ".##..#...#.",
                                    ".......##..",
                                    ".##.#.####.",
                                    ".##..##.##.",
                                    "..........."
                            )), 100L, 6536L
                    ),
                    Arguments.of(
                            Graph.of(listOf(
                                    "...........",
                                    ".....###.#.",
                                    ".###.##..#.",
                                    "..#.#...#..",
                                    "....#.#....",
                                    ".##..S####.",
                                    ".##..#...#.",
                                    ".......##..",
                                    ".##.#.####.",
                                    ".##..##.##.",
                                    "..........."
                            )), 500L, 167004L
                    )
            )
        }
    }
}