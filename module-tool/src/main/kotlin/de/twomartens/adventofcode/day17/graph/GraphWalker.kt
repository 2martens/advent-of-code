package de.twomartens.adventofcode.day17.graph

import mu.KotlinLogging
import java.util.*

data class VisitedNode(val row: Int, val column: Int, var direction: Direction?, var directionCount: Int = 0)
data class PathNode(val visitedNode: VisitedNode, val distanceToStart: Int) : Comparable<PathNode> {
    override fun compareTo(other: PathNode): Int {
        return distanceToStart - other.distanceToStart
    }
}

class GraphWalker {

    fun findMinimalHeatLoss(graph: Graph, minPerDirection: Int, maxPerDirection: Int): Int {
        log.debug { "Find minimal heat loss" }
        val lastColumnIndex = graph.rows[0].lastIndex
        val lastRowIndex = graph.rows.lastIndex

        val visitedNodes: MutableMap<VisitedNode, Boolean> = HashMap()
        val firstVisitedNode = VisitedNode(0, 0, null, 0)
        val queue: Queue<PathNode> = PriorityQueue()
        queue.add(PathNode(firstVisitedNode, 0))
        var lastNode: PathNode? = null

        while (queue.isNotEmpty()) {
            lastNode = queue.poll()
            if (lastNode.visitedNode.row == lastRowIndex
                    && lastNode.visitedNode.column == lastColumnIndex
                    && lastNode.visitedNode.directionCount >= minPerDirection
                    && lastNode.visitedNode.directionCount <= maxPerDirection) {
                break
            }

            log.debug { "Currently at tile $lastNode with heat loss ${lastNode.distanceToStart}" }

            val neighbours = findNeighbours(lastNode, minPerDirection)
            val validNeighbours = neighbours
                    .filter {
                        isWithinRowBounds(it, graph) && isWithinColumnBounds(it, graph)
                    }
                    .filter { !visitedNodes.containsKey(it) }
                    .filter { it.directionCount <= maxPerDirection }

            for (neighbour in validNeighbours) {
                val neighbourTile = graph.rows[neighbour.row][neighbour.column]
                val newDistance = lastNode.distanceToStart + neighbourTile.heatLossOnEnter
                val pathNode = PathNode(neighbour, newDistance)
                queue.add(pathNode)
                visitedNodes[neighbour] = true
            }
        }

        return lastNode?.distanceToStart ?: 0
    }

    fun findNeighbours(currentNode: PathNode, minPerDirection: Int): List<VisitedNode> {
        val direction = currentNode.visitedNode.direction
        val visitedNode = currentNode.visitedNode
        val sameDirectionCount = currentNode.visitedNode.directionCount
        val belowMinDirectionCount = sameDirectionCount < minPerDirection
        val leftNeighbour = findNeighbour(visitedNode, Direction.LEFT)
        val rightNeighbour = findNeighbour(visitedNode, Direction.RIGHT)
        val downNeighbour = findNeighbour(visitedNode, Direction.DOWN)
        val upNeighbour = findNeighbour(visitedNode, Direction.UP)

        return when (direction) {
            Direction.LEFT -> if (belowMinDirectionCount) listOf(leftNeighbour) else listOf(
                    downNeighbour, leftNeighbour, rightNeighbour)

            Direction.UP -> if (belowMinDirectionCount) listOf(upNeighbour) else listOf(
                    leftNeighbour, upNeighbour, rightNeighbour)

            Direction.RIGHT -> if (belowMinDirectionCount) listOf(rightNeighbour) else listOf(
                    upNeighbour, rightNeighbour, downNeighbour)

            Direction.DOWN -> if (belowMinDirectionCount) listOf(downNeighbour) else listOf(
                    rightNeighbour, downNeighbour, leftNeighbour)

            null -> listOf(rightNeighbour,
                    downNeighbour,
                    leftNeighbour,
                    upNeighbour)
        }
    }

    fun findNeighbour(currentNode: VisitedNode, direction: Direction): VisitedNode {
        val sameDirectionCount = if (currentNode.direction == direction) currentNode.directionCount + 1 else 1

        return when (direction) {
            Direction.LEFT -> VisitedNode(currentNode.row, currentNode.column - 1, direction, sameDirectionCount)
            Direction.UP -> VisitedNode(currentNode.row - 1, currentNode.column, direction, sameDirectionCount)
            Direction.RIGHT -> VisitedNode(currentNode.row, currentNode.column + 1, direction, sameDirectionCount)
            Direction.DOWN -> VisitedNode(currentNode.row + 1, currentNode.column, direction, sameDirectionCount)
        }
    }

    private fun isWithinRowBounds(
            it: VisitedNode,
            graph: Graph
    ) = it.row >= 0 && it.row <= graph.rows.lastIndex

    private fun isWithinColumnBounds(
            it: VisitedNode,
            graph: Graph
    ) = it.column >= 0 && it.column <= graph.rows[0].lastIndex

    companion object {
        private val log = KotlinLogging.logger {}
    }
}