package co.edu.udea.proyecto20231_gr03.data.service

import co.edu.udea.proyecto20231_gr03.domain.Restaurant
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    db: FirebaseFirestore
) {
    private val collection = db.collection("Restaurants")
    fun saveRestaurant(restaurant: Restaurant) {
        collection.document(restaurant.email).set(restaurant)
    }
}