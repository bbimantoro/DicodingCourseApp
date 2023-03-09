package com.academy.bangkit.dicodingcourses.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.academy.bangkit.dicodingcourses.R
import com.academy.bangkit.dicodingcourses.databinding.ItemRowCourseBinding
import com.academy.bangkit.dicodingcourses.datasource.Course
import com.bumptech.glide.Glide

class ListCourseAdapter(
    private val listCourse: ArrayList<Course>,
    private val onClick: (Course) -> Unit
) :
    RecyclerView.Adapter<ListCourseAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemRowCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listCourse.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listCourse[position]
        holder.bind(data)
    }

    inner class ListViewHolder(private var binding: ItemRowCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Course) {
            Glide.with(itemView.context)
                .load(data.photo)
                .placeholder(R.drawable.baseline_broken_image_24)
                .into(binding.ivItemPhoto)

            binding.apply {
                tvItemName.text = data.name
                tvItemDesc.text = data.desc
            }

            itemView.setOnClickListener {
                onClick(data)
            }
        }
    }
}