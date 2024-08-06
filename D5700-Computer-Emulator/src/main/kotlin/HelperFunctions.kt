package org.example

fun byteToNibbles(byte: Byte): Pair<Byte, Byte> {
    return Pair(
        ((byte.toInt() shr 4) and 0x0F).toByte(),
        (byte.toInt() and 0x0F).toByte()
    )
}

fun combineNibbles(high: Byte, low: Byte): Byte {
    return (((high.toInt() and 0x0F) shl 4) or (low.toInt() and 0x0F)).toByte()
}

fun byteArrayToInt(byteArray: ByteArray): Int {
    require(byteArray.size == 2) { "Byte array must be of size 2" }
    return (byteArray[1].toInt() and 0xFF) or ((byteArray[0].toInt() and 0xFF) shl 8)
}

fun intToByteArray(int: Int): ByteArray {
    require(int in 0..0xFFFF) { "Integer must be between 0 and 65535" }
    return byteArrayOf((int shr 8 and 0xFF).toByte(), (int and 0xFF).toByte())
}