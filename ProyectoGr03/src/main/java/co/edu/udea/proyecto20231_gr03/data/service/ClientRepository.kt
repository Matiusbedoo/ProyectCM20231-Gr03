package co.edu.udea.proyecto20231_gr03.data.service

import android.util.Log
import co.edu.udea.proyecto20231_gr03.domain.Client
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import javax.inject.Inject

class ClientRepository @Inject constructor(
    db: FirebaseFirestore
) {

    private val collection = db.collection("Clients")
    fun saveClient(client: Client) {
        collection.document(client.email).set(client)
    }

    fun getClient(email: String, callback: (Client) -> Unit) {
        val doc = collection.document(email)
        doc.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val client = document.toObject<Client>()
                    if (client != null) {
                        callback(client)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d("ClientRepository", "get failed with ", exception)
            }
    }

}