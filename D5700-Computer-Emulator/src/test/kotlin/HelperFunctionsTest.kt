import org.example.byteArrayToInt
import org.example.byteToNibbles
import org.example.combineNibbles
import org.example.intToByteArray

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class HelperFunctionsTest {

    @Test
    fun byteToNibbles_splitsByteCorrectly() {
        val byte: Byte = 0xAB.toByte()
        val (highNibble, lowNibble) = byteToNibbles(byte)
        assertEquals(0x0A.toByte(), highNibble)
        assertEquals(0x0B.toByte(), lowNibble)
    }

    @Test
    fun combineNibbles_combinesNibblesCorrectly() {
        val highNibble: Byte = 0x0A
        val lowNibble: Byte = 0x0B
        val combinedByte = combineNibbles(highNibble, lowNibble)
        assertEquals(0xAB.toByte(), combinedByte)
    }

    @Test
    fun byteArrayToInt_convertsByteArrayToIntCorrectly() {
        val byteArray = byteArrayOf(0x12.toByte(), 0x34.toByte())
        val intValue = byteArrayToInt(byteArray)
        assertEquals(0x1234, intValue)
    }

    @Test
    fun byteArrayToInt_throwsExceptionForInvalidSize() {
        val byteArray = byteArrayOf(0x12.toByte())
        assertFailsWith<IllegalArgumentException> {
            byteArrayToInt(byteArray)
        }
    }

    @Test
    fun intToByteArray_convertsIntToByteArrayCorrectly() {
        val intValue = 0x1234
        val byteArray = intToByteArray(intValue)
        assertEquals(byteArrayOf(0x12.toByte(), 0x34.toByte()).toList(), byteArray.toList())
    }

    @Test
    fun intToByteArray_throwsExceptionForOutOfRangeInt() {
        val intValue = 0x10000
        assertFailsWith<IllegalArgumentException> {
            intToByteArray(intValue)
        }
    }
}