package co.edu.udea.proyecto20231_gr03

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.proyecto20231_gr03.domain.Product


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuRestaurantFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuRestaurantFragment : Fragment()  {
    // TODO: Rename and change types of parameters

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_menu_restaurant, container, false)

        // Paso 2: Diseña la interfaz de usuario
        recyclerView = view.findViewById(R.id.recyclerView)



        // Paso 3: Crea una clase para el adaptador del RecyclerView
        adapter = MyAdapter()

        // Paso 4: Obtén los datos de Firebase
        val databaseRef = FirebaseRestaurant.getInstance().getReference("Restaurant/juanse.ramirez91@gmail.com/menu")
        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            fun onDataChange(dataSnapshot: DataSnapshot) {
                val data = ArrayList<Product>()
                for (snapshot in dataSnapshot.children) {
                    val item = snapshot.getValue(Product::class.java)
                    item?.let { data.add(it) }
                }
                adapter.setData(data)
                adapter.notifyDataSetChanged()
            }

            fun onCancelled(databaseError: DatabaseError) {
                // Maneja el error si ocurre
            }
        })

        // Paso 5: Configura el RecyclerView en el Fragment
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        return view
    }
}
   // override fun onCreateView(
      //  inflater: LayoutInflater, container: ViewGroup?,
       // savedInstanceState: Bundle?
    //): View? {
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_menu_restaurant, container, false)
    //}

class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var data: List<Product> = listOf()

    fun setData(data: List<Product>) {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuRestaurantFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuRestaurantFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}