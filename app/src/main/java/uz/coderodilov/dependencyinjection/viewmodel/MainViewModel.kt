package uz.coderodilov.dependencyinjection.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.coderodilov.dependencyinjection.database.PostEntity
import uz.coderodilov.dependencyinjection.repository.PostRepository
import uz.coderodilov.dependencyinjection.utility.NetworkResult
import javax.inject.Inject

/* 
* Created by Coder Odilov on 13/07/2023
*/

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PostRepository) : ViewModel(){
    fun getAllPosts() : LiveData<NetworkResult<List<PostEntity>>>{
        return repository.getAllPosts()
    }

}