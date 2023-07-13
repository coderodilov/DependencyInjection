package uz.coderodilov.dependencyinjection.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.coderodilov.dependencyinjection.database.PostEntity
import uz.coderodilov.dependencyinjection.databinding.PostItemBinding

/* 
* Created by Coder Odilov on 13/07/2023
*/

class PostRvAdapter(private val list: List<PostEntity>) : RecyclerView.Adapter<PostRvAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: PostItemBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(post: PostEntity){
            binding.tvTitle.text = post.title
            binding.tvBody.text = post.body


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

}