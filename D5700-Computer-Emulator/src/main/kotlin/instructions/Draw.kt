package org.example.instructions

import org.example.DisplayManager.display
import org.example.memory.R
import org.example.memory.RManager.r

class Draw(nibbles: ByteArray) : Instruction(nibbles) {
    private lateinit var registerX: R
    private var row: Byte = 0
    private var col: Byte = 0

    override fun process() {
        registerX = r[nibbles.first().toInt()]
        row = nibbles[1]
        col = nibbles[2]
    }

    override fun perform() {
        val asciiValue = registerX.readBytes().first().toInt().also {
            require(it <= 0x7F) { "Value must be between (0-7F)" }
        }
        display.drawDisplay(asciiValue.toByte(), row, col)
    }
}