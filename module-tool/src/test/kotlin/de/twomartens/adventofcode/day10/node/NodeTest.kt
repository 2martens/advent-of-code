package de.twomartens.adventofcode.day10.node

import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream


abstract class NodeTest {

    companion object {
        @JvmStatic
        private fun getNeighbours(): Stream<Arguments> {
            // combine all node types with all indices
            val neighbours = getNodeTypes().flatMap { nodeType ->
                getIndices().map { index ->
                    Arguments.of(nodeType, index)
                }
            }.stream()

            return neighbours
        }

        @JvmStatic
        private fun getDiagonalNeighbours(): Stream<Arguments> {
            // combine all node types with all indices
            val neighbours = getNodeTypes().flatMap { nodeType ->
                getDiagonalIndices().map { index ->
                    Arguments.of(nodeType, index)
                }
            }.stream()

            return neighbours
        }

        @JvmStatic
        private fun getVerticalNeighbours(): Stream<Arguments> {
            // combine all node types with all indices
            val neighbours = getNodeTypes().flatMap { nodeType ->
                getVerticalIndices().map { index ->
                    Arguments.of(nodeType, index)
                }
            }.stream()

            return neighbours
        }

        @JvmStatic
        private fun getHorizontalNeighbours(): Stream<Arguments> {
            // combine all node types with all indices
            val neighbours = getNodeTypes().flatMap { nodeType ->
                getHorizontalIndices().map { index ->
                    Arguments.of(nodeType, index)
                }
            }.stream()

            return neighbours
        }

        @JvmStatic
        private fun getNodeTypes(): List<NodeType> {
            return listOf(
                    NodeType.START_NODE,
                    NodeType.NORTH_WEST,
                    NodeType.NORTH_EAST,
                    NodeType.SOUTH_WEST,
                    NodeType.SOUTH_EAST,
                    NodeType.HORIZONTAL,
                    NodeType.VERTICAL
            )
        }

        @JvmStatic
        private fun getIndices(): List<Pair<Int, Int>> {
            return listOf(
                    Pair(0, 1),
                    Pair(0, 2),
                    Pair(1, 2),
                    Pair(2, 2),
                    Pair(2, 1),
                    Pair(2, 0),
                    Pair(1, 0),
                    Pair(0, 0),
            )
        }

        @JvmStatic
        private fun getDiagonalIndices(): List<Pair<Int, Int>> {
            return listOf(
                    Pair(0, 2),
                    Pair(2, 2),
                    Pair(2, 0),
                    Pair(0, 0),
            )
        }

        @JvmStatic
        private fun getVerticalIndices(): List<Pair<Int, Int>> {
            return listOf(
                    Pair(0, 1),
                    Pair(2, 1),
            )
        }

        @JvmStatic
        private fun getHorizontalIndices(): List<Pair<Int, Int>> {
            return listOf(
                    Pair(1, 2),
                    Pair(1, 0),
            )
        }
    }
}