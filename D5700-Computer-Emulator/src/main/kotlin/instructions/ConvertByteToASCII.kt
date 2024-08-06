package org.example.instructions

import org.example.memory.R
import org.example.memory.RManager.r

class ConvertByteToASCII(nibbles: ByteArray) : Instruction(nibbles) {
    private lateinit var registerX: R
    private lateinit var registerY: R

    override fun process() {
        registerX = r[nibbles[0].toInt()]
        registerY = r[nibbles[1].toInt()]
    }

    override fun perform() {
        val value = registerX.readBytes().first().toInt()
        require(value <= 0xF) { "Value must be between 0 and 15 (0-F)" }

        val asciiValue = (if (value < 10) value + '0'.code else value - 10 + 'A'.code).toByte()
        registerY.writeBytes(byteArrayOf(asciiValue))
    }
}