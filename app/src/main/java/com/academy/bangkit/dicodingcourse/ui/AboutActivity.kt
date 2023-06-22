package com.academy.bangkit.dicodingcourse.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.academy.bangkit.dicodingcourse.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "About Me"
        }

        binding.apply {
            ivPhoto
            tvAuthor
            tvEmail
        }
    }
}