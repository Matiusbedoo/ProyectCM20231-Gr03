package co.edu.udea.proyecto20231_gr03

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import co.edu.udea.proyecto20231_gr03.databinding.FragmentNewFoodBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewFoodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewFoodFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val SELECTT_ACTIVITY = 1
    private var imageUri: Uri? = null
    private lateinit var bindingNewFood: FragmentNewFoodBinding
    private val File = 1
    private val database = Firebase.database
    val myRef = database.getReference("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingNewFood = FragmentNewFoodBinding.inflate(inflater, container, false)
        return bindingNewFood.root
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment newFood.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewFoodFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgBtn= view.findViewById<ImageView>(R.id.ImgFromGallery)
        imgBtn.setOnClickListener{
        ImageController.selectPhotoFromGallery(this, SELECTT_ACTIVITY )
       }
        val btnLoadImage =view.findViewById<Button>(R.id.registernewFood)
        btnLoadImage.setOnClickListener {
            fileUpLoad()
        }
    }

    fun fileUpLoad() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "/"
        startActivityForResult(intent, File)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when {
            requestCode == SELECTT_ACTIVITY && resultCode == Activity.RESULT_OK -> {
                imageUri = data!!.data
                bindingNewFood.ImgFromGallery.setImageURI(imageUri)
            }
        }

        if (requestCode == File){
            if (resultCode == Activity.RESULT_OK){
                val FileUri = data!!.data
                val Folder: StorageReference = FirebaseStorage.getInstance().getReference().child("ImageFood")
                val file_name: StorageReference = Folder.child("file" + FileUri!!.lastPathSegment)
                file_name.putFile(FileUri).addOnSuccessListener { uri ->
                    val hasMap = HashMap<String, String>()
                    hasMap["link"] = java.lang.String.valueOf(uri)
                    myRef.setValue(hasMap)
                    Log.d("Mensaje", "Se sbio correctamente")
                }
            }
        }
    }
}