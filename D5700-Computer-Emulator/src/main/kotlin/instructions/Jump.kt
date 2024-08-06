package org.example.instructions

import org.example.intToByteArray
import org.example.memory.PManager.p

class Jump(nibbles: ByteArray) : Instruction(nibbles){
    private lateinit var addressBytes: ByteArray

    override fun process() {
        val address = (nibbles[0].toInt() shl 8) or (nibbles[1].toInt() shl 4) or nibbles[2].toInt()
        addressBytes = intToByteArray(address)
    }

    override fun perform() {
        p.writeBytes(addressBytes)
    }

    override fun incrementProgramCounter() {
        // Do not increment program counter
    }
}