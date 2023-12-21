package de.twomartens.adventofcode.day21.graph

import mu.KotlinLogging
import java.util.*

class GraphWalker {

    fun findNumberOfReachableNodesToInfinity(graph: Graph, numberOfSteps: Long): Long {

        val queue: Queue<Pair<Index, Long>> = PriorityQueue(Comparator.comparing { it.second })
        val reachedNodes: MutableMap<Index, Long> = mutableMapOf()
        queue.add(Pair(graph.startPosition, 0))

        while (queue.isNotEmpty()) {
            val currentIndex = queue.poll()
            reachedNodes[currentIndex.first] = currentIndex.second

            val neighbours = findNeighbours(currentIndex.first)
            val nextNumberOfSteps = currentIndex.second + 1
            if (nextNumberOfSteps <= numberOfSteps) {
                val allowedNeighbours = neighbours
                        .asSequence()
                        .map { Pair(it, modifyIndexToBeInBounds(it, graph)) }
                        .filter { graph.rows[it.second.row.toInt()][it.second.column.toInt()] }
                        .filterNot { reachedNodes.containsKey(it.first) && reachedNodes[it.first] == nextNumberOfSteps }
                        .map { Pair(it.first, nextNumberOfSteps) }
                        .filterNot { queue.contains(it) }
                        .toList()

                queue.addAll(allowedNeighbours)
            }
        }

        return reachedNodes.filter { it.value == numberOfSteps }.size.toLong()
    }

    private fun modifyIndexToBeInBounds(index: Index, graph: Graph): Index {
        var row = index.row

        while (row < 0) {
            row += graph.rows.size
        }
        while (row >= graph.rows.size) {
            row -= graph.rows.size
        }

        var column = index.column
        val numberOfColumns = graph.rows[0].size

        while (column < 0) {
            column += numberOfColumns
        }
        while (column >= numberOfColumns) {
            column -= numberOfColumns
        }

        return Index(row, column)
    }


    fun findNumberOfReachableNodes(graph: Graph, numberOfSteps: Int): Int {

        val queue: Queue<Pair<Index, Int>> = PriorityQueue(Comparator.comparing { it.second })
        val reachedNodes: MutableMap<Index, Int> = mutableMapOf()
        queue.add(Pair(graph.startPosition, 0))

        while (queue.isNotEmpty()) {
            val currentIndex = queue.poll()
            reachedNodes[currentIndex.first] = currentIndex.second

            val neighbours = findNeighbours(currentIndex.first)
            val nextNumberOfSteps = currentIndex.second + 1
            if (nextNumberOfSteps <= numberOfSteps) {
                val allowedNeighbours = neighbours
                        .asSequence()
                        .filter { isWithinRowBounds(it, graph) && isWithinColumnBounds(it, graph) }
                        .filter { graph.rows[it.row.toInt()][it.column.toInt()] }
                        .filterNot { reachedNodes.containsKey(it) && reachedNodes[it] == nextNumberOfSteps }
                        .map { Pair(it, nextNumberOfSteps) }
                        .filterNot { queue.contains(it) }
                        .toList()

                queue.addAll(allowedNeighbours)
            }
        }

        return reachedNodes.filter { it.value == numberOfSteps }.size
    }

    fun findNeighbours(currentIndex: Index): List<Index> {
        val leftNeighbour = findNeighbour(currentIndex, Direction.LEFT)
        val rightNeighbour = findNeighbour(currentIndex, Direction.RIGHT)
        val downNeighbour = findNeighbour(currentIndex, Direction.DOWN)
        val upNeighbour = findNeighbour(currentIndex, Direction.UP)

        return listOf(rightNeighbour,
                downNeighbour,
                leftNeighbour,
                upNeighbour)
    }


    fun findNeighbour(currentIndex: Index, direction: Direction): Index {

        return when (direction) {
            Direction.LEFT -> Index(currentIndex.row, currentIndex.column - 1)
            Direction.UP -> Index(currentIndex.row - 1, currentIndex.column)
            Direction.RIGHT -> Index(currentIndex.row, currentIndex.column + 1)
            Direction.DOWN -> Index(currentIndex.row + 1, currentIndex.column)
        }
    }

    private fun isWithinRowBounds(
            it: Index,
            graph: Graph
    ) = it.row >= 0 && it.row <= graph.rows.lastIndex

    private fun isWithinColumnBounds(
            it: Index,
            graph: Graph
    ) = it.column >= 0 && it.column <= graph.rows[0].lastIndex

    companion object {
        private val log = KotlinLogging.logger {}
    }
}