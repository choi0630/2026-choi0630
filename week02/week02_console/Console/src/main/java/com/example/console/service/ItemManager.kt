package com.example.console.service

import com.example.console.data.model.Item
import com.example.console.data.model.ItemType
import com.example.console.data.model.Priority
import com.example.console.data.repository.ItemRepository

class ItemManager (val repo: ItemRepository){

    fun add(type: ItemType, title:String, priority: Priority?): Item {
        return repo.create {id->
            Item(id=id, type=type, title = title, priority=priority)
        }
    }

    fun list(showDone: Boolean):List<Item>{
        val all = repo.findAll()
        return if (showDone) all else all.filter { !it.done }
    }

    fun search(keyword:String):List<Item> =
        repo.findAll()
            .filter{it.title.contains(keyword, ignoreCase = true)}
            .sortedBy { it.id }


    fun delete(id:Int): Boolean = repo.deleteById(id)

    fun toggleDone(id:Int): Boolean{
        val item = repo.findById(id) ?: return false
        item.done = !item.done
        return true
    }
}