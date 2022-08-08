package com.intrvw.sqr.data

import com.google.gson.annotations.SerializedName

data class EmployeeInfo(
    @SerializedName("uuid")
    val id: String,
    @SerializedName("full_name")
    val name: String?,
    @SerializedName("phone_number")
    val phone: String?,
    @SerializedName("email_address")
    val email: String?,
    @SerializedName("biography")
    val bio: String?,
    @SerializedName("photo_url_small")
    val smallIcon: String?,
    @SerializedName("photo_url_large")
    val largeIcon: String?,
    @SerializedName("team")
    val teamName: String?,
    @SerializedName("employee_type")
    val employmentType: String?
) {

    fun getEmploymentType() = employmentType?.let {
        when {
            it.equals(
                EmploymentType.FULL_TIME.toString(),
                true
            ) -> EmploymentType.FULL_TIME
            it.equals(
                EmploymentType.PART_TIME.toString(),
                true
            ) -> EmploymentType.PART_TIME
            else -> EmploymentType.CONTRACTOR
        }
    }

    fun isIncompleteRecord() =
        component2().isNullOrBlank() ||
                component3().isNullOrBlank() ||
                component4().isNullOrBlank() ||
                component5().isNullOrBlank() ||
                component6() == null ||
                component7() == null ||
                component8().isNullOrBlank() ||
                component9().isNullOrBlank()

}
