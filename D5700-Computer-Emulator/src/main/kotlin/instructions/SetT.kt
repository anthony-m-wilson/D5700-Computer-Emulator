package org.example.instructions

import org.example.TimerManager
import org.example.combineNibbles
import org.example.memory.TManager.t

class SetT(nibbles: ByteArray) : Instruction(nibbles){
    private var value: Byte = 0

    override fun process() {
        value = combineNibbles(nibbles[0], nibbles[1])
    }

    override fun perform() {
        TimerManager.timer.set(true)

        t.writeBytes(byteArrayOf(value))

        TimerManager.timer.set(false)
    }
}