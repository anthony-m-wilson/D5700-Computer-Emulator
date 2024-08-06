package org.example.instructions

import org.example.memory.MManager.m

class SwitchMemory(nibbles: ByteArray) : Instruction(nibbles) {
    override fun process() {
        // No processing needed
    }

    override fun perform() {
        val currentM = m.readBytes().first().toInt()
        val newM = if (currentM == 0) 1 else 0
        m.writeBytes(byteArrayOf(newM.toByte()))
    }
}