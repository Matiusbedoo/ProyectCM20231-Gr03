package co.edu.udea.proyecto20231_gr03.domain

import android.net.Uri

data class FoodPlate(
    val restaurantEmail: String,
    val name: String,
    val price: Double,
    val description: String,
    val Image: Uri?
)