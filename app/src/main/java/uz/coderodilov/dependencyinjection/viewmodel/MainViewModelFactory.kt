package uz.coderodilov.dependencyinjection.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.coderodilov.dependencyinjection.repository.PostRepository
import java.lang.IllegalArgumentException

/* 
* Created by Coder Odilov on 13/07/2023
*/

class MainViewModelFactory(private val repository: PostRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Error occurred")
    }
}