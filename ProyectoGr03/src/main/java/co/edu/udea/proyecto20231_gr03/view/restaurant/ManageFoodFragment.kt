package co.edu.udea.proyecto20231_gr03.view.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.edu.udea.proyecto20231_gr03.R
import co.edu.udea.proyecto20231_gr03.databinding.FragmentManageFoodBinding

class ManageFoodFragment : Fragment() {

    private var _binding: FragmentManageFoodBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManageFoodBinding.inflate(inflater, container, false)

        binding.imageManageFood.setOnClickListener {
            findNavController().navigate(R.id.action_manageFood_to_foods)
        }

        return binding.root
    }
}