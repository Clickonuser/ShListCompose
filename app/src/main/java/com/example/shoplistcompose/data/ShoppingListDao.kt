package com.example.shoplistcompose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingListItem)

    @Delete
    suspend fun deleteItem(item: ShoppingListItem)

    @Query("DELETE FROM add_item WHERE listId = :listId")
    suspend fun deleteAddItems(listId: Int)

    @Query("SELECT * FROM shop_list_name")
    fun getAllItems(): Flow<List<ShoppingListItem>>

    @Transaction
    suspend fun deleteShoppingList(item: ShoppingListItem) {
        item.id?.let {
            deleteAddItems(it)
        }
        deleteItem(item)
    }
}