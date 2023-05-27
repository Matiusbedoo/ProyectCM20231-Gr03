package co.edu.udea.proyecto20231_gr03.view.restaurant

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.edu.udea.proyecto20231_gr03.R
import co.edu.udea.proyecto20231_gr03.data.service.FoodPlateRepository
import co.edu.udea.proyecto20231_gr03.data.service.UserRepository
import co.edu.udea.proyecto20231_gr03.databinding.FragmentNewFoodBinding
import co.edu.udea.proyecto20231_gr03.domain.FoodPlate
import co.edu.udea.proyecto20231_gr03.domain.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewFoodFragment : Fragment() {
    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var foodPlateRepository: FoodPlateRepository

    private var imageUri: Uri? = null

    private var _binding: FragmentNewFoodBinding? = null
    private val binding get() = _binding!!

    lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewFoodBinding.inflate(inflater, container, false)

        userRepository.getUserAuthenticated { userAuth ->
            if (userAuth == null) {
                findNavController().navigate(R.id.action_home)
            } else {
                user = userAuth
            }

        }

        binding.ImgFromGallery.setOnClickListener {
            launchIntent()
        }

        binding.registerNewFood.setOnClickListener {
            val name = binding.txtNameFood.text.toString()
            val price = binding.txtPriceFood.text.toString().toDouble()
            val description = binding.txtDescriptionFood.text.toString()

            val plateFood = FoodPlate(user.email, name, price, description, imageUri)
            foodPlateRepository.save(plateFood)

            findNavController().navigate(R.id.action_restaurant_home)
        }

        return binding.root
    }


    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                imageUri = result.data?.data
                binding.ImgFromGallery.setImageURI(imageUri)

            }
        }

    private fun launchIntent() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startForResult.launch(intent)
    }
}