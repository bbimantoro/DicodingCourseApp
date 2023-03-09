package com.academy.bangkit.dicodingcourses.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.academy.bangkit.dicodingcourses.databinding.ActivityDetailBinding
import com.academy.bangkit.dicodingcourses.datasource.Course

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Detail Course"
        }

        val data = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_COURSE, Course::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_COURSE)
        }

        if (data != null) {
            binding.apply {
                tvItemDetailName.text = data.name
                tvItemDetailDesc.text = data.desc
                ivItemDetailPhoto.setImageResource(data.photo)
                tvSyllabusContent.text = data.syllabus
            }
            binding.btnEnroll.setOnClickListener {
                Toast.makeText(this, "Anda terdaftar di kelas " + data.name, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }


}