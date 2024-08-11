package com.hellocuriosity.data.converters

import com.hellocuriosity.TestData
import kotlinx.serialization.json.JsonArray
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CsvJsonConverterTest {
    private val converter = CsvJsonConverter

    @Test
    fun `CSV conversion fails due to missing CSV file`() {
        val missing = "missing.csv"
        val exception = assertFailsWith<IllegalArgumentException> { converter.from(missing) }
        assertEquals("File $missing does not exist or is not a valid file", exception.message)
    }

    @Test
    fun `CSV conversion fails due to empty CSV file`() {
        val csvFile = File.createTempFile("test", ".csv")
        csvFile.writeText("")

        val exception = assertFailsWith<IllegalArgumentException> { converter.from(csvFile.absolutePath) }
        assertEquals("File $csvFile is empty", exception.message)

        csvFile.delete()
    }

    @Test
    fun `CSV converts to JSON successfully`() {
        val csvFile = File.createTempFile("test", ".csv")
        csvFile.writeText(TestData.readCsv)

        val resultJson = converter.from(csvFile.absolutePath)
        assertEquals(TestData.completeJson, resultJson)

        csvFile.delete()
    }

    @Test
    fun `JSON conversion fails due to empty JSON object`() {
        val emptyJson = JsonArray(emptyList())

        val exception = assertFailsWith<IllegalArgumentException> { converter.to(emptyJson) }
        assertEquals("JSON array is empty", exception.message)
    }

    @Test
    fun `JSON converts to CSV successfully`() {
        val resultCsv = converter.to(TestData.completeJson)
        assertEquals(TestData.completeCsv, resultCsv.trim())
    }

    @Test
    fun `JSON converts to CSV successfully with null value`() {
        val resultCsv = converter.to(TestData.incompleteJson)
        assertEquals(TestData.emptyCsv, resultCsv.trim())
    }

    @Test
    fun `CSV converts to JSON successfully with empty value`() {
        val json = TestData.incompleteJson

        val resultCsv = converter.to(json)
        assertEquals(TestData.emptyCsv, resultCsv.trim())
    }

    @Test
    fun `CSV converts to JSON successfully with null value`() {
        val json = TestData.nullJson

        val resultCsv = converter.to(json)
        assertEquals(TestData.emptyCsv, resultCsv.trim())
    }
}
