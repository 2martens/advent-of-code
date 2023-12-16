package de.twomartens.adventofcode.day16.graph

class Graph(val rows: List<List<Tile>>) {

    fun countEnergizedTiles(): Int {
        return rows.flatten().count { it.isEnergized }
    }

    fun deepCopy(): Graph {
        val newRows = rows.map { row ->
            row.map { tile ->
                tile.copy(reached = tile.reachedDirections.toMutableMap())
            }
        }
        return Graph(newRows)
    }

    companion object {
        fun of(lines: List<String>): Graph {
            val rows: MutableList<List<Tile>> = mutableListOf()
            for (line in lines) {
                val fields = line.split("").filterNot { it.isBlank() }
                val tiles = fields
                        .map { parseOperation(it) }
                        .map { Tile(it, false, mutableMapOf()) }
                rows.add(tiles)
            }

            return Graph(rows)
        }

        fun parseOperation(field: String): Operation {
            return when (field) {
                "/" -> Operation.REFLECT_SLASH
                "\\" -> Operation.REFLECT_BACKSLASH
                "." -> Operation.PASS_THROUGH
                "|" -> Operation.SPLIT_VERTICAL
                "-" -> Operation.SPLIT_HORIZONTAL
                else -> throw IllegalArgumentException("Field must be one of the supported types")
            }
        }
    }
}