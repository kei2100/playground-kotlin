package basics.idioms

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested

class IdiomsKtTest {
    @Test
    fun safeConvertToIntTest() {
        assertEquals(100, safeConvertToInt("100"))
        assertEquals(null, safeConvertToInt("百"))
    }

    @Test
    fun iterateRangeTest() {
        assertEquals(55, iterateRangeCloseEnded())
        assertEquals(45, iterateRangeOpenEnded())
    }

    @Nested
    inner class LazyPropertyTest {
        @Test
        fun lazyPropertyTest() {
            val lazyProperty = LazyProperty()
            assertEquals("Lazy property", lazyProperty.lazy)
        }
    }

    @Test
    fun tryWithResourceTest() {
        assertEquals("Hello, World!", tryWithResource())
    }

    @Test
    fun swapTwoVariablesTest() {
        assertEquals(Pair(2, 1), swapTwoVariables())
    }

    @Test
    fun useTODOTest(){
        assertThrows(NotImplementedError::class.java) {
            useTODO()
        }
    }
}