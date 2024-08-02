package com.hellocuriosity.data.converters

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.File

typealias Csv = String

object CsvJsonConverter : Converter<Csv, JsonElement> {
    override fun from(value: Csv): JsonElement {
        val csvFile = File(value)
        require(csvFile.exists() && csvFile.isFile) { "File $value does not exist or is not a valid file" }

        val lines = csvFile.readLines()
        require(lines.isNotEmpty()) { "File $value is empty" }

        val headers = lines.first().split(",").map { it.trim() }
        val data = lines.drop(1).map { it.split(",").map { field -> field.trim() } }

        val jsonArray =
            JsonArray(
                data.map { row ->
                    buildJsonObject {
                        headers.forEachIndexed { idx, header ->
                            put(header, JsonPrimitive(row.getOrElse(idx) { "" }))
                        }
                    }
                },
            )

        return jsonArray
    }

    override fun to(value: JsonElement): Csv {
        val jsonArray = value.jsonArray
        require(jsonArray.isNotEmpty()) { "JSON array is empty" }

        val headers = jsonArray.first().jsonObject.keys
        return buildString {
            append(headers.joinToString(","))
            appendLine()
            jsonArray.forEach { jsonElement ->
                val row =
                    headers.joinToString(",") { key ->
                        jsonElement.jsonObject[key]?.jsonPrimitive?.contentOrNull ?: ""
                    }
                append(row)
                appendLine()
            }
        }
    }
}
