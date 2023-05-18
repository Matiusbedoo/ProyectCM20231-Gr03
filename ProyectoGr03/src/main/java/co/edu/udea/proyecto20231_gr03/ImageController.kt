package co.edu.udea.proyecto20231_gr03

import android.content.Intent

object ImageController {
    fun selectPhotoFromGallery(activity: NewFoodFragment, code: Int){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        activity.startActivityForResult(intent, code)
    }
}