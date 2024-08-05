package org.example.memory

abstract class Memory(val bytes: ByteArray) {

    abstract fun read(address: Int): Byte
    abstract fun write(address: Int, byte: Byte)
}