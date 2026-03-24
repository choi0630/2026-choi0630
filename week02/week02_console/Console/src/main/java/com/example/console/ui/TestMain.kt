package com.example.console.ui

import com.example.console.data.repository.ItemRepository
import com.example.console.service.ItemManager
import com.example.console.util.enableUtf8Console
import com.example.console.util.readIntOrNull
import com.example.console.util.readItemType
import com.example.console.util.readNonBlankLine
import com.example.console.util.readPriorityOrNull
import com.example.console.util.readYesNo

fun main() {
    enableUtf8Console()

    val repo = ItemRepository()
    val service = ItemManager(repo)

    while (true) {
        printMenu()
        when (readIntOrNull()) {
            1 -> handleAdd(service)
            2 -> handleList(service)
            3 -> {}
            4 -> {}
            5 -> {}
            6 -> {
                println("종료합니다.")
                return
            }
            null -> println("숫자를 입력하세요.")
            else -> println("1~6 중에서 선택하세요.")
        }
    }

}

fun handleAdd(service: ItemManager) {
    val type = readItemType("타입(TASK/NOTE) : ")
    val title = readNonBlankLine("제목 : ")
    val priority = readPriorityOrNull("우선순위(P1/P2/P3) 또는 Enter(스킵) : ")

    val item = service.add(type, title, priority)
    println(item)
}

fun handleList(service: ItemManager) {
    val showDone = readYesNo("완료 항목도 볼까요? (y/n) : ")
    val items = service.list(showDone)
    if (items.isEmpty()) {
        println("등록된 항목이 없습니다.")
        return
    }
    items.forEachIndexed { index, item ->
        val mark = if (item.done) "●" else "○"
        val p = item.priority?.let { "[$it]" } ?: ""
        println("$index) $mark id=${item.id} ${item.type} $p ${item.title} (${item.createdTime})")
    }
}

fun printMenu() {
    println()
    println("===== Smart Task & Note Manager =====")
    println("1. 항목 추가")
    println("2. 전체 목록 보기")
    println("3. 키워드 검색")
    println("4. 완료 토글")
    println("5. 항목 삭제하기")
    println("6. 종료")
}