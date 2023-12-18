package de.twomartens.adventofcode.day17.graph

import mu.KotlinLogging
import java.util.*
import kotlin.collections.HashMap

data class VisitedNode(val row: Int, val column: Int, var direction: Direction?, var directionCount: Int = 0)
data class PathNode(val visitedNode: VisitedNode, val distanceToStart: Int) : Comparable<PathNode> {
    override fun compareTo(other: PathNode): Int {
        return distanceToStart - other.distanceToStart
    }
}

class GraphWalker {

    fun findMinimalHeatLoss(graph: Graph): Int {
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
            if (lastNode.visitedNode.row == lastRowIndex && lastNode.visitedNode.column == lastColumnIndex) {
                break
            }

            log.debug { "Currently at tile $lastNode with heat loss ${lastNode.distanceToStart}" }

            val neighbours = findNeighbours(lastNode)
            val validNeighbours = neighbours
                    .filter {
                        isWithinRowBounds(it, graph) && isWithinColumnBounds(it, graph)
                    }
                    .filter { !visitedNodes.containsKey(it) }
                    .filter { it.directionCount <= 3 }

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

    fun findNeighbours(currentNode: PathNode): List<VisitedNode> {
        val direction = currentNode.visitedNode.direction
        val visitedNode = currentNode.visitedNode
        return when (direction) {
            Direction.LEFT -> listOf(findNeighbour(visitedNode, Direction.DOWN),
                    findNeighbour(visitedNode, Direction.LEFT),
                    findNeighbour(visitedNode, Direction.RIGHT))

            Direction.UP -> listOf(findNeighbour(visitedNode, Direction.LEFT),
                    findNeighbour(visitedNode, Direction.UP),
                    findNeighbour(visitedNode, Direction.RIGHT))

            Direction.RIGHT -> listOf(findNeighbour(visitedNode, Direction.UP),
                    findNeighbour(visitedNode, Direction.RIGHT),
                    findNeighbour(visitedNode, Direction.DOWN))

            Direction.DOWN -> listOf(findNeighbour(visitedNode, Direction.RIGHT),
                    findNeighbour(visitedNode, Direction.DOWN),
                    findNeighbour(visitedNode, Direction.LEFT))

            null -> listOf(findNeighbour(visitedNode, Direction.RIGHT),
                    findNeighbour(visitedNode, Direction.DOWN),
                    findNeighbour(visitedNode, Direction.LEFT),
                    findNeighbour(visitedNode, Direction.UP))
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