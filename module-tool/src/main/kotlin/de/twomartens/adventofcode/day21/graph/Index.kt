package de.twomartens.adventofcode.day21.graph

import kotlin.math.abs

data class Index(val row: Int, val column: Int) {
    fun isHorizontal(otherIndex: Index) =
            (abs(otherIndex.column - column) == 1 && otherIndex.row == row)

    fun isVertical(otherIndex: Index) =
            (abs(otherIndex.row - row) == 1 && otherIndex.column == column)

    fun isWest(otherIndex: Index) =
            (otherIndex.row == row && otherIndex.column == column - 1)

    fun isNorth(otherIndex: Index) = (otherIndex.row == row - 1 && otherIndex.column == column)

    fun isEast(otherIndex: Index) = (otherIndex.row == row && otherIndex.column == column + 1)

    fun isSouth(otherIndex: Index) = (otherIndex.row == row + 1 && otherIndex.column == column)
}
