package uz.coderodilov.dependencyinjection.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/* 
* Created by Coder Odilov on 13/07/2023
*/

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun casheAllPosts(list: List<PostEntity>)

    @Query("SELECT * FROM PostEntity")
    suspend fun getAllPosts() : List<PostEntity>
}