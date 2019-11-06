package com.example.sneaky


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.sneaky.databinding.FragmentBrandSelectedBinding
import com.example.waterreview.listSneakersModel
import kotlinx.android.synthetic.main.fragment_brand_selected.*

/**
 * A simple [Fragment] subclass.
 */
class modelSelected : Fragment() {
    private lateinit var model: dataViewModel
    private lateinit var binding: FragmentBrandSelectedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentBrandSelectedBinding>(
            inflater,
            R.layout.fragment_brand_selected, container, false
        )
        model = ViewModelProviders.of(this).get(dataViewModel::class.java)
        var getModel = arguments?.getString("brand")
        var array: ArrayList<SneakersBand> = onModel(getModel)

        binding.listBand.adapter = listSneakersModel(getActivity()?.applicationContext, array);
        (activity as AppCompatActivity).supportActionBar?.title = getModel

        binding.listBand.setOnItemClickListener { parent, view, position, id ->
            view.findNavController()
                .navigate(modelSelectedDirections.actionBrandSelectedToModelChoose(array[position].name + "+" + array[position].model))
        }


        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item!!,
            view!!.findNavController()
        )
                || super.onOptionsItemSelected(item)
    }

    private fun onModel(type: String?): ArrayList<SneakersBand> {
        var count = 0
        var array: ArrayList<SneakersBand> = ArrayList()
        if (type == "nike") {
            array.add(SneakersBand("Air max", type));
        } else if (type == "adidas") {
            array.add(SneakersBand("original", type));
        }
        model.all.observe(this, Observer { t ->
            t.forEach {
                if (it.sneakyCategory == "nike") {
                    if (type == "nike") {
                        array.add(SneakersBand("${it.sneakyBand}", type))
                    }

                } else if (it.sneakyCategory == "vans") {
                    if (type == "vans") {
                        array.add(SneakersBand("${it.sneakyBand}", type))
                    }

                } else if (it.sneakyCategory == "converse") {
                    if (type == "converse") {
                        array.add(SneakersBand("${it.sneakyBand}", type))
                    }

                } else if (it.sneakyCategory == "adidas") {
                    if (type == "adidas") {
                        array.add(SneakersBand("${it.sneakyBand}", type))
                    }

                } else if (it.sneakyCategory == "birkenstock") {
                    if (type == "birkenstock") {
                        array.add(SneakersBand("${it.sneakyBand}", type))
                    }

                }
                count++
            }
            if (count == t.size) {
                list_band.adapter =
                    listSneakersModel(getActivity()?.applicationContext, array);
            }

        })
        return array
    }

}
