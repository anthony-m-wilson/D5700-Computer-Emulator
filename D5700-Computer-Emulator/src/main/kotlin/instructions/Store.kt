package org.example.instructions

import org.example.combineNibbles
import org.example.memory.R
import org.example.memory.RManager.r

class Store(nibbles: ByteArray) : Instruction(nibbles) {
    private lateinit var registerX: R
    private var byte: Byte = 0


    override fun process() {
        registerX = r[nibbles.first().toInt()]
        byte = combineNibbles(nibbles[1], nibbles[2])
    }

    override fun perform() {
        registerX.writeBytes(byteArrayOf(byte))
    }
}