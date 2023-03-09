package com.academy.bangkit.dicodingcourses.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.academy.bangkit.dicodingcourses.R
import com.academy.bangkit.dicodingcourses.adapter.ListCourseAdapter
import com.academy.bangkit.dicodingcourses.databinding.ActivityMainBinding
import com.academy.bangkit.dicodingcourses.datasource.Course

class MainActivity : AppCompatActivity() {

    private val courseList = ArrayList<Course>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rvCourses.setHasFixedSize(true)

        courseList.addAll(getCourses())
        showRecyclerList()
    }

    private fun getCourses(): ArrayList<Course> {
        val dataName = resources.getStringArray(R.array.course_name)
        val dataDesc = resources.getStringArray(R.array.course_desc)
        val dataPhoto = resources.obtainTypedArray(R.array.course_photo)
        val dataSyllabus = resources.getStringArray(R.array.course_syllabus)
        val courseList = ArrayList<Course>()
        for (i in dataName.indices) {
            val course =
                Course(dataName[i], dataDesc[i], dataSyllabus[i], dataPhoto.getResourceId(i, -1))
            courseList.add(course)
        }
        dataPhoto.recycle()
        return courseList
    }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvCourses.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvCourses.layoutManager = LinearLayoutManager(this)
        }
        val listCourseAdapter = ListCourseAdapter(courseList) { course ->
            val mIntent = Intent(this@MainActivity, DetailActivity::class.java)
            mIntent.putExtra(DetailActivity.EXTRA_COURSE, course)
            startActivity(mIntent)
        }
        binding.rvCourses.adapter = listCourseAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val mIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}