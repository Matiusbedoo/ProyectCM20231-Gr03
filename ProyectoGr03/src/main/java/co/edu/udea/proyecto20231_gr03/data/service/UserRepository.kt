package co.edu.udea.proyecto20231_gr03.data.service

import android.util.Log
import co.edu.udea.proyecto20231_gr03.domain.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import javax.inject.Inject

class UserRepository @Inject constructor(
    db: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) {

    private val collection = db.collection("Users")
    private fun saveUser(user: User) {
        collection.document(user.email).set(user)
    }

    fun getUserByEmail(email: String, callback: (User?) -> Unit) {
        val doc = collection.document(email)
        doc.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val user = document.toObject<User>()
                    callback(user)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener { exception ->
                Log.d("UserRepository", "get failed with ", exception)
                callback(null)
            }
    }

    fun getUserAuthenticated(callback: (User?) -> Unit) {
        val user = firebaseAuth.currentUser
        if (user != null && user.email != null) {
            getUserByEmail(user.email!!, callback)
        } else {
            callback(null)
        }
    }

    fun signIn(email: String, password: String, callback: (User?) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    getUserByEmail(email!!, callback)
                } else {
                    callback(null)
                }
            }
    }

    fun registerUser(user: User, password: String, onComplete: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(user.email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    saveUser(user)
                    onComplete(true)
                } else {
                    onComplete(false)
                }
            }
    }
}