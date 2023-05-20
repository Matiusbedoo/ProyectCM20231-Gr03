package co.edu.udea.proyecto20231_gr03.view.registro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.edu.udea.proyecto20231_gr03.R
import co.edu.udea.proyecto20231_gr03.data.service.UserRepository
import co.edu.udea.proyecto20231_gr03.databinding.FragmentLoginBinding
import co.edu.udea.proyecto20231_gr03.domain.User
import co.edu.udea.proyecto20231_gr03.domain.UserType
import co.edu.udea.proyecto20231_gr03.helpers.SerializeHelper
import co.edu.udea.proyecto20231_gr03.helpers.UserAlertHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var userRepository: UserRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

//        userRepository.getUserAuthenticated { user ->
//            if (user != null) {
//                ingress(user)
//            }
//        }

        binding.enterButton.setOnClickListener {
            logIn()
        }

        binding.registerTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_tipoUsuarioFragment)
        }

        return binding.root
    }

    private fun logIn() {
        val email = binding.emailLogin.text.toString()
        val password = binding.passwordLogin.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            userRepository.signIn(email, password) { user ->
                if (user != null) {
                    ingress(user)
                } else {
                    UserAlertHelper.showErrorDialog(
                        requireContext(),
                        getString(R.string.wrong_login)
                    )
                    binding.passwordLogin.setText("")
                }
            }
        } else {
            UserAlertHelper.showErrorDialog(requireContext(), getString(R.string.incomplete_fields))
        }
    }

    private fun ingress(user: User) {
        val bundle = SerializeHelper.serializeInBundle(user)
        when (user.type) {
            UserType.Client -> {
                findNavController().navigate(
                    R.id.action_loginFragment_to_homeClientFragment,
                    bundle
                )
            }

            UserType.Restaurant -> {
                findNavController().navigate(
                    R.id.action_loginFragment_to_homeRestaurantFragment,
                    bundle
                )
            }
        }

    }

}