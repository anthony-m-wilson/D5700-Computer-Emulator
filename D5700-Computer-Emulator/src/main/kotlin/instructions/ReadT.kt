package org.example.instructions

import org.example.TimerManager
import org.example.memory.R
import org.example.memory.RManager.r
import org.example.memory.TManager.t

class ReadT(nibbles: ByteArray) : Instruction(nibbles){
    private lateinit var registerX: R

    override fun process() {
        registerX = r[nibbles.first().toInt()]
    }

    override fun perform() {
        TimerManager.timer.set(true)

        registerX.writeBytes(byteArrayOf(t.readBytes().first()))

        TimerManager.timer.set(false)
    }
}