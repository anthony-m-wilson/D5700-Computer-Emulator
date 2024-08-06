package org.example.memory

class RAM : Memory(ByteArray(4096)) {
    override fun read(address: Int): Byte {
        return bytes[address]
    }

    override fun write(address: Int, byte: Byte) {
        bytes[address] = byte
    }
}

object RAMManager {
    val ram = RAM()
}