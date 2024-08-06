package org.example.instructions

import org.example.TimerManager
import org.example.memory.R
import org.example.memory.RManager.r

class ReadKeyboard(nibbles: ByteArray) : Instruction(nibbles) {

    private lateinit var registerX: R


    override fun process() {
        val registerXIndex = nibbles[0].toInt()
        registerX = r[registerXIndex]
    }

    override fun perform() {
        TimerManager.timer.set(true)

        println("Enter a hex value (0-F): ")
        val byte = hexValue(readln().trim().uppercase())
        registerX.writeBytes(byteArrayOf(byte))

        TimerManager.timer.set(false)
    }

    private fun hexValue(input: String): Byte {
        return input.takeIf { it.matches(Regex("^[0-9A-F]{1,2}$")) }
            ?.toIntOrNull(16)
            ?.toByte()
            ?: 0
    }
}