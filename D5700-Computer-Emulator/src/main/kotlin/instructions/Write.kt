package org.example.instructions

import org.example.byteArrayToInt
import org.example.memory.AManager.a
import org.example.memory.MManager.m
import org.example.memory.R
import org.example.memory.RAMManager.ram
import org.example.memory.RManager.r
import org.example.memory.ROMManager

class Write(nibbles: ByteArray) : Instruction(nibbles) {
    private lateinit var registerX: R

    override fun process() {
        val registerXIndex = nibbles.first().toInt()
        registerX = r[registerXIndex]
    }

    override fun perform() {
        val address = byteArrayToInt(a.readBytes())
        val value = registerX.readBytes().first()

        val memory = if (m.readBytes().first().toInt() != 0) ROMManager.getROM() else ram
        memory.write(address, value)
    }
}