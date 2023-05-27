package co.edu.udea.proyecto20231_gr03.view.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.edu.udea.proyecto20231_gr03.R
import co.edu.udea.proyecto20231_gr03.databinding.FragmentFoodsBinding

class FoodsFragment : Fragment() {
    private var _binding: FragmentFoodsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFoodsBinding.inflate(inflater, container, false)

        binding.imageFood1.setOnClickListener {
            findNavController().navigate(R.id.action_foods_to_manage)
        }

        binding.btnAddFood.setOnClickListener {
            findNavController().navigate(R.id.action_foods_to_newFood)
        }

        return binding.root
    }

}