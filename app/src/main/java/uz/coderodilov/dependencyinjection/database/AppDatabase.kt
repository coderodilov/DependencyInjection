package uz.coderodilov.dependencyinjection.database

import androidx.room.Database
import androidx.room.RoomDatabase

/* 
* Created by Coder Odilov on 13/07/2023
*/

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}