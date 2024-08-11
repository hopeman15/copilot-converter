package com.hellocuriosity.providers

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FileWriterProviderTest {
    @Test
    fun `Provider should write to file`() {
        val tempDir =
            File.createTempFile("temp", "").apply {
                delete()
                mkdirs()
            }
        val fileName = "test_output.csv"
        val provider = FileWriterProvider(fileName, tempDir)
        val data = "This is a test file"

        provider.write(data)

        val file = File(tempDir, fileName)
        assertTrue(file.exists())
        assertEquals(data, file.readText())

        file.delete()
        tempDir.deleteRecursively()
    }
}
