package org.example

import org.example.memory.ROM
import org.example.memory.Rom
import java.io.File
import java.io.IOException

class Emulator {
    private var cpu = CPU()

    fun run(path: String) {
        if (path.isBlank()) throw IllegalArgumentException("Path to binary file is empty")
        val file = File(path)
        if (!file.exists()) throw IllegalArgumentException("File not found: $path")

        try {
            val binaryFile = getBinaryFile(path)
            val binaryProgram = getBinaryProgram(binaryFile)
            val rom = getRom(binaryProgram)
            cpu.execute(rom)
        } catch (_: Exception) {
        }
    }

    private fun getBinaryFile(path: String): File {
        val file = File(path)
        if (!file.exists()) {
            throw IOException("File not found: $path")
        }
        return file
    }

    private fun getBinaryProgram(binaryFile: File): ByteArray {
        return try {
            binaryFile.readBytes()
        } catch (e: IOException) {
            throw IOException("Failed to read binary file", e)
        }
    }

    private fun getRom(binaryProgram: ByteArray): ROM {
        val memory = ByteArray(4096)
        binaryProgram.copyInto(memory, 0, 0, binaryProgram.size)
        Rom.initializeROM(memory)
        return Rom.getROM()
    }
}