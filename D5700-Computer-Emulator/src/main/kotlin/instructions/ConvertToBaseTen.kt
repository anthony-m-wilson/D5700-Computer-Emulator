package org.example.instructions

import org.example.byteArrayToInt
import org.example.memory.AManager.a
import org.example.memory.MManager.m
import org.example.memory.R
import org.example.memory.RAMManager.ram
import org.example.memory.RManager.r
import org.example.memory.ROMManager

class ConvertToBaseTen(nibbles: ByteArray) : Instruction(nibbles) {
    private lateinit var registerX: R

    override fun process() {
        val registerXIndex = nibbles[0].toInt()
        registerX = r[registerXIndex]
    }

    override fun perform() {
        val address = byteArrayToInt(a.readBytes())
        val value = registerX.readBytes()[0].toInt()

        val (hundreds, tens, ones) = listOf(value / 100, (value % 100) / 10, value % 10)


        val isUsingROM = m.readBytes()[0].toInt() != 0
        val memory = if (isUsingROM) ROMManager.getROM() else ram

        memory.write(address, hundreds.toByte())
        memory.write(address + 1, tens.toByte())
        memory.write(address + 2, ones.toByte())
    }
}