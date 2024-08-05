package org.example.memory

class ROM : Memory(ByteArray(4096)) {
    override fun read(address: Int): Byte {
        val byte = bytes[address]
        return byte
    }

    override fun write(address: Int, byte: Byte) {
        throw UnsupportedOperationException("ROM is read-only")
    }
}

object ROMManager {
    val rom = ROM()

}