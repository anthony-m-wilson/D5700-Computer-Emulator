package org.example.instructions

import org.example.combineNibbles
import org.example.memory.R
import org.example.memory.RManager.r

class Store(nibbles: ByteArray) : Instruction(nibbles) {
    private lateinit var registerX: R
    private var byte: Byte = 0


    override fun process() {
        val registerXIndex = nibbles.first().toInt()
        registerX = r[registerXIndex]

        val nibbleHigh = nibbles[1]
        val nibbleLow = nibbles[2]

        byte = combineNibbles(nibbleHigh, nibbleLow)
    }

    override fun perform() {
        registerX.writeBytes(byteArrayOf(byte))
    }
}