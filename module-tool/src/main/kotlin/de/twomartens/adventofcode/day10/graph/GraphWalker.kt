package de.twomartens.adventofcode.day10.graph

import de.twomartens.adventofcode.day10.node.Node

class GraphWalker {
    fun findFurthestDistance(graph: Graph): Int {
        val startNode = findStartNode(graph)
        val (_, distance) = findLoop(startNode, graph)

        return distance
    }

    private fun findStartNode(graph: Graph): Node {
        val startPosition = graph.startPosition
        val startNode = graph.rows[startPosition.first][startPosition.second]
        return startNode
    }

    private fun findLoop(startNode: Node, graph: Graph): Pair<Set<Node>, Int> {
        val visitedNodes = mutableSetOf<Node>()
        var furthestDistance = 0

        val queue = initializeQueue(startNode)

        while (queue.isNotEmpty()) {
            val (currentNode, distance) = queue.removeFirst()
            visitedNodes.add(currentNode)

            if (distance > furthestDistance) {
                furthestDistance = distance
            }

            val neighbours = findNeighbours(currentNode, graph)

            for (neighbour in neighbours) {
                if (isReachable(visitedNodes, currentNode, neighbour)) {
                    queue.add(neighbour to distance + 1)
                }
            }
        }

        return Pair(visitedNodes, furthestDistance)
    }

    private fun initializeQueue(startNode: Node): ArrayDeque<Pair<Node, Int>> {
        val queue = ArrayDeque<Pair<Node, Int>>()
        queue.add(startNode to 0)
        return queue
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

    private fun isReachable(
            visitedNodes: MutableSet<Node>,
            currentNode: Node,
            neighbour: Node
    ) = (neighbour !in visitedNodes
            && currentNode.isConnectedTo(neighbour)
            && neighbour.isConnectedTo(currentNode))
}