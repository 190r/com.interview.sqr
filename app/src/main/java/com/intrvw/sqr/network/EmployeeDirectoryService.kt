package com.intrvw.sqr.network

import com.intrvw.sqr.data.Employee
import retrofit2.Call
import retrofit2.http.GET

interface EmployeeDirectoryService {

    @GET("employees.json")
    suspend fun getEmployeeInfos(): Employee

}