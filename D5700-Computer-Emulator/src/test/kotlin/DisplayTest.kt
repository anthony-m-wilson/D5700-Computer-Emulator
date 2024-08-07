import org.example.Display
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class DisplayTest {
    private lateinit var display: Display

    @BeforeEach
    fun setUp() {
        display = Display()
    }

    @Test
    fun drawDisplay_withinBounds_updatesFrameBuffer() {
        val asciiChar: Byte = 65
        val row: Byte = 2
        val col: Byte = 3
        val frameBuffer = ByteArray(Display.WIDTH * Display.HEIGHT)

        display.drawDisplay(asciiChar, row, col)

        assertEquals(0, frameBuffer[2 * Display.WIDTH + 3])
    }

    @Test
    fun drawDisplay_outOfBounds_throwsIndexOutOfBoundsException() {
        val asciiChar: Byte = 65
        val row: Byte = 20
        val col: Byte = 3
        val frameBuffer = ByteArray(Display.WIDTH * Display.HEIGHT)

        assertThrows<IndexOutOfBoundsException> {
            display.drawDisplay(asciiChar, row, col)
        }
    }

    @Test
    fun drawDisplay_negativeIndex_throwsIndexOutOfBoundsException() {
        val asciiChar: Byte = 65
        val row: Byte = -2
        val col: Byte = 3

        assertThrows<IndexOutOfBoundsException> {
            display.drawDisplay(asciiChar, row, col)
        }
    }

    @Test
    fun drawDisplay_edgeCase_updatesFrameBuffer() {
        val asciiChar: Byte = 65
        val row: Byte = 7
        val col: Byte = 7
        val frameBuffer = ByteArray(Display.WIDTH * Display.HEIGHT)

        display.drawDisplay(asciiChar, row, col)
        assertEquals(0, frameBuffer[7 * Display.WIDTH + 7])
    }
}