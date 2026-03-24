package com.example.console.ui

import com.example.console.data.repository.ItemRepository
import com.example.console.service.ItemManager
import com.example.console.util.enableUtf8Console
import com.example.console.util.readIdOrNull
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
            3 -> handleSearch(service)
            4 -> handleToggle(service)
            5 -> handleDelete(service)
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

private fun handleSearch(service: ItemManager) {
    val keyword = readNonBlankLine("검색 키워드: ")
    val results = service.search(keyword)

    if (results.isEmpty()) {
        println("검색 결과가 없습니다.")
        return
    }

    println("----- 검색 결과 (${results.size}) -----")
    results.forEach { item ->
        println("id=${item.id} / ${item.type} / done=${item.done} / title=${item.title}")
    }
}

fun printAllItems(service: ItemManager){
    val items = service.list(true)
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
private fun handleToggle(service: ItemManager) {
    printAllItems(service)
    println("--------------------------------------")
    val id = readIdOrNull("토글할 id: ")
    if (id == null) {
        println("숫자를 입력해 주세요.")
        return
    }
    val ok = service.toggleDone(id)
    println(if (ok) "토글 완료" else "해당 id가 없습니다.")
}

private fun handleDelete(service: ItemManager) {
    printAllItems(service)
    println("--------------------------------------")
    val id = readIdOrNull("삭제할 id: ")
    if (id == null) {
        println("숫자를 입력해 주세요.")
        return
    }
    val ok = service.delete(id)
    println(if (ok) "삭제 완료" else "해당 id가 없습니다.")
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