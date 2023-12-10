package de.twomartens.adventofcode.day10.graph

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GraphWalkerTest {
    @ParameterizedTest
    @MethodSource(value = ["getGraphs"])
    fun shouldFindFarthestDistance(graph: Graph, distance: Int) {
        val walker = GraphWalker()

        val farthestDistance = walker.findFarthestDistance(graph)

        Assertions.assertThat(farthestDistance).isEqualTo(distance)
    }

    companion object {
        @JvmStatic
        private fun getGraphs(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(buildSimpleGraph(), 4),
                    Arguments.of(buildComplexGraph(), 8)
            )
        }

        private fun buildSimpleGraph() = Graph.of("simple", listOf(
                "-L|F7",
                "7S-7|",
                "L|7||",
                "-L-J|",
                "L|-JF"
        ))

        private fun buildComplexGraph() = Graph.of("complex", listOf(
                "7-F7-",
                ".FJ|7",
                "SJLL7",
                "|F--J",
                "LJ.LJ"))
    }
}