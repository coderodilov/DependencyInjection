package uz.coderodilov.dependencyinjection.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/* 
* Created by Coder Odilov on 13/07/2023
*/

@Entity
data class PostEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
    val cachedTime:Long
)