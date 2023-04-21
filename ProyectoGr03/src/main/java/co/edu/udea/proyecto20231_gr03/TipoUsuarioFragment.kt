package co.edu.udea.proyecto20231_gr03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class TipoUsuarioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tipo_usuario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val restaurantButton = view.findViewById<Button>(R.id.restaurantButton)

        restaurantButton.setOnClickListener {
            findNavController().navigate(R.id.action_tipoUsuarioFragment_to_restaurantDataFragment)
        }
        val clientButton = view.findViewById<Button>(R.id.clienteButton)

        clientButton.setOnClickListener {
            findNavController().navigate(R.id.action_tipoUsuarioFragment_to_clientRegisterFragment)
        }
    }
}