package com.academy.bangkit.dicodingcourses.ui

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.academy.bangkit.dicodingcourses.R
import com.academy.bangkit.dicodingcourses.adapter.ListCourseAdapter
import com.academy.bangkit.dicodingcourses.databinding.ActivityMainBinding
import com.academy.bangkit.dicodingcourses.datasource.Course

class MainActivity : AppCompatActivity() {

    private lateinit var rvCourses: RecyclerView
    private val courseList = ArrayList<Course>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvCourses = binding.rvCourses
        rvCourses.setHasFixedSize(true)

        courseList.addAll(getCourses())
        showRecyclerList()
    }

    private fun getCourses(): ArrayList<Course> {
        val dataName = resources.getStringArray(R.array.course_name)
        val dataDesc = resources.getStringArray(R.array.course_desc)
        val dataPhoto = resources.obtainTypedArray(R.array.course_photo)
        val courseList = ArrayList<Course>()
        for (i in dataName.indices) {
            val course = Course(dataName[i], dataDesc[i], dataPhoto.getResourceId(i, -1))
            courseList.add(course)
        }
        return courseList
    }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvCourses.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvCourses.layoutManager = LinearLayoutManager(this)
        }
        val listCourseAdapter = ListCourseAdapter(courseList) { course ->
            val mIntent = Intent(this@MainActivity, DetailActivity::class.java)
            mIntent.putExtra(DetailActivity.EXTRA_COURSE, course)
            startActivity(mIntent)
        }
        rvCourses.adapter = listCourseAdapter
    }


}