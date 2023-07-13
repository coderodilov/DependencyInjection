package uz.coderodilov.dependencyinjection.api

import uz.coderodilov.dependencyinjection.model.Post
import javax.inject.Inject

/* 
* Created by Coder Odilov on 13/07/2023
*/

class PostRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllPosts() : List<Post>{
        return apiService.getPosts()
    }
}