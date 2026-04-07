package com.example.console

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    println("202415209 최정수")
    enableUtf8Console()
    val items = ArrayList<String>()
    val maxItems = 50
    while (true) {
        printMenu()
        val choice = readIntOrNull()
        when (choice) {
            1 -> {
                if(items.size >= maxItems){
                    println("저장 공간이 가득 찼습니다.")
                    continue
                }
                val text = readNonBlankLine("추가할 항목을 입력하세요 : ")
                val now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd HH:mm"))
                val saved = "$text $now"
                items.add(saved)
            }
2 -> {
                if(items.isEmpty()){
                    println("등록된 항목이 없습니다.")
                }else{
                    println("------- 전체 목록 -------")
                    for(i in 0 until items.size){
                        println("$i : ${items[i]}")
                    }
                }
            }
            3 -> {
                if(items.isEmpty()) {
                    println("등록된 항목이 없습니다.")
                    continue
                }
                val keyword = readNonBlankLine("검색할 키워드를 입력하세요 : ")
                var found = false
                println("===== 검색 결과 ======")
                for(i in 0 until items.size){
                    if(items[i].contains(keyword)){
                        println("$i: ${items[i]}")
                        found = true
                    }
                }
                if(!found){
                    println("검색 결과가 없습니다.")
                }
            }
            4 -> {
                println("프로그램을 종료합니다.")
                break
            }

            null -> println("숫자를 입력해 주세요.")
            else -> println("1~4 중에서 선택해 주세요.")
        }
    }
}

fun readNonBlankLine(prompt: String) : String {
    while(true){
        print(prompt)
        val line = readlnOrNull()?.trim()
        if(!line.isNullOrEmpty()) return line
        println("빈 값은 입력할 수 없습니다. 다시 입력해 주세요.")
    }
}

fun readIntOrNull(): Int? {
    print("메뉴 선택 : ")
    val raw = readlnOrNull()?.trim()
    if (raw.isNullOrEmpty()) return null
    return raw.toIntOrNull()
}


fun printMenu() {
    println()
    println("===== Smart Task & Note Manager =====")
    println("1. 항목 추가")
    println("2. 전체 목록 보기")
    println("3. 키워드 검색")
    println("4. 종료")
}