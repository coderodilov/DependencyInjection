package uz.coderodilov.dependencyinjection.api

import retrofit2.http.GET
import uz.coderodilov.dependencyinjection.model.Post

/* 
* Created by Coder Odilov on 13/07/2023
*/

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}