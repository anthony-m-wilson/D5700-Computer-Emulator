package org.example

import org.example.instructions.InstructionFactory
import org.example.memory.PManager.p
import org.example.memory.ROM
import org.example.memory.TManager.t
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

class CPU(private val instructionSpeed: Long = 2L, private val timerSpeed: Long = 16L) {

    private val executor = Executors.newSingleThreadScheduledExecutor()
    private val instructions = InstructionFactory()
    private var rom: ROM? = null

    private val cpu = Runnable {
        try {
            val bytes = readInstructionP()
            require(bytes.size == 2)
            if (bytes[0] == 0.toByte() && bytes[1] == 0.toByte()) {
                executor.shutdown()
                return@Runnable
            }
            val (nibble0, nibble1) = byteToNibbles(bytes[0])
            val (nibble2, nibble3) = byteToNibbles(bytes[1])


            val instruction = instructions.createInstruction(nibble0, nibble1, nibble2, nibble3)
            instruction.execute()
        } catch (_: Exception) {
            executor.shutdown()
        }
    }

    private val timer = Runnable {
        try {
            if (!TimerManager.timer.get()) {
                val currentT = t.readBytes()[0].toInt()
                if (currentT > 0) {
                    t.writeBytes(byteArrayOf((currentT - 1).toByte()))
                }
            }
        } catch (_: Exception) {
        }
    }

    fun execute(rom: ROM) {
        this.rom = rom

        val cpuFuture = executor.scheduleAtFixedRate(
            cpu,
            0,
            instructionSpeed,
            TimeUnit.MILLISECONDS
        )
        val timerFuture = executor.scheduleAtFixedRate(
            timer,
            0,
            timerSpeed,
            TimeUnit.MILLISECONDS
        )

        try {
            cpuFuture.get()
            timerFuture.get()
        } catch (e: Exception) {
            println("Error executing: ${e.message}")
        } finally {
            executor.shutdown()
        }
    }

    private fun readInstructionP(): ByteArray {
        val instruction = byteArrayToInt(p.readBytes())
        val byte1 = rom?.read(instruction) ?: 0
        val byte2 = rom?.read(instruction + 1) ?: 0
        return byteArrayOf(byte1, byte2)
    }
}


object TimerManager {
    val timer = AtomicBoolean(false)
}