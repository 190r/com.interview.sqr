package com.intrvw.sqr.data

import android.net.Uri
import com.google.gson.annotations.SerializedName

enum class EmploymentType {FULL_TIME, PART_TIME, CONTRACTOR}

data class Employee(
    @SerializedName("employees")
    val employeeInfoList: List<EmployeeInfo>
)
