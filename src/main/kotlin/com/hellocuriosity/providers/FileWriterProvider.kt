package com.hellocuriosity.providers

import com.hellocuriosity.data.converters.Csv
import java.io.File

class FileWriterProvider(
    private val fileName: String = "output.csv",
    private val destinationDir: File = File("build/output/copilot/"),
) {
    fun write(data: Csv) {
        if (!destinationDir.exists()) {
            destinationDir.mkdirs()
        }

        val file = File(destinationDir, fileName)
        file.writeText(data)

        println("CSV file created: ${file.absolutePath}")
        println("CSV file generated successfully: $fileName")
    }
}
