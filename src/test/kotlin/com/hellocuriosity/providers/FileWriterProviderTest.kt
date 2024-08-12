package com.hellocuriosity.providers

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FileWriterProviderTest {
    private lateinit var tempDir: File
    private lateinit var file: File
    private lateinit var provider: FileWriterProvider

    private val fileName = "test_output.csv"
    private val data = "This is a test file"

    @BeforeTest
    fun setup() {
        tempDir = File.createTempFile("temp", "")
    }

    @AfterTest
    fun teardown() {
        file.delete()
        tempDir.deleteRecursively()
    }

    @Test
    fun `Provider should write to file when directory exists`() {
        tempDir.apply {
            delete()
            mkdirs()
        }
        writeFileAndValidate()
    }

    @Test
    fun `Provider should create directory and write to file`() {
        tempDir.apply { delete() }
        writeFileAndValidate()
    }

    private fun writeFileAndValidate() {
        provider = FileWriterProvider(fileName, tempDir)
        provider.write(data)

        file = File(tempDir, fileName)
        assertTrue(file.exists())
        assertEquals(data, file.readText())
    }
}
