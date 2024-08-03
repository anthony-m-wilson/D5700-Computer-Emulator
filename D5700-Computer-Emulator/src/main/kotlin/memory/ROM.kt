package org.example.memory

class ROM : Memory(ByteArray(4096)) {
    override fun read(address: Int): Byte {
        TODO("Not yet implemented")
    }

    override fun write(address: Int, byte: Byte) {
        TODO("Not yet implemented")
    }
}