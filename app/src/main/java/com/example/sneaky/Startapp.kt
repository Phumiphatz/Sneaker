package com.example.sneaky


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.sneaky.databinding.FragmentStartappBinding


/**
 * A simple [Fragment] subclass.
 */
class Startapp : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentStartappBinding>(
            inflater,
            R.layout.fragment_startapp, container, false
        )
        binding.btnStartApp.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_startapp_to_typeChoose)
        }
        return binding.root
    }



}
