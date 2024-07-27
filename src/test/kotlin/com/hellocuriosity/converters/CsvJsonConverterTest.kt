package com.hellocuriosity.converters

import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.File
import kotlin.test.Test
import kotlin.test.assertFailsWith

class CsvJsonConverterTest {
    private val converter = CsvJsonConverter
    private val csvContent =
        """
        name,age,city
        Alice,30,New York
        Bob,25,Los Angeles
        Charlie,35,Chicago
        """.trimIndent()

    private val expectedJson =
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
        assertFailsWith<IllegalArgumentException> { converter.from("missing.csv") }
    }

    @Test
    fun `CSV conversion fails due to empty CSV file`() {
        val csvFile = File.createTempFile("test", ".csv")
        csvFile.writeText("")

        assertFailsWith<IllegalArgumentException> { converter.from(csvFile.absolutePath) }

        csvFile.delete()
    }

    @Test
    fun `CSV converts to JSON successfully`() {
        val csvFile = File.createTempFile("test", ".csv")
        csvFile.writeText(csvContent)

        val resultJson = converter.from(csvFile.absolutePath)
        assertEquals(expectedJson, resultJson)

        csvFile.delete()
    }
}
