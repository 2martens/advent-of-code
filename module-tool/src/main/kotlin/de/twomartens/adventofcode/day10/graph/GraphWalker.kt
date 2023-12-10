package de.twomartens.adventofcode.day10.graph

import de.twomartens.adventofcode.day10.node.*
import mu.KotlinLogging

class GraphWalker {
    fun findFurthestDistance(graph: Graph): Int {
        val startNode = findStartNode(graph)
        val (_, distance) = findLoop(startNode, graph)

        return distance
    }

    fun findNumberOfContainedNodes(graph: Graph): Int {
        val startNode = findStartNode(graph)
        val (visitedNodes, _) = findLoop(startNode, graph)
        visitedNodes.filterIsInstance<AdjustableColourNode>()
                .forEach { it.colour = NodeColour.LOOP }
        val loopIndices = visitedNodes.map { it.index }
        val loop = Loop(visitedNodes, loopIndices)
        val anyNode = graph.randomNonLoopNode()
        if (anyNode != null) {
            colourNodes(anyNode, graph, loop)
            val innerNodes = graph.nodes.filter { it.colour == NodeColour.INSIDE }
            return innerNodes.size
        } else {
            return 0
        }
    }

    private fun findStartNode(graph: Graph): Node {
        val startPosition = graph.startPosition
        val startNode = graph.rows[startPosition.first][startPosition.second]
        return startNode
    }

