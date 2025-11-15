package com.bharat.mupple_app_sketch.auth_feature.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

enum class Gender(val displayName : String){
    MALE("Male"), FEMALE("Female")
}

enum class Colleges(val displayName : String){
    Lovely_Professional_University("Lovely Professional University")
}

data class UserProfileDetails @RequiresApi(Build.VERSION_CODES.O) constructor(
    val gender : Gender? = null,
    val dob : LocalDate = LocalDate.of(2000,1, 1),
    val nickname : String = "",
    val collage : Colleges? = null
)
