package br.com.fiap.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import br.com.fiap.main.ui.theme.TestsTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File

fun spreadsheetToJSON(spreadsheetPath: String): String {
    val workbook = WorkbookFactory.create(File(spreadsheetPath))

    val sheet = workbook.getSheetAt(0)

    val columnNames = mutableMapOf<Int, String>()

    val spreadsheetMap = mutableMapOf<String, MutableList<String>>()

    val headerRow = sheet.getRow(0)

    for (i in 0 until headerRow.lastCellNum) {
        val columnName = headerRow.getCell(i).stringCellValue
        columnNames[i] = columnName
        spreadsheetMap[columnName] = mutableListOf()
    }

    for (rowIndex in 1..sheet.lastRowNum) {
        val row = sheet.getRow(rowIndex)

        for (i in 0 until row.lastCellNum) {
            val columnName = columnNames[i]
            val cellValue = row.getCell(i).stringCellValue
            spreadsheetMap[columnName]?.add(cellValue)
        }
    }

    val stringifiedJSON = Json.encodeToString(spreadsheetMap)

    println(stringifiedJSON)

    return stringifiedJSON
}

fun handleConvert() {
    try {
        val path = "path"
        spreadsheetToJSON(path)
    } catch (e: Exception) {
        println("ERROR:::")
        println(e)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Button(
                        onClick = { handleConvert() }
                    ) {
                        Text(text = "Converter planilha para JSON")
                    }
                }
            }
        }
    }
}