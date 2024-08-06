package org.example

fun main(args: Array<String>) {
    val path = args.firstOrNull() ?: run {
        println("Enter the path to the ROM binary file:")
        readlnOrNull() ?: ""
    }

    val emulator = Emulator()
    emulator.run(path)
}