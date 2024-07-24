package com.hellocuriosity

import org.junit.jupiter.api.Assertions.assertEquals
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test

class MainTest {
    @Test
    fun testMain() {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)
        val originalOut = System.out

        try {
            // Redirect standard output to the custom stream
            System.setOut(printStream)

            // Call the main function
            main()

            // Flush the stream to ensure all output is captured
            printStream.flush()

            // Verify the output
            val output = outputStream.toString().trim()
            assertEquals("Hello World!", output)
        } finally {
            // Restore the original standard output
            System.setOut(originalOut)
        }
    }
}
