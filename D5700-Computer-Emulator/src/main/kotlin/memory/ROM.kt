package org.example.memory

class ROM(bytes: ByteArray) : Memory(bytes) {
    override fun read(address: Int): Byte {
        return bytes[address]
    }

    override fun write(address: Int, byte: Byte) {
        throw UnsupportedOperationException("ROM is read-only")
    }
}

object Rom {
    private var rom: ROM? = null

    fun initializeROM(bytes: ByteArray) {
        rom = ROM(bytes)
    }
    fun getROM(): ROM {
        return rom ?: throw Exception("ROM not initialized")
    }
}