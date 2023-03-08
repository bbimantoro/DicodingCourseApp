package com.academy.bangkit.dicodingcourses.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.academy.bangkit.dicodingcourses.databinding.ActivityDetailBinding
import com.academy.bangkit.dicodingcourses.datasource.Course

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ivItemPhoto = binding.ivItemDetailPhoto
        val tvItemName = binding.tvItemDetailName
        val tvItemDesc = binding.tvItemDetailDesc

        val data = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_COURSE, Course::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_COURSE)
        }

//        tvItemName.text = data?.name.toString()
//        tvItemDesc.text = data?.desc.toString()
//        ivItemPhoto.setImageResource(data?.photo!!.toInt())

    }

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }
}