package org.example.instructions

import org.example.memory.R
import org.example.memory.RManager.r

class Sub(nibbles: ByteArray) : Instruction(nibbles) {
    private lateinit var registerX: R
    private lateinit var registerY: R
    private lateinit var registerZ: R

    override fun process() {
        registerX = r[nibbles[0].toInt()]
        registerY = r[nibbles[1].toInt()]
        registerZ = r[nibbles[2].toInt()]
    }

    override fun perform() {
        val registerXValue = registerX.readBytes().first().toInt()
        val registerYValue = registerY.readBytes().first().toInt()

        val diff = (registerXValue - registerYValue).toByte()

        registerZ.writeBytes(byteArrayOf(diff))
    }
}