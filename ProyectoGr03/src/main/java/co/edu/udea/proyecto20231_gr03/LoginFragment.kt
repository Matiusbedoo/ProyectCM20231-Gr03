package co.edu.udea.proyecto20231_gr03

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginFragment : Fragment() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup(view)

    }

    private fun setup(view: View) {
        etEmail = view.findViewById(R.id.emailLogin)
        etPassword = view.findViewById(R.id.passwordLogin)

        auth = FirebaseAuth.getInstance()

        loginButton = view.findViewById(R.id.enterButton)

        loginButton.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(view.context, email, password)
            } else {
                Toast.makeText(
                    view.context,
                    "Por favor, completa todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val registerText = view.findViewById<TextView>(R.id.registerTextView)

        registerText.setOnClickListener {
            //Descomentar para mostrar navegación entre manejador de comidas y comentar el otro o viceversa
            findNavController().navigate(R.id.action_loginFragment_to_manageFood)
            //findNavController().navigate(R.id.action_loginFragment_to_tipoUsuarioFragment)
        }
    }

    private fun loginUser(context: Context, email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val user: FirebaseUser? = auth.currentUser
                Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_homeClientFragment)
            } else {
                Toast.makeText(
                    context,
                    "Error en el inicio de sesión: ${it.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}