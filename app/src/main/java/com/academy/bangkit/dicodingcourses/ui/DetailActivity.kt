package com.academy.bangkit.dicodingcourses.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.academy.bangkit.dicodingcourses.R
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
                btnEnroll.setOnClickListener {
                    Toast.makeText(
                        it.context,
                        "Anda terdaftar di kelas " + data.name,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val intentShare = Intent.createChooser(Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Cek selengkapnya di tautan berikut: https://www.dicoding.com/")
                    type = "text/plain"
                }, null)
                startActivity(intentShare)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }


}