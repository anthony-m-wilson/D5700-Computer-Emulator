import org.example.Display
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class DisplayTest {
    @Test
    fun drawDisplay_withinBounds_updatesFrameBuffer() {
        val display = Display()
        display.drawDisplay(1, 2, 3)
        assertEquals(1, display.frameBuffer[2 * Display.WIDTH + 3])
    }

    @Test
    fun drawDisplay_outOfBounds_throwsIndexOutOfBoundsException() {
        val display = Display()
        assertThrows<IndexOutOfBoundsException> {
            display.drawDisplay(1, 8, 8)
        }
    }

    @Test
    fun drawDisplay_negativeIndex_throwsIndexOutOfBoundsException() {
        val display = Display()
        assertThrows<IndexOutOfBoundsException> {
            display.drawDisplay(1, -1, -1)
        }
    }

    @Test
    fun drawDisplay_edgeCase_updatesFrameBuffer() {
        val display = Display()
        display.drawDisplay(1, 7, 7)
        assertEquals(1, display.frameBuffer[7 * Display.WIDTH + 7])
    }
}