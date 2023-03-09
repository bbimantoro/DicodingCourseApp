package com.academy.bangkit.dicodingcourses.datasource

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Course(
    val name: String,
    val desc: String,
    val syllabus: String,
    val photo: Int,
) : Parcelable
