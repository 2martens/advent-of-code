package de.twomartens.adventofcode.day16.graph

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GraphWalkerTest {
    @ParameterizedTest
    @MethodSource(value = ["getGraphAndTiles"])
    fun shouldEnergize(graph: Graph, energizedTiles: Int) {
        val walker = GraphWalker()
        val startPosition = ActiveNode(0, 0, Direction.RIGHT)

        val modifiedGraph = walker.energizeGraph(graph, startPosition)
        val result = modifiedGraph.countEnergizedTiles()

        Assertions.assertThat(result).isEqualTo(energizedTiles)
    }

    @ParameterizedTest
    @MethodSource(value = ["getGraphAndTiles"])
    fun shouldNotModifyInputGraph(graph: Graph, energizedTiles: Int) {
        val walker = GraphWalker()
        val startPosition = ActiveNode(0, 0, Direction.RIGHT)

        walker.energizeGraph(graph, startPosition)
        val result = graph.countEnergizedTiles()

        Assertions.assertThat(result).isZero()
    }

    companion object {
        @JvmStatic
        fun getGraphAndTiles(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(Graph.of(listOf(
                            ".|...\\....",
                            "|.-.\\.....",
                            ".....|-...",
                            "........|.",
                            "..........",
                            ".........\\",
                            "..../.\\\\..",
                            ".-.-/..|..",
                            ".|....-|.\\",
                            "..//.|....",
                    )), 46)
            )
        }
    }
}