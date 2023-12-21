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
    }
}