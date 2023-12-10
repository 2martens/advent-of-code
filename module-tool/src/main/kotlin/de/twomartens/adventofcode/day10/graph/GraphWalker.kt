package de.twomartens.adventofcode.day10.graph

import de.twomartens.adventofcode.day10.node.Node

class GraphWalker {
    fun findFarthestDistance(graph: Graph): Int {
        val startPosition = graph.startPosition
        val startNode = graph.rows[startPosition.first][startPosition.second]

        val visitedNodes = mutableSetOf<Node>()
        var farthestDistance = 0

        val queue = initializeQueue(startNode)

        while (queue.isNotEmpty()) {
            val (currentNode, distance) = queue.removeFirst()
            visitedNodes.add(currentNode)

            if (distance > farthestDistance) {
                farthestDistance = distance
            }

            val neighbours = findNeighbours(currentNode, graph)

            for (neighbour in neighbours) {
                if (neighbour !in visitedNodes
                        && currentNode.isConnectedTo(neighbour)
                        && neighbour.isConnectedTo(currentNode)) {
                    queue.add(neighbour to distance + 1)
                }
            }
        }

        return farthestDistance
    }

    private fun findNeighbours(
            currentNode: Node,
            graph: Graph
    ): List<Node> {
        val neighbours = listOf(
                currentNode.northIndex(),
                currentNode.eastIndex(),
                currentNode.southIndex(),
                currentNode.westIndex()
        ).mapNotNull { (row, col) ->
            if (row in graph.rows.indices && col in graph.rows[row].indices) {
                graph.rows[row][col]
            } else {
                null
            }
        }
        return neighbours
    }

    private fun initializeQueue(startNode: Node): ArrayDeque<Pair<Node, Int>> {
        val queue = ArrayDeque<Pair<Node, Int>>()
        queue.add(startNode to 0)
        return queue
    }
}