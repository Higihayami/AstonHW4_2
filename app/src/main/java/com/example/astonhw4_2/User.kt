package com.example.astonhw4_2

import java.io.Serializable

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    var photoUrl: String
) : Serializable
