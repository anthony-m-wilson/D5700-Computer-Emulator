package org.example.instructions

import org.example.byteArrayToInt
import org.example.intToByteArray
import org.example.memory.PManager.p

abstract class Instruction(protected val nibbles: ByteArray) {
    init {
        require(nibbles.size == 3)
    }

    fun execute() {
        process()
        perform()
        incrementProgramCounter()
    }

    protected open fun incrementProgramCounter() {
        val currentPC = byteArrayToInt(p.readBytes())
        p.writeBytes(intToByteArray(currentPC + 2))
    }

    protected abstract fun process()
    protected abstract fun perform()

}