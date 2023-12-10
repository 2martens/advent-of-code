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

        val farthestDistance = walker.findFurthestDistance(graph)

        Assertions.assertThat(farthestDistance).isEqualTo(distance)
    }

    @ParameterizedTest
    @MethodSource(value = ["getContainingGraphs"])
    fun shouldFindContainedNodes(graph: Graph, expectedContainedNodes: Int) {
        val walker = GraphWalker()

        val containedNodes = walker.findNumberOfContainedNodes(graph)

        Assertions.assertThat(containedNodes).isEqualTo(expectedContainedNodes)
    }


    companion object {
        @JvmStatic
        private fun getGraphs(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(buildSimpleGraph(), 4),
                    Arguments.of(buildComplexGraph(), 8)
            )
        }

        @JvmStatic
        private fun getContainingGraphs(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(buildTrivialContainingGraph(), 1),
                    Arguments.of(buildSimpleContainingGraph(), 4),
                    Arguments.of(buildComplexContainingGraph(), 8),
                    Arguments.of(buildComplexJunkContainingGraph(), 10)
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

        private fun buildTrivialContainingGraph() = Graph.of("trivial", listOf(
                "FS7",
                "|.|",
                "L-J",
        ))

        private fun buildSimpleContainingGraph() = Graph.of("simple", listOf(
                "...........",
                ".S-------7.",
                ".|F-----7|.",
                ".||.....||.",
                ".||.....||.",
                ".|L-7.F-J|.",
                ".|..|.|..|.",
                ".L--J.L--J.",
                "..........."
        ))

        private fun buildComplexContainingGraph() = Graph.of("complex", listOf(
                ".F----7F7F7F7F-7....",
                ".|F--7||||||||FJ....",
                ".||.FJ||||||||L7....",
                "FJL7L7LJLJ||LJ.L-7..",
                "L--J.L7...LJS7F-7L7.",
                "....F-J..F7FJ|L7L7L7",
                "....L7.F7||L7|.L7L7|",
                ".....|FJLJ|FJ|F7|.LJ",
                "....FJL-7.||.||||...",
                "....L---J.LJ.LJLJ..."
        ))

        private fun buildComplexJunkContainingGraph() = Graph.of("complexWithJunk", listOf(
                "FF7FSF7F7F7F7F7F---7",
                "L|LJ||||||||||||F--J",
                "FL-7LJLJ||||||LJL-77",
                "F--JF--7||LJLJ7F7FJ-",
                "L---JF-JLJ.||-FJLJJ7",
                "|F|F-JF---7F7-L7L|7|",
                "|FFJF7L7F-JF7|JL---7",
                "7-L-JL7||F7|L7F-7F7|",
                "L.L7LFJ|||||FJL7||LJ",
                "L7JLJL-JLJLJL--JLJ.L",
        ))
    }
}