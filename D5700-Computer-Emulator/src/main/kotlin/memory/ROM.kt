package org.example.memory

class ROM(bytes: ByteArray) : Memory(ByteArray(4096)) {
    override fun read(address: Int): Byte {
        val byte = bytes[address]
        return byte
    }

    override fun write(address: Int, byte: Byte) {
        throw UnsupportedOperationException("ROM is read-only")
    }
}

object ROMManager {
    private var rom: ROM? = null

    fun initializeROM(bytes: ByteArray) {
        rom = ROM(bytes)
    }
    fun getROM(): ROM {
        return rom ?: throw Exception("ROM not initialized")
    }


}