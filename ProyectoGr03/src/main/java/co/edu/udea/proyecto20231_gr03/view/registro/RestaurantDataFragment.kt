package co.edu.udea.proyecto20231_gr03.view.registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import co.edu.udea.proyecto20231_gr03.R
import co.edu.udea.proyecto20231_gr03.databinding.FragmentRestaurantDataBinding
import co.edu.udea.proyecto20231_gr03.domain.Restaurant
import co.edu.udea.proyecto20231_gr03.domain.RestaurantOwner
import co.edu.udea.proyecto20231_gr03.helpers.SerializeHelper
import co.edu.udea.proyecto20231_gr03.helpers.UserAlertHelper
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RestaurantDataFragment : Fragment() {

    private var _binding: FragmentRestaurantDataBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var gson: Gson

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRestaurantDataBinding.inflate(inflater, container, false)
        binding.nextRestaurantButton.setOnClickListener {
            var restaurant = Restaurant(
                binding.mailText.text.toString(),
                binding.restaurantNameText.text.toString(),
                binding.restaurantAddressText.text.toString(),
                binding.restaurantPhoneText.text.toString(),
                RestaurantOwner(
                    binding.ownerNameText.text.toString(),
                    binding.ownerPhoneText.text.toString()
                )
            )

            val bundle = SerializeHelper.serializeInBundle(restaurant)

            if (restaurant.validate().errors.isEmpty()) {
                findNavController().navigate(
                    R.id.action_restaurantDataFragment_to_restaurantRegisterFragment,
                    bundle
                )
            } else {
                UserAlertHelper.showValidationErrorsDialog(
                    requireContext(),
                    restaurant.validate()
                )
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}