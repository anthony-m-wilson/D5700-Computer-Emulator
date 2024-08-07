package org.example

class Display {
    companion object {
        const val WIDTH = 8
        const val HEIGHT = 8
    }

    private val frameBuffer: ByteArray = ByteArray(WIDTH * HEIGHT)

    private fun printDisplay() {
        println("=".repeat(WIDTH))
        frameBuffer.forEachIndexed { index, byte ->
            print(byte.toInt().toChar())
            if ((index + 1) % WIDTH == 0) println()
        }
    }

    fun drawDisplay(byte: Byte, row: Byte, col: Byte) {
        val rowIndex = row.toInt()
        val colIndex = col.toInt()

        if (rowIndex in 0 until HEIGHT && colIndex in 0 until WIDTH) {
            frameBuffer[rowIndex * WIDTH + colIndex] = byte
            printDisplay()
        } else {
            throw IndexOutOfBoundsException("Row and column index out of bounds")
        }
    }
}

object DisplayManager {
    val display = Display()
}