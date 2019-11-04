package com.example.sneaky


import android.content.ActivityNotFoundException
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.example.sneaky.databinding.FragmentAboutmeBinding

/**
 * A simple [Fragment] subclass.
 */
class Aboutme : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAboutmeBinding>(inflater,
            R.layout.fragment_aboutme,container,false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (menu != null) {
            if (inflater != null) {
                super.onCreateOptionsMenu(menu, inflater)
            }
        }
        inflater?.inflate(R.menu.shared, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shareMenu -> onShare()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun onShare() {
        val shareIntent = ShareCompat.IntentBuilder.from(getActivity())
            .setText("PHUMIPHAT WONGCHUEA")
            .setType("text/plain")
            .intent

        try {
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {

        }
    }
}
