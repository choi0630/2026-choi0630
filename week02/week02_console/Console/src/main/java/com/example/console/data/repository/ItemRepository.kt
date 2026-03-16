package com.example.console.data.repository

import com.example.console.data.model.Item

class ItemRepository {
    val items: MutableList<Item> = mutableListOf()
    var nextId: Int = 1

    fun create(factory: (id: Int) -> Item): Item {
        val item = factory(nextId++)
        items.add(item)
        return item
    }

    fun findAll(): List<Item> = items.toList()

    fun findById(id: Int): Item? = items.firstOrNull { it.id == id }

    fun deleteById(id: Int): Boolean = items.removeAll { it.id == id }

}