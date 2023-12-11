package de.twomartens.adventofcode.day11.graph

import de.twomartens.adventofcode.day11.node.GalaxyNode
import de.twomartens.adventofcode.day11.node.Node
import de.twomartens.adventofcode.day11.node.NodeType


data class Graph(val name: String, val galaxies: Collection<Pair<Int, Int>>) {

    override fun toString(): String {
        return name
    }

    companion object {
        fun of(name: String, rows: List<String>, emptySpaceMultiplier: Int): Graph {
            if (emptySpaceMultiplier < 1) {
                throw IllegalArgumentException("Empty space cannot be deleted")
            }

            val colIndicesWithGalaxy = mutableListOf<Int>()
            val nodeRows = rows.map {
                it.split("")
            }.mapIndexed { indexRow, row ->
                row.filterNot { it.isBlank() }.mapIndexed { indexCol, col ->
                    val nodeType = NodeType.of(col)
                    val index = Pair(indexRow, indexCol)
                    if (nodeType == NodeType.GALAXY) {
                        colIndicesWithGalaxy.add(indexCol)
                    }
                    Node.of(nodeType, index)
                }
            }

            val colIndicesWithoutGalaxy = mutableListOf<Int>()
            for (i in nodeRows.first().indices) {
                if (i !in colIndicesWithGalaxy) {
                    colIndicesWithoutGalaxy.add(i)
                }
            }

            val rowIndicesWithoutGalaxies = mutableListOf<Int>()
            nodeRows.forEachIndexed { index, row ->
                if (row.none { it.nodeType == NodeType.GALAXY }) {
                    rowIndicesWithoutGalaxies.add(index)
                }
            }
            val galaxies = nodeRows.flatten().filter { it.nodeType == NodeType.GALAXY }
                    .map { it.index }

            val updatedGalaxies = updateIndices(galaxies,
                    colIndicesWithoutGalaxy, rowIndicesWithoutGalaxies,
                    emptySpaceMultiplier)

            return Graph(name, updatedGalaxies)
        }

        private fun updateIndices(
                galaxies: Collection<Pair<Int, Int>>,
                colIndicesWithoutGalaxy: Collection<Int>,
                rowIndicesWithoutGalaxy: Collection<Int>,
                emptySpaceMultiplier: Int
        ): Collection<Pair<Int, Int>> {
            return galaxies.map { galaxy ->
                val emptyRowsBefore = rowIndicesWithoutGalaxy.filter { it < galaxy.first }.size
                val emptyColumnsBefore = colIndicesWithoutGalaxy.filter { it < galaxy.second }.size
                Pair(
                        calculateExpandedRowIndex(galaxy, emptySpaceMultiplier, emptyRowsBefore),
                        calculateExpandedColumnIndex(galaxy, emptySpaceMultiplier, emptyColumnsBefore)
                )
            }
        }

        private fun calculateExpandedColumnIndex(galaxy: Pair<Int, Int>,
                                                 emptySpaceMultiplier: Int,
                                                 emptyColumnsBefore: Int
        ) = galaxy.second + emptySpaceMultiplier * emptyColumnsBefore - emptyColumnsBefore

        private fun calculateExpandedRowIndex(
                galaxy: Pair<Int, Int>,
                emptySpaceMultiplier: Int,
                emptyRowsBefore: Int
        ) = galaxy.first + emptySpaceMultiplier * emptyRowsBefore - emptyRowsBefore
    }
}