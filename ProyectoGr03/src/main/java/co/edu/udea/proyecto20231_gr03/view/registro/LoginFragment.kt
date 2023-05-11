package co.edu.udea.proyecto20231_gr03.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.edu.udea.proyecto20231_gr03.R
import co.edu.udea.proyecto20231_gr03.data.service.AuthService
import co.edu.udea.proyecto20231_gr03.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    public lateinit var auth: AuthService
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

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
            auth.signIn(email, password) { success, error ->
                if (success) {
                    findNavController().navigate(R.id.action_loginFragment_to_homeClientFragment)
                } else {
                    Toast.makeText(
                        context,
                        "Error en el inicio de sesión: $error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            Toast.makeText(
                context,
                "Por favor, completa todos los campos",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}