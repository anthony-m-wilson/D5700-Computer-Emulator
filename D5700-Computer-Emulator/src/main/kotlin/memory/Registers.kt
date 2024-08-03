package org.example.memory

abstract class Register(private val bytes: ByteArray) {

    fun readBytes(): ByteArray {
        val readBytes = bytes.copyOf()
        return readBytes
    }

    abstract fun writeBytes(newBytes: ByteArray)
}

// general purpose registers
class R : Register(ByteArray(1)) {
    override fun writeBytes(newBytes: ByteArray) {
        TODO("Not yet implemented")
    }
}

// special program counter register
class P : Register(ByteArray(2)) {
    override fun writeBytes(newBytes: ByteArray) {
        TODO("Not yet implemented")
    }
}

// special timer register
class T : Register(ByteArray(1)) {
    override fun writeBytes(newBytes: ByteArray) {
        TODO("Not yet implemented")
    }

}

// special address register
class A : Register(ByteArray(2)) {
    override fun writeBytes(newBytes: ByteArray) {
        TODO("Not yet implemented")
    }

}

// special memory register
class M : Register(ByteArray(1)) {
    override fun writeBytes(newBytes: ByteArray) {
        TODO("Not yet implemented")
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
