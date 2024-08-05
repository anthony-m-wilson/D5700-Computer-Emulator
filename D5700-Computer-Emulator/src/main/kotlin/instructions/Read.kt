package org.example.instructions

import org.example.byteArrayToInt
import org.example.memory.AManager.a
import org.example.memory.MManager.m
import org.example.memory.R
import org.example.memory.RAMManager.ram
import org.example.memory.RManager.r
import org.example.memory.ROMManager

class Read(nibbles: ByteArray) : Instruction(nibbles) {
    private lateinit var registerX: R

    override fun process() {
        val registerIndex = nibbles[1].toInt()
        registerX = r[registerIndex]
    }

    override fun perform() {
        val byteArrayM = m.readBytes()
        val isUsingROM = byteArrayM[0].toInt() != 0

        val addressArray = a.readBytes()
        val address = byteArrayToInt(addressArray)

        val value = if (isUsingROM) ROMManager.getROM().read(address) else ram.read(address)

        registerX.writeBytes(byteArrayOf(value))
    }
}