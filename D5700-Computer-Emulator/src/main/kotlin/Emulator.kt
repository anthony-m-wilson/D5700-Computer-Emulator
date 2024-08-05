package org.example

import org.example.memory.ROM
import org.example.memory.ROMManager
import java.io.File

class Emulator {
    private var cpu = CPU()

    fun run() {
        println("Enter the path to the ROM binary file:")
        val filePath = readln()
        try {
            val binaryFile = File(filePath).takeIf { it.exists() } ?: throw Exception("File not found")
            val binaryProgram = try {
                binaryFile.readBytes()
            } catch (e: Exception) {
                throw Exception("Error reading binary file")
            }
            cpu.execute(getRom(binaryProgram))
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun getRom(binaryProgram: ByteArray): ROM {
        val memory = ByteArray(4096)
        for (i in binaryProgram.indices) {
            memory[i] = binaryProgram[i]
        }
        ROMManager.initializeROM(memory)
        val rom = ROMManager.getROM()
        return rom
    }
}