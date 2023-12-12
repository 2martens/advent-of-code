package de.twomartens.adventofcode.day11.graph


data class Graph(val name: String, val galaxies: Collection<Pair<Int, Int>>) {

    override fun toString(): String {
        return name
    }

    companion object {
        fun of(name: String, rows: List<String>, emptySpaceMultiplier: Int): Graph {
            if (emptySpaceMultiplier < 1) {
                throw IllegalArgumentException("Empty space cannot be deleted")
            }

            val galaxies = mutableListOf<Pair<Int, Int>>()
            val colIndices = mutableMapOf<Int, Boolean>()
            val rowIndicesWithoutGalaxies = mutableListOf<Int>()
            rows.map { it.split("") }.forEachIndexed { indexRow, row ->
                var rowContainsGalaxy = false
                row.filterNot { it.isBlank() }.forEachIndexed { indexCol, col ->
                    colIndices.putIfAbsent(indexCol, false)

                    val index = Pair(indexRow, indexCol)
                    if (col == "#") {
                        galaxies.add(index)
                        rowContainsGalaxy = true
                        colIndices[indexCol] = true
                    }
                }
                if (!rowContainsGalaxy) {
                    rowIndicesWithoutGalaxies.add(indexRow)
                }
            }
            val colIndicesWithoutGalaxy = colIndices.entries
                    .filterNot { it.value }
                    .map { it.key }

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