package org.example

class Display {
    companion object {
        const val WIDTH = 8
        const val HEIGHT = 8
    }

    val frameBuffer: ByteArray = ByteArray(WIDTH * HEIGHT)

    private fun printDisplay() {
        println("=".repeat(WIDTH))
        for (i in 0 until HEIGHT) {
            for (j in 0 until WIDTH) {
                print(frameBuffer[i * WIDTH + j].toInt().toChar())
            }
            println()
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