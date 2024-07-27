package com.hellocuriosity.converters

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CsvJsonConverterTest {
    private val converter = CsvJsonConverter
    private val csvContent: Csv =
        """
        name,age,city
        Alice,30,New York
        Bob,25,Los Angeles
        Charlie,35,Chicago
        """.trimIndent()

    private val jsonContent =
        buildJsonArray {
            add(
                buildJsonObject {
                    put("name", JsonPrimitive("Alice"))
                    put("age", JsonPrimitive("30"))
                    put("city", JsonPrimitive("New York"))
                },
            )
            add(
                buildJsonObject {
                    put("name", JsonPrimitive("Bob"))
                    put("age", JsonPrimitive("25"))
                    put("city", JsonPrimitive("Los Angeles"))
                },
            )
            add(
                buildJsonObject {
                    put("name", JsonPrimitive("Charlie"))
                    put("age", JsonPrimitive("35"))
                    put("city", JsonPrimitive("Chicago"))
                },
            )
        }

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
        csvFile.writeText(csvContent)

        val resultJson = converter.from(csvFile.absolutePath)
        assertEquals(jsonContent, resultJson)

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
        val resultCsv = converter.to(jsonContent)
        assertEquals(csvContent, resultCsv.trim())
    }

    @Test
    fun `JSON converts to CSV successfully with null value`() {
        val resultCsv = converter.to(jsonContent)
        assertEquals(csvContent, resultCsv.trim())
    }
}
