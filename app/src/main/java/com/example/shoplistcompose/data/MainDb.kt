package com.example.shoplistcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NoteItem::class, ShoppingListItem::class, AddItem::class],
    version = 1,
    exportSchema = true
)
abstract class MainDb : RoomDatabase() {
    abstract val shoppingListDao: ShoppingListDao
    abstract val addItemDao: AddItemDao
    abstract val noteDao: NoteDao
}