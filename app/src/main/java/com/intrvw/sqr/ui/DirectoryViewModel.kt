package com.intrvw.sqr.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intrvw.sqr.data.Employee
import com.intrvw.sqr.data.EmployeeInfo
import com.intrvw.sqr.network.EmployeeDirectoryService
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DirectoryViewModel: ViewModel() {

    val state = mutableStateOf(emptyList<EmployeeInfo>())

    private var employeeDirectory: EmployeeDirectoryService

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://s3.amazonaws.com/sq-mobile-interview/")
            .build()
        employeeDirectory = retrofit.create(EmployeeDirectoryService::class.java)
        getEmployeeList()
    }

    private fun getEmployeeList() {
        viewModelScope.launch(errorHandler) {
            val employees = getRemoteEmployeeList()
            state.value = employees.employeeInfoList
        }
    }

    private suspend fun getRemoteEmployeeList(): Employee {
        return withContext(Dispatchers.IO) {
            employeeDirectory.getEmployeeInfos()
        }
    }

}