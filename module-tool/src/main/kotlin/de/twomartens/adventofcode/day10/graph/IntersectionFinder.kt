package de.twomartens.adventofcode.day10.graph

import de.twomartens.adventofcode.day10.node.*

class IntersectionFinder {
    fun findNumberOfIntersections(nodes: Collection<Node>): Int {
        if (nodes.last() !is BorderNode) {
            throw IllegalArgumentException("Last node in collection must be BorderNode")
        }

        if (!nodes.any { isLoopNode(it) }) {
            return 0
        }

        var intersections = 0

        var metLoop = false
        var loopFromNorth = false
        var loopFromSouth = false
        for (node in nodes) {
            if (!metLoop && isNotLoopNode(node)
                    || node is HorizontalNode) {
                continue
            }

            if (isLoopNode(node) && node is VerticalNode) {
                intersections++
            }

            if (loopFromSouth && node is NorthEastNode) {
                intersections++
                metLoop = false
                loopFromSouth = false
            }

            if (loopFromNorth && node is SouthEastNode) {
                intersections++
                metLoop = false
                loopFromNorth = false
            }

            if (loopFromSouth && node is SouthEastNode) {
                metLoop = false
                loopFromSouth = false
            }

            if (loopFromNorth && node is NorthEastNode) {
                metLoop = false
                loopFromNorth = false
            }

            if (node is SouthWestNode) {
                loopFromSouth = true
                metLoop = true
            } else if (node is NorthWestNode) {
                loopFromNorth = true
                metLoop = true
            }
        }

        return intersections
    }

    private fun isNotLoopNode(node: Node) = node.colour != NodeColour.LOOP

    private fun isLoopNode(node: Node) = node.colour == NodeColour.LOOP
}