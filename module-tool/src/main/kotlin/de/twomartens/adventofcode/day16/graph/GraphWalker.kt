package de.twomartens.adventofcode.day16.graph

import mu.KotlinLogging
import java.util.*

data class ActiveNode(val row: Int, val column: Int, val direction: Direction)

class GraphWalker {

    fun energizeFromAllStartPositions(graph: Graph): List<Graph> {
        val resultGraphs: MutableList<Graph> = mutableListOf()

        val firstRow = graph.rows.first()
        val firstRowIndex = 0
        val lastRow = graph.rows.last()
        val lastRowIndex = graph.rows.lastIndex
        val firstColumnIndex = 0
        val lastColumnIndex = graph.rows[0].lastIndex

        // add all start positions from top
        for (columnIndex in firstRow.indices) {
            val startPosition = ActiveNode(firstRowIndex, columnIndex, Direction.DOWN)
            resultGraphs.add(energizeGraph(graph, startPosition))
        }

        // add all start positions from bottom
        for (columnIndex in lastRow.indices) {
            val startPosition = ActiveNode(lastRowIndex, columnIndex, Direction.DOWN)
            resultGraphs.add(energizeGraph(graph, startPosition))
        }

        for (rowIndex in graph.rows.indices) {
            // add all start positions from left
            val startPositionLeft = ActiveNode(rowIndex, firstColumnIndex, Direction.RIGHT)
            resultGraphs.add(energizeGraph(graph, startPositionLeft))

            // add all start positions from right
            val startPositionRight = ActiveNode(rowIndex, lastColumnIndex, Direction.RIGHT)
            resultGraphs.add(energizeGraph(graph, startPositionRight))
        }

        return resultGraphs
    }

    fun energizeGraph(graph: Graph, startPosition: ActiveNode): Graph {
        log.info { "Energize graph with start position $startPosition" }
        val queue: Deque<ActiveNode> = ArrayDeque()
        queue.add(startPosition)
        val modifiedGraph = graph.deepCopy()

        while (queue.isNotEmpty()) {
            val currentNode = queue.pop()
            val currentRow = modifiedGraph.rows[currentNode.row]
            val currentColumn = currentRow[currentNode.column]

            if (currentColumn.isReachedFrom(currentNode.direction)) {
                continue
            }

            currentColumn.energizeFrom(currentNode.direction)
            val nextNodes = findNextNodes(currentColumn.operation, currentNode)
            nextNodes.filter {
                isWithinRowBounds(it, modifiedGraph) && isWithinColumnBounds(it, modifiedGraph)
            }.forEach { queue.add(it) }
        }

        return modifiedGraph
    }

    private fun isWithinRowBounds(
            it: ActiveNode,
            graph: Graph
    ) = it.row >= 0 && it.row <= graph.rows.lastIndex

    private fun isWithinColumnBounds(
            it: ActiveNode,
            graph: Graph
    ) = it.column >= 0 && it.column <= graph.rows[0].lastIndex

    fun findNextNodes(operation: Operation, currentNode: ActiveNode): List<ActiveNode> {
        val direction = currentNode.direction
        val verticalDirections = listOf(Direction.DOWN, Direction.UP)
        val horizontalDirections = listOf(Direction.LEFT, Direction.RIGHT)
        return when {
            operation == Operation.PASS_THROUGH -> listOf(adjustIndex(currentNode, direction))
            operation == Operation.REFLECT_SLASH
                    && direction == Direction.RIGHT -> listOf(adjustIndex(currentNode, Direction.UP))

            operation == Operation.REFLECT_SLASH
                    && direction == Direction.LEFT -> listOf(adjustIndex(currentNode, Direction.DOWN))

            operation == Operation.REFLECT_SLASH
                    && direction == Direction.UP -> listOf(adjustIndex(currentNode, Direction.RIGHT))

            operation == Operation.REFLECT_SLASH
                    && direction == Direction.DOWN -> listOf(adjustIndex(currentNode, Direction.LEFT))

            operation == Operation.REFLECT_BACKSLASH
                    && direction == Direction.RIGHT -> listOf(adjustIndex(currentNode, Direction.DOWN))

            operation == Operation.REFLECT_BACKSLASH
                    && direction == Direction.LEFT -> listOf(adjustIndex(currentNode, Direction.UP))

            operation == Operation.REFLECT_BACKSLASH
                    && direction == Direction.UP -> listOf(adjustIndex(currentNode, Direction.LEFT))

            operation == Operation.REFLECT_BACKSLASH
                    && direction == Direction.DOWN -> listOf(adjustIndex(currentNode, Direction.RIGHT))

            operation == Operation.SPLIT_VERTICAL
                    && direction in verticalDirections -> listOf(adjustIndex(currentNode, direction))

            operation == Operation.SPLIT_HORIZONTAL
                    && direction in horizontalDirections -> listOf(adjustIndex(currentNode, direction))

            operation == Operation.SPLIT_VERTICAL && direction in horizontalDirections -> listOf(
                    adjustIndex(currentNode, Direction.UP),
                    adjustIndex(currentNode, Direction.DOWN)
            )

            operation == Operation.SPLIT_HORIZONTAL && direction in verticalDirections -> listOf(
                    adjustIndex(currentNode, Direction.LEFT),
                    adjustIndex(currentNode, Direction.RIGHT)
            )

            else -> listOf()
        }
    }

    fun adjustIndex(currentNode: ActiveNode, direction: Direction): ActiveNode {
        return when (direction) {
            Direction.LEFT -> currentNode.copy(column = currentNode.column - 1, direction = direction)
            Direction.UP -> currentNode.copy(row = currentNode.row - 1, direction = direction)
            Direction.RIGHT -> currentNode.copy(column = currentNode.column + 1, direction = direction)
            Direction.DOWN -> currentNode.copy(row = currentNode.row + 1, direction = direction)
        }
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}