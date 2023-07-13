package uz.coderodilov.dependencyinjection.database

import javax.inject.Inject

/* 
* Created by Coder Odilov on 13/07/2023
*/

class PostLocalDataSource @Inject constructor(private val postDao: PostDao) {
    suspend fun cashePosts(list: List<PostEntity>) {
        postDao.casheAllPosts(list)
    }

    suspend fun getAllPosts(): List<PostEntity> {
        return postDao.getAllPosts()
    }
}