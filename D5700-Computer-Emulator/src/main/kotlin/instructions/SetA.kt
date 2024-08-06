package org.example.instructions

import org.example.intToByteArray
import org.example.memory.AManager.a

class SetA(nibbles: ByteArray) : Instruction(nibbles) {

    private lateinit var addressBytes: ByteArray

    override fun process() {
        val nibbleHigh = nibbles[0].toInt()
        val nibbleMid = nibbles[1].toInt()
        val nibbleLow = nibbles[2].toInt()

        val address = (nibbleHigh shl 8) or (nibbleMid shl 4) or nibbleLow
        addressBytes = intToByteArray(address)
    }

    override fun perform() {
        a.writeBytes(addressBytes)
    }
}