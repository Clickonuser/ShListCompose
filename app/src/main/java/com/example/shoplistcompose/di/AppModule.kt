package com.example.shoplistcompose.di

import android.app.Application
import androidx.room.Room
import com.example.shoplistcompose.data.AddItemRepoImpl
import com.example.shoplistcompose.data.AddItemRepository
import com.example.shoplistcompose.data.MainDb
import com.example.shoplistcompose.data.NoteRepoImpl
import com.example.shoplistcompose.data.NoteRepository
import com.example.shoplistcompose.data.ShoppingListRepoImpl
import com.example.shoplistcompose.data.ShoppingListRepository
import com.example.shoplistcompose.datastore.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMainDb(app: Application): MainDb {
        return Room.databaseBuilder(
            app,
            MainDb::class.java,
            "shop_list_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShopRepo(db: MainDb): ShoppingListRepository {
        return ShoppingListRepoImpl(db.shoppingListDao)
    }

    @Provides
    @Singleton
    fun provideAddItemRepo(db: MainDb): AddItemRepository {
        return AddItemRepoImpl(db.addItemDao)
    }

    @Provides
    @Singleton
    fun provideNoteRepo(db: MainDb): NoteRepository {
        return NoteRepoImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(app: Application): DataStoreManager {
        return DataStoreManager(app)
    }
}