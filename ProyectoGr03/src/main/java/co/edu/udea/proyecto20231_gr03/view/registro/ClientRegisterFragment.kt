package co.edu.udea.proyecto20231_gr03.view.registro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.edu.udea.proyecto20231_gr03.R
import co.edu.udea.proyecto20231_gr03.data.service.ClientRepository
import co.edu.udea.proyecto20231_gr03.data.service.UserRepository
import co.edu.udea.proyecto20231_gr03.databinding.FragmentClientRegisterBinding
import co.edu.udea.proyecto20231_gr03.domain.Client
import co.edu.udea.proyecto20231_gr03.domain.User
import co.edu.udea.proyecto20231_gr03.domain.UserType
import co.edu.udea.proyecto20231_gr03.helpers.SerializeHelper
import co.edu.udea.proyecto20231_gr03.helpers.UserAlertHelper
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ClientRegisterFragment : Fragment() {


    private var _binding: FragmentClientRegisterBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var clientRepository: ClientRepository

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientRegisterBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    private fun setup() {

        binding.registerClientButton.setOnClickListener {
            val name = binding.clientName.text.toString()
            val phone = binding.cellphoneClient.text.toString()
            val email = binding.mailClient.text.toString()
            val password = binding.passwordClient.text.toString()
            val passwordConfirm = binding.confirmPasswordClient.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()) {
                val user = User(email, UserType.Client)
                val client = Client(email, name, phone)
                if (client.validate().errors.isEmpty()) {
                    if (password == passwordConfirm) {
                        userRepository.registerUser(user, password) {
                            if (it) {
                                clientRepository.saveClient(client)
                                val bundle = SerializeHelper.serializeInBundle(user)
                                findNavController().navigate(
                                    R.id.action_clientRegisterFragment_to_homeClientFragment,
                                    bundle
                                )
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
                        binding.passwordClient.setText("")
                        binding.confirmPasswordClient.setText("")
                    }
                } else {
                    UserAlertHelper.showValidationErrorsDialog(
                        requireContext(),
                        client.validate()
                    )
                }
            } else {
                UserAlertHelper.showErrorDialog(
                    requireContext(),
                    getString(R.string.incomplete_fields)
                )
            }

        }
    }
}