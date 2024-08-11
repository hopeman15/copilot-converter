package com.hellocuriosity

import com.hellocuriosity.data.converters.Csv
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

object TestData {
    private val list: List<Map<String, String?>> =
        listOf(
            mapOf(
                "name" to "Alice",
                "age" to "30",
                "city" to "New York",
            ),
            mapOf(
                "name" to "Bob",
                "age" to "25",
                "city" to "Los Angeles",
            ),
        )

    val completeJson: JsonElement =
        Json.encodeToJsonElement(
            list.toMutableList().apply {
                add(
                    mapOf(
                        "name" to "Charlie",
                        "age" to "35",
                        "city" to "Chicago",
                    ),
                )
            },
        )

    val incompleteJson: JsonElement =
        Json.encodeToJsonElement(
            list.toMutableList().apply {
                add(
                    mapOf(
                        "name" to "Charlie",
                        "age" to "35",
                        "city" to "",
                    ),
                )
            },
        )

    val nullJson: JsonElement =
        Json.encodeToJsonElement(
            list.toMutableList().apply {
                add(
                    mapOf(
                        "name" to "Charlie",
                        "age" to "35",
                        "city" to null,
                    ),
                )
            },
        )

    val readCsv: Csv =
        """
        name,age,city
        Alice,30,New York
        Bob,25,Los Angeles
        Charlie,35,Chicago
        """.trimIndent()

    val completeCsv: Csv =
        """
        name;age;city
        Alice;30;New York
        Bob;25;Los Angeles
        Charlie;35;Chicago
        """.trimIndent()

    /**
     * Representation of a CSV file where
     * json values are either null or empty
     */
    val emptyCsv: Csv =
        """
        name;age;city
        Alice;30;New York
        Bob;25;Los Angeles
        Charlie;35;
        """.trimIndent()
}
