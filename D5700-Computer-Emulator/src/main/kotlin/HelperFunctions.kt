package org.example

fun byteToNibbles(byte: Byte): Pair<Byte, Byte> {
    val uByteInt = byte.toUByte().toInt()
    val high = (uByteInt shr 4) and 0x0F
    val low = uByteInt and 0x0F
    return Pair(high.toByte(), low.toByte())
}

fun combineNibbles(high: Byte, low: Byte): Byte {
    val highInt = high.toInt() and 0x0F
    val lowInt = low.toInt() and 0x0F
    val combinedNibble = (highInt shl 4) or lowInt
    return combinedNibble.toByte()
}

fun byteArrayToInt(byteArray: ByteArray): Int {
    require(byteArray.size == 2) { "Byte array must be of size 2" }
    val int = (byteArray[1].toInt() and 0xFF) or ((byteArray[0].toInt() and 0xFF) shl 8)
    return int
}

fun intToByteArray(int: Int): ByteArray {
    require(int in 0..0xFFFF) { "Integer must be between 0 and 65535" }
    val byteArray = ByteArray(2)
    byteArray[0] = (int shr 8 and 0xFF).toByte()
    byteArray[1] = (int and 0xFF).toByte()
    return byteArray
}