package co.edu.udea.proyecto20231_gr03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val enterButton = view.findViewById<Button>(R.id.enterButton)

        enterButton.setOnClickListener{
            //TODO: Ir al fragment del home
        }

        val registerText= view.findViewById<TextView>(R.id.registerTextView)

        registerText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_tipoUsuarioFragment)
        }
    }
}