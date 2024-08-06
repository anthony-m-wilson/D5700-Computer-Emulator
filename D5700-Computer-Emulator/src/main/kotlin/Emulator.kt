package org.example

import org.example.memory.ROM
import org.example.memory.ROMManager
import java.io.File
import java.io.IOException

class Emulator {
    private var cpu = CPU()

    fun run() {
        println("Enter the path to the ROM binary file:")
        val filePath = readlnOrNull() ?: return println("No path provided")

        try {
            val binaryFile = getBinaryFile(filePath)
            val binaryProgram = getBinaryProgramFromBinaryFile(binaryFile)
            val rom = getRom(binaryProgram)
            cpu.execute(rom)
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun getBinaryFile(pathToBinaryFile: String): File {
        val file = File(pathToBinaryFile)
        if (!file.exists()) {
            throw IOException("File not found: $pathToBinaryFile")
        }
        return file
    }

    private fun getBinaryProgramFromBinaryFile(binaryFile: File): ByteArray {
        return try {
            binaryFile.readBytes()
        } catch (e: IOException) {
            throw IOException("Failed to read binary file", e)
        }
    }

    private fun getRom(binaryProgram: ByteArray): ROM {
        val memory = ByteArray(4096)
        binaryProgram.copyInto(memory, 0, 0, binaryProgram.size)
        ROMManager.initializeROM(memory)
        return ROMManager.getROM()
    }
}