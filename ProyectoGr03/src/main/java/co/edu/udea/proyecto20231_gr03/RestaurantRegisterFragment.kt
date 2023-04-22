package co.edu.udea.proyecto20231_gr03

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RestaurantRegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etPasswordConfirm: EditText
    private lateinit var registerButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_register, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {
        auth = FirebaseAuth.getInstance()
        etEmail = view.findViewById(R.id.mailClient)
        etPassword = view.findViewById(R.id.passwordClient)
        etPasswordConfirm = view.findViewById(R.id.confirmPasswordClient)
        registerButton = view.findViewById(R.id.registerClientButton)

        registerButton.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            var passwordConfirm = etPasswordConfirm.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()) {
                if (password == passwordConfirm) {
                    createUser(view.context, email, password)
                }else{
                    Toast.makeText(
                        view.context,
                        "Las contraseñas no coinciden",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    view.context,
                    "Por favor, completa todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun createUser(context: Context, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val user: FirebaseUser? = auth.currentUser
                Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
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