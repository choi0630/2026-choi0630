package com.example.console.util

import com.example.console.data.model.ItemType
import com.example.console.data.model.Priority
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun nowText(): String =
    LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("MM-dd HH:mm"))

fun String?.toIntOrNullSafe():Int? = this?.trim()?.toIntOrNull()

fun readIntOrNull(): Int? {
    print("메뉴 선택 : ")
    return readlnOrNull().toIntOrNullSafe()
}

fun readNonBlankLine(prompt: String) : String {
    while(true){
        print(prompt)
        val line = readlnOrNull()?.trim()
        if(!line.isNullOrEmpty()) return line
        println("빈 값은 입력할 수 없습니다. 다시 입력해 주세요.")
    }
}

fun readYesNo(prompt: String): Boolean{
    while (true){
        val v = readNonBlankLine(prompt)
        if(v.equals("y", ignoreCase = true)) return true
        if(v.equals("n", ignoreCase = true)) return false
        println("y 또는 n을 입력하세요.")
    }
}


fun readPriorityOrNull(prompt: String): Priority?{
    print(prompt)
    val raw = readlnOrNull()?.trim()
    if(raw.isNullOrEmpty()) return null
    return Priority.fromInput(raw) ?: run{
        println("{P1, P2, P3 중 하나를 입력하세요.")
        readPriorityOrNull(prompt)
    }
}


fun readItemType(prompt: String): ItemType{
    while(true){
        val raw = readNonBlankLine(prompt)
        val result = ItemType.fromInput(raw)
        if(result!=null) return result
        println("TASK 또는 NOTE를 입력해 주세요.")
    }
}
