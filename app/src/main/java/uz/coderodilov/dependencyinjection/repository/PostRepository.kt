package uz.coderodilov.dependencyinjection.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import uz.coderodilov.dependencyinjection.api.PostRemoteDataSource
import uz.coderodilov.dependencyinjection.database.PostEntity
import uz.coderodilov.dependencyinjection.database.PostLocalDataSource
import uz.coderodilov.dependencyinjection.model.Post
import uz.coderodilov.dependencyinjection.utility.NetworkHelper
import uz.coderodilov.dependencyinjection.utility.NetworkResult
import java.lang.Exception
import javax.inject.Inject

/* 
* Created by Coder Odilov on 13/07/2023
*/

class PostRepository @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val localDataSource: PostLocalDataSource,
    private val remoteDataSource: PostRemoteDataSource
) {
    private val posts = MutableLiveData<NetworkResult<List<PostEntity>>>()

    init {
        getPosts()
    }

    private fun getPosts(){
        if (networkHelper.isNetworkConnected()){
            CoroutineScope(Dispatchers.IO).launch {
                posts.postValue(NetworkResult.loading())
                try {
                    coroutineScope {
                        val deferredResult: Deferred<List<Post>> = async { remoteDataSource.getAllPosts() }
                        val list = deferredResult.await()

                        val cacheList:MutableList<PostEntity> = ArrayList()

                        for (post in list){
                            val postEntity = PostEntity(post.id, post.userId, post.title, post.body, System.currentTimeMillis())
                            cacheList.add(postEntity)
                        }

                        localDataSource.cashePosts(cacheList)

                        posts.postValue(NetworkResult.success(cacheList))
                    }

                }
                catch (e:Exception){
                    posts.postValue(NetworkResult.error(e.message ?: "Error occurred"))
                    Log.d("TTT", e.message.toString())
                }
            }
        }
        else{
            CoroutineScope(Dispatchers.IO).launch {
                posts.postValue(NetworkResult.success(localDataSource.getAllPosts()))
            }
        }
    }

    fun getAllPosts() : LiveData<NetworkResult<List<PostEntity>>>{
        return posts
    }
}