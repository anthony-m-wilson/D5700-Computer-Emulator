package org.example.memory

class RAM : Memory(ByteArray(4096)){
    override fun read(address: Int): Byte {
        TODO("Not yet implemented")
    }

    override fun write(address: Int, byte: Byte) {
        TODO("Not yet implemented")
    }
}