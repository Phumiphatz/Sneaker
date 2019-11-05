package com.example.sneaky


import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.sneaky.databinding.FragmentTypeChooseBinding

/**
 * A simple [Fragment] subclass.
 */
class TypeChoose : Fragment() {
    private lateinit var binding: FragmentTypeChooseBinding
    private lateinit var sneakers: Sneakers

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_type_choose, container, false
        )

        var array: ArrayList<Sneakers> = set()
        binding.listViewData.adapter = listSneakers(getActivity()?.applicationContext, array);

        binding.listViewData.setOnItemClickListener { parent, view, position, id ->

            view.findNavController()
                .navigate(TypeChooseDirections.actionTypeChooseToBrandSelected(array[position].name))
        }

        (activity as AppCompatActivity).supportActionBar?.title = "รองเท้า"
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


    private fun set(): ArrayList<Sneakers> {

        var array: ArrayList<Sneakers> = ArrayList()
        array.add(Sneakers("nike", R.drawable.nike));
        array.add(Sneakers("vans", R.drawable.vanslogo));
        array.add(Sneakers("converse", R.drawable.logoconverse));
        array.add(Sneakers("adidas", R.drawable.logoadidas));
        array.add(Sneakers("birkenstock", R.drawable.birkenstocklogo));
        return array
    }



}