    private fun findLoop(startNode: Node, graph: Graph): Pair<Collection<Node>, Int> {
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

    private fun colourNodes(startNode: Node, graph: Graph, loop: Loop) {
        val visitedNodes = expandGraph(startNode, graph, loop)

        if (visitedNodes.any { it is BorderNode }) {
            visitedNodes.filterIsInstance<AdjustableColourNode>()
                    .forEach { it.colour = NodeColour.OUTSIDE }
        } else {
            visitedNodes.filterIsInstance<AdjustableColourNode>()
                    .forEach { it.colour = NodeColour.INSIDE }
        }

        var uncolouredNodes = graph.rows.flatten().filter { it.colour == NodeColour.UNKNOWN }
        log.debug("remaining uncoloured nodes: {}", uncolouredNodes.size)
        while (uncolouredNodes.isNotEmpty()) {
            colourNodes(uncolouredNodes.random(), graph, loop)
            uncolouredNodes = graph.rows.flatten().filter { it.colour == NodeColour.UNKNOWN }
            log.debug("remaining uncoloured nodes: {}", uncolouredNodes.size)
        }
    }

    private fun expandGraph(
            startNode: Node,
            graph: Graph,
            loop: Loop
    ): MutableSet<Node> {
        log.debug("Expand graph with startNode at position: {}", startNode.index)
        val visitedNodes = mutableSetOf<Node>()
        val queue = ArrayDeque<Node>()
        queue.add(startNode)

        while (queue.isNotEmpty()) {
            log.debug("Current amount of entries in queue: {}", queue.size)
            val currentNode = queue.removeFirst()
            visitedNodes.add(currentNode)

            if (currentNode is BorderNode) {
                continue
            }

            val neighbours = findNeighboursWithPipes(currentNode, graph, loop)

            for (neighbour in neighbours) {
                if (neighbour !in visitedNodes
                        && neighbour.colour in listOf(NodeColour.UNKNOWN, NodeColour.BORDER)
                        && !queue.contains(neighbour)) {
                    queue.add(neighbour)
                }
            }
        }
        return visitedNodes
    }

    private fun initializeQueue(startNode: Node): ArrayDeque<Pair<Node, Int>> {
        val queue = ArrayDeque<Pair<Node, Int>>()
        queue.add(startNode to 0)
        return queue
    }

    private fun findNeighboursWithPipes(
            currentNode: Node,
            graph: Graph,
            loop: Loop
    ): List<Node> {
        val neighbours = listOf(
                findNorthernNode(currentNode, graph, loop),
                findEasternNode(currentNode, graph, loop),
                findSouthernNode(currentNode, graph, loop),
                findWesternNode(currentNode, graph, loop)
        )
        return neighbours
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

    private fun findNorthernNode(currentNode: Node, graph: Graph, loop: Loop): Node {
        val neighbourIndex = currentNode.northIndex()
        var neighbour = findNode(graph, neighbourIndex)
        val originalNeighbour = neighbour
        if (isLoopNode(loop, neighbour.index)
                && (neighbour is NorthWestNode || neighbour is NorthEastNode)) {
            var nextNodeIndex = neighbour.northIndex()
            var nextNeighbour = findNode(graph, nextNodeIndex)
            while (nextNeighbour.isConnectedTo(neighbour)
                    && neighbour.isConnectedTo(nextNeighbour)) {
                neighbour = nextNeighbour
                nextNodeIndex = neighbour.northIndex()
                nextNeighbour = findNode(graph, nextNodeIndex)
            }
            neighbour = if (neighbour is SouthWestNode && originalNeighbour is NorthWestNode
                    || neighbour is SouthEastNode && originalNeighbour is NorthEastNode) {
                nextNeighbour
            } else {
                originalNeighbour
            }
        }

        return neighbour
    }

    private fun findEasternNode(currentNode: Node, graph: Graph, loop: Loop): Node {
        val neighbourIndex = currentNode.eastIndex()
        var neighbour = findNode(graph, neighbourIndex)
        val originalNeighbour = neighbour
        if (isLoopNode(loop, neighbour.index)
                && (neighbour is NorthEastNode || neighbour is SouthEastNode)) {
            var nextNodeIndex = neighbour.eastIndex()
            var nextNeighbour = findNode(graph, nextNodeIndex)
            while (nextNeighbour.isConnectedTo(neighbour)
                    && neighbour.isConnectedTo(nextNeighbour)) {
                neighbour = nextNeighbour
                nextNodeIndex = neighbour.eastIndex()
                nextNeighbour = findNode(graph, nextNodeIndex)
            }
            neighbour = if (neighbour is NorthWestNode && originalNeighbour is NorthEastNode
                    || neighbour is SouthWestNode && originalNeighbour is SouthEastNode) {
                nextNeighbour
            } else {
                originalNeighbour
            }
        }

        return neighbour
    }

    private fun findSouthernNode(currentNode: Node, graph: Graph, loop: Loop): Node {
        val neighbourIndex = currentNode.southIndex()
        var neighbour = findNode(graph, neighbourIndex)
        val originalNeighbour = neighbour
        if (isLoopNode(loop, neighbour.index)
                && (neighbour is SouthWestNode || neighbour is SouthEastNode)) {
            var nextNodeIndex = neighbour.southIndex()
            var nextNeighbour = findNode(graph, nextNodeIndex)
            while (nextNeighbour.isConnectedTo(neighbour)
                    && neighbour.isConnectedTo(nextNeighbour)) {
                neighbour = nextNeighbour
                nextNodeIndex = neighbour.southIndex()
                nextNeighbour = findNode(graph, nextNodeIndex)
            }
            neighbour = if (neighbour is NorthWestNode && originalNeighbour is SouthWestNode
                    || neighbour is NorthEastNode && originalNeighbour is SouthEastNode) {
                nextNeighbour
            } else {
                originalNeighbour
            }
        }

        return neighbour
    }

    private fun findWesternNode(currentNode: Node, graph: Graph, loop: Loop): Node {
        val neighbourIndex = currentNode.westIndex()
        var neighbour = findNode(graph, neighbourIndex)
        val originalNeighbour = neighbour
        if (isLoopNode(loop, neighbour.index)
                && (neighbour is NorthWestNode || neighbour is SouthWestNode)) {
            var nextNodeIndex = neighbour.westIndex()
            var nextNeighbour = findNode(graph, nextNodeIndex)
            while (nextNeighbour.isConnectedTo(neighbour)
                    && neighbour.isConnectedTo(nextNeighbour)) {
                neighbour = nextNeighbour
                nextNodeIndex = neighbour.westIndex()
                nextNeighbour = findNode(graph, nextNodeIndex)
            }
            neighbour = if (neighbour is NorthEastNode && originalNeighbour is NorthWestNode
                    || neighbour is SouthEastNode && originalNeighbour is SouthWestNode) {
                nextNeighbour
            } else {
                originalNeighbour
            }
        }

        return neighbour
    }

    private fun isLoopNode(
            loop: Loop,
            neighbourIndex: Pair<Int, Int>
    ) = loop.indices.contains(neighbourIndex)

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