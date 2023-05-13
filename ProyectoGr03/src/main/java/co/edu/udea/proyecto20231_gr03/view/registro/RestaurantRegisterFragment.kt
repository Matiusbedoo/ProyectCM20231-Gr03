package co.edu.udea.proyecto20231_gr03.view.registro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.edu.udea.proyecto20231_gr03.R
import co.edu.udea.proyecto20231_gr03.data.service.RestaurantRepository
import co.edu.udea.proyecto20231_gr03.data.service.UserRepository
import co.edu.udea.proyecto20231_gr03.databinding.FragmentRestaurantRegisterBinding
import co.edu.udea.proyecto20231_gr03.domain.Restaurant
import co.edu.udea.proyecto20231_gr03.domain.User
import co.edu.udea.proyecto20231_gr03.domain.UserType
import co.edu.udea.proyecto20231_gr03.helpers.SerializeHelper
import co.edu.udea.proyecto20231_gr03.helpers.UserAlertHelper
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RestaurantRegisterFragment : Fragment() {

    private var _binding: FragmentRestaurantRegisterBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var restaurantRepository: RestaurantRepository


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRestaurantRegisterBinding.inflate(inflater, container, false)

        setup()
        return binding.root

    }


    private fun setup() {

        val restaurant = SerializeHelper.deserializeFromBundle<Restaurant>(arguments)

        if (restaurant != null) {
            binding.restaurantEmailRegister.setText(restaurant.email)
            binding.restaurantEmailRegister.isEnabled = false
            binding.registerRestaurantButton.setOnClickListener {
                val email = binding.restaurantEmailRegister.text.toString()
                val password = binding.restaurantPasswordRegister.text.toString()
                var passwordConfirm = binding.restaurantPasswordConfirmRegister.text.toString()




                if (email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()) {
                    val user = User(email, UserType.Restaurant)
                    if (password == passwordConfirm) {
                        userRepository.registerUser(user, password) {
                            if (it) {
                                restaurantRepository.saveRestaurant(restaurant)
                                findNavController().navigate(R.id.action_restaurantRegisterFragment_to_homeRestaurantFragment)
                            } else {
                                UserAlertHelper.showErrorDialog(
                                    requireContext(),
                                    "Registro no exitoso"
                                )
                            }
                        }
                    } else {
                        UserAlertHelper.showErrorDialog(
                            requireContext(),
                            "Las contraseñas no coinciden"
                        )
                        binding.restaurantPasswordRegister.setText("")
                        binding.restaurantPasswordConfirmRegister.setText("")
                    }
                } else {
                    UserAlertHelper.showErrorDialog(
                        requireContext(),
                        getString(R.string.incomplete_fields)
                    )
                }
            }
        } else {
            findNavController().popBackStack()
        }
    }


}