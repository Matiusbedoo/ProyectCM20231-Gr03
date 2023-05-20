package co.edu.udea.proyecto20231_gr03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.edu.udea.proyecto20231_gr03.data.service.ClientRepository
import co.edu.udea.proyecto20231_gr03.databinding.FragmentHomeClientBinding
import co.edu.udea.proyecto20231_gr03.domain.User
import co.edu.udea.proyecto20231_gr03.helpers.SerializeHelper
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeClientFragment : Fragment() {

    private var _binding: FragmentHomeClientBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var clientRepository: ClientRepository

    lateinit var user: User
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeClientBinding.inflate(inflater, container, false)
        user = SerializeHelper.deserializeFromBundle<User>(arguments)!!

        setup()

        return binding.root
    }

    private fun setup() {

        clientRepository.getClient(user.email) {

            binding.welcomeText.text = String.format(getString(R.string.greetings), it.name)
        }

    }

}