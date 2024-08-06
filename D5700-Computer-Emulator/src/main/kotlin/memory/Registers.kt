package org.example.memory

import org.example.byteArrayToInt

abstract class Register(val bytes: ByteArray) {

    fun readBytes(): ByteArray {
        return bytes.copyOf()
    }

    abstract fun writeBytes(newBytes: ByteArray)
}

// general purpose registers
class R : Register(ByteArray(1)) {
    override fun writeBytes(newBytes: ByteArray) {
        require(newBytes.size == 1)
        newBytes.copyInto(destination = this.bytes, startIndex = 0, endIndex = 1)
    }
}

// special program counter register
class P : Register(ByteArray(2)) {
    override fun writeBytes(newBytes: ByteArray) {
        require(newBytes.size == 2)
        val intBytes = byteArrayToInt(newBytes)
        require(intBytes % 2 == 0)
        newBytes.copyInto(destination = this.bytes, startIndex = 0, endIndex = 2)
    }
}

// special timer register
class T : Register(ByteArray(1)) {
    override fun writeBytes(newBytes: ByteArray) {
        require(newBytes.size == 1)
        newBytes.copyInto(destination = this.bytes, startIndex = 0, endIndex = 1)
    }

}

// special address register
class A : Register(ByteArray(2)) {
    override fun writeBytes(newBytes: ByteArray) {
        require(newBytes.size == 2)
        newBytes.copyInto(destination = this.bytes, startIndex = 0, endIndex = 2)
    }

}

// special memory register
class M : Register(ByteArray(1)) {
    override fun writeBytes(newBytes: ByteArray) {
        require(newBytes.size == 1)
        val flag = newBytes[0].toInt() and 0xFF
        require(flag == 0 || flag == 1)
        newBytes.copyInto(destination = this.bytes, startIndex = 0, endIndex = 1)
    }

}

object RManager {
    val r = arrayOf(R(), R(), R(), R(), R(), R(), R(), R())
}

object PManager {
    val p = P()
}

object TManager {
    val t = T()
}

object AManager {
    val a = A()
}

object MManager {
    val m = M()
}
