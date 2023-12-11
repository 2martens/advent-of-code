package de.twomartens.adventofcode.day10.graph

import de.twomartens.adventofcode.day10.node.*
import mu.KotlinLogging

class GraphWalker {
    fun findFurthestDistance(graph: Graph): Int {
        val startNode = findStartNode(graph)
        val (_, _, distance) = findLoop(startNode, graph)

        return distance
    }

    fun findNumberOfContainedNodes(graph: Graph): Int {
        val startNode = findStartNode(graph)
        val (visitedNodes, newStartNode, _) = findLoop(startNode, graph)
        visitedNodes.filterIsInstance<AdjustableColourNode>()
                .forEach { it.colour = NodeColour.LOOP }
        graph.replaceNode(startNode.index, newStartNode)
        colourNodes(graph)
        log.debug { graph.printGraphString() }
        val innerNodes = graph.nodes.filter { it.colour == NodeColour.INSIDE }
        return innerNodes.size
    }

    private fun findStartNode(graph: Graph): Node {
        val startPosition = graph.startPosition
        val startNode = graph.rows[startPosition.first][startPosition.second]
        return startNode
    }

    private fun findLoop(startNode: Node, graph: Graph): Triple<Collection<Node>, Node, Int> {
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

        val neighboursOfStart = visitedNodes.filter { it.isConnectedTo(startNode) && startNode.isConnectedTo(it) }
        val realStartNodeType = findStartNodeType(startNode.index, neighboursOfStart)
        val realStartNode = Node.of(realStartNodeType, startNode.index)
        visitedNodes.remove(startNode)
        visitedNodes.add(realStartNode)

        return Triple(visitedNodes, realStartNode, furthestDistance)
    }

    private fun colourNodes(graph: Graph) {
        val uncolouredNodes = graph.nodes.filter { it.colour == NodeColour.UNKNOWN }
        val intersectionFinder = IntersectionFinder()
        for (node in uncolouredNodes) {
            if (node !is AdjustableColourNode) {
                continue
            }

            val nodesToBorder = mutableListOf<Node>(node)
            var neighbour = node
            do {
                neighbour = findNode(graph, neighbour.westIndex())
                nodesToBorder.add(neighbour)
            } while (neighbour !is BorderNode)
            val intersections = intersectionFinder.findNumberOfIntersections(nodesToBorder)
            if (isOdd(intersections)) {
                node.colour = NodeColour.INSIDE
            } else {
                node.colour = NodeColour.OUTSIDE
            }
        }
    }

    private fun isOdd(intersections: Int) = intersections % 2 == 1

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
        ).map { (row, col) ->
            findNode(graph, row, col)
        }
        return neighbours
    }

    private fun findStartNodeType(startNodeIndex: Pair<Int, Int>, neighbours: Collection<Node>): NodeType {
        if (neighbours.size != 2) {
            throw IllegalArgumentException("Neighbours must have size 2")
        }

        val firstNode = neighbours.first()
        val firstNodeType = firstNode.nodeType
        val secondNode = neighbours.last()
        val secondNodeType = secondNode.nodeType
        if (firstNodeType == secondNodeType) {
            return firstNodeType
        }

        if (firstNode.northIndex() == startNodeIndex && secondNode.southIndex() == startNodeIndex
                || firstNode.southIndex() == startNodeIndex && secondNode.northIndex() == startNodeIndex) {
            return NodeType.VERTICAL
        }

        if (firstNode.eastIndex() == startNodeIndex && secondNode.westIndex() == startNodeIndex
                || firstNode.westIndex() == startNodeIndex && secondNode.eastIndex() == startNodeIndex) {
            return NodeType.HORIZONTAL
        }

        if (firstNode.northIndex() == startNodeIndex && secondNode.westIndex() == startNodeIndex
                || firstNode.westIndex() == startNodeIndex && secondNode.northIndex() == startNodeIndex) {
            return NodeType.SOUTH_EAST
        }

        if (firstNode.southIndex() == startNodeIndex && secondNode.westIndex() == startNodeIndex
                || firstNode.westIndex() == startNodeIndex && secondNode.southIndex() == startNodeIndex) {
            return NodeType.NORTH_EAST
        }

        if (firstNode.northIndex() == startNodeIndex && secondNode.eastIndex() == startNodeIndex
                || firstNode.eastIndex() == startNodeIndex && secondNode.northIndex() == startNodeIndex) {
            return NodeType.SOUTH_WEST
        }

        if (firstNode.southIndex() == startNodeIndex && secondNode.eastIndex() == startNodeIndex
                || firstNode.eastIndex() == startNodeIndex && secondNode.southIndex() == startNodeIndex) {
            return NodeType.NORTH_WEST
        }

        throw IllegalArgumentException("Invalid combination of neighbours")
    }

    private fun findNode(
            graph: Graph,
            index: Pair<Int, Int>
    ) = findNode(graph, index.first, index.second)

    private fun findNode(
            graph: Graph,
            row: Int,
            col: Int
    ) = if (row in graph.rows.indices && col in graph.rows[row].indices) {
        graph.rows[row][col]
    } else {
        BorderNode(Pair(row, col))
    }

    private fun isReachable(
            visitedNodes: MutableSet<Node>,
            currentNode: Node,
            neighbour: Node
    ) = (neighbour !in visitedNodes
            && currentNode.isConnectedTo(neighbour)
            && neighbour.isConnectedTo(currentNode))


    companion object {
        private val log = KotlinLogging.logger {}
    }
}