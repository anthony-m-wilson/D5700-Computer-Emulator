package org.example.instructions

import org.example.memory.R
import org.example.memory.RManager.r

class Add(nibbles: ByteArray) : Instruction(nibbles) {

    private lateinit var registerX: R
    private lateinit var registerY: R
    private lateinit var registerZ: R

    override fun process() {
        val registerXIndex = nibbles[1].toInt()
        val registerYIndex = nibbles[2].toInt()
        val registerZIndex = nibbles[3].toInt()

        registerX = r[registerXIndex]
        registerY = r[registerYIndex]
        registerZ = r[registerZIndex]
    }

    override fun perform() {
        val registerXValue = registerX.readBytes()[0].toInt()
        val registerYValue = registerY.readBytes()[0].toInt()

        val sum = (registerXValue + registerYValue).toByte()

        registerZ.writeBytes(byteArrayOf(sum))
    }
}