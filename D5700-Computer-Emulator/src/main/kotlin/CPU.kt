package org.example

import org.example.instructions.InstructionFactory
import org.example.memory.PManager.p
import org.example.memory.ROM
import org.example.memory.ROMManager
import org.example.memory.TManager.t
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

class CPU(private val instructionSpeed: Long = 2L, private val timerSpeed: Long = 16L) {

    private val executor = Executors.newSingleThreadScheduledExecutor()
    private val instructions = InstructionFactory()
    private var rom = ROMManager.rom

    val cpu = Runnable {
        try {
            val bytes = readInstructionP()
            require(bytes.size == 2)
            if (bytes[0] == 0.toByte() && bytes[1] == 0.toByte()) {
                executor.shutdown()
                return@Runnable
            }
            val nibblesPair1 = byteToNibbles(bytes[0])
            val nibblesPair2 = byteToNibbles(bytes[1])

            val nibble0 = nibblesPair1.first
            val nibble1 = nibblesPair1.second
            val nibble2 = nibblesPair2.first
            val nibble3 = nibblesPair2.second

            val instruction = instructions.createInstruction(nibble0, nibble1, nibble2, nibble3)
            instruction.execute()
        } catch (_: Exception) {
            executor.shutdown()
            return@Runnable
        }
    }

    val timer = Runnable {
        try {
            if (TimerManager.timer.get()) {
                return@Runnable
            }
            val currentT = t.readBytes()[0].toInt()
            if (currentT > 0) {
                t.writeBytes(byteArrayOf((currentT - 1).toByte()))
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
        } catch (_: Exception) {
        } finally {
            executor.shutdown()
        }
    }

    private fun readInstructionP(): ByteArray {
        return try {
            val instruction = byteArrayToInt(p.readBytes())
            val byte1 = rom.read(instruction)
            val byte2 = rom.read(instruction + 1)
            byteArrayOf(byte1, byte2)
        } catch (e: Exception) {
            byteArrayOf(0, 0)
        }
    }
}


object TimerManager {
    val timer = AtomicBoolean(false)
}