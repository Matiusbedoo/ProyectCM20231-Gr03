package co.edu.udea.proyecto20231_gr03.data.service

import android.net.Uri
import co.edu.udea.proyecto20231_gr03.domain.FoodPlate
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class FoodPlateRepository @Inject constructor(
    db: FirebaseFirestore, private val storage: FirebaseStorage
) {
    private val collection = db.collection("FoodPlate")


    fun save(plate: FoodPlate) {
        collection.add(plate)
            .addOnSuccessListener { documentReference ->
                saveImage(documentReference.id, plate.Image)
            }

    }

    private fun saveImage(name: String, file: Uri?) {
        val folder = storage.reference.child("ImagePlateFood")
        var fileToSave = file ?: Uri.parse("drawable/hamburguesa.png")
        val fileRef = folder.child(name + fileToSave.lastPathSegment)
        fileRef.putFile(fileToSave)
    }
}