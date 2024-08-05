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
        val input = readln().trim().uppercase()
        val byte = hexValue(input)

        registerX.writeBytes(byteArrayOf(byte))

        TimerManager.timer.set(false)
    }

    private fun hexValue(input: String): Byte {
        if (input.isEmpty() || !input.matches(Regex("^[0-9A-F]*$"))) {
            return 0
        }

        return try {
            val hexString = input.take(2)
            hexString.toInt(16).toByte()
        } catch (e: NumberFormatException) {
            0
        }
    }
}