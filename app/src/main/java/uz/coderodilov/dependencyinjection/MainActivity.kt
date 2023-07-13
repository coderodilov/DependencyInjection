package uz.coderodilov.dependencyinjection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import uz.coderodilov.dependencyinjection.app.App
import uz.coderodilov.dependencyinjection.databinding.ActivityMainBinding
import uz.coderodilov.dependencyinjection.ui.adapter.PostRvAdapter
import uz.coderodilov.dependencyinjection.utility.NetworkStatus
import uz.coderodilov.dependencyinjection.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvPostRvAdapter: PostRvAdapter

    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getAllPosts().observe(this){
            when(it.status){
                NetworkStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                NetworkStatus.SUCCES -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    rvPostRvAdapter = PostRvAdapter(it.data ?: emptyList())
                    binding.rvPost.adapter = rvPostRvAdapter
                    val divider = DividerItemDecoration(binding.rvPost.context, RecyclerView.VERTICAL)
                    binding.rvPost.addItemDecoration(divider)
                }
                NetworkStatus.ERROR -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}