package org.example.instructions

import org.example.byteArrayToInt
import org.example.intToByteArray
import org.example.memory.PManager.p
import org.example.memory.R
import org.example.memory.RManager.r

class SkipEqual(nibbles: ByteArray) : Instruction(nibbles) {
    private var skip = false
    private lateinit var registerX: R
    private lateinit var registerY: R

    override fun process() {
        registerX = r[nibbles[0].toInt()]
        registerY = r[nibbles[1].toInt()]
    }

    override fun perform() {
        skip = (registerX.readBytes().first().toInt() == registerY.readBytes().first().toInt())
    }

    override fun incrementProgramCounter() {
        val offset = if (skip) 4 else 2
        p.writeBytes(intToByteArray(byteArrayToInt(p.readBytes()) + offset))
    }
}