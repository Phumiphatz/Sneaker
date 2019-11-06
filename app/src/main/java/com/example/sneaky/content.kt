package com.example.sneaky


import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.sneaky.databinding.FragmentModelChooseBinding

/**
 * A simple [Fragment] subclass.
 */
class content : Fragment() {
    private lateinit var binding: FragmentModelChooseBinding
    private lateinit var model: dataViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_model_choose, container, false
        )
        model = ViewModelProviders.of(this).get(dataViewModel::class.java)

        var brand = arguments?.getString("modelChoose")!!.split("+")[0]

        binding.titleModel.setText(brand)

        setData(brand)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var model = arguments?.getString("modelChoose")!!.split("+")[0]
        (activity as AppCompatActivity).supportActionBar?.title = model

    }

    private fun setData(model: String?): ArrayList<BandDetail> {
        Log.i("modelChooseLog", model)
        var bandSp = arguments?.getString("modelChoose")!!.split("+")[1]
        var image = ""
        var array: ArrayList<BandDetail> = ArrayList()
        var map = HashMap<String, Int>();
        var count = 0

        map["Air max"] = R.string.air_max
        map["original"] = R.string.original
        if (bandSp == "nike") {
            if (model == "Air max") {
                array.add(BandDetail(resources.getText(map.get(model)!!).toString()));
                binding.dataModel.text = resources.getText(map.get(model)!!)
                binding.imageInsert.setImageResource(R.drawable.airmax)
            } else {
                this.model.all.observe(this, Observer { t ->
                    t.forEach {
                        if (it.sneakyCategory == "nike") {
                            array.add(BandDetail(it.sneakyDetail));
                            binding.dataModel.text = it.sneakyDetail
                            image = it.sneakypicture
                            getActivity()?.applicationContext?.let {
                                Glide.with(it)
                                    .load(image)
                                    .into(binding.imageInsert)
                            }
                        }

                    }
                })
            }
        } else if (bandSp == "adidas") {
            if (model == "original") {
                array.add(BandDetail(resources.getText(map.get(model)!!).toString()));
                binding.dataModel.text = resources.getText(map.get(model)!!)
                binding.imageInsert.setImageResource(R.drawable.adidas_boot)
            } else {
                this.model.all.observe(this, Observer { t ->
                    t.forEach {
                        if (it.sneakyCategory == "adidas") {
                            array.add(BandDetail(it.sneakyDetail));
                            binding.dataModel.text = it.sneakyDetail
                            image = it.sneakypicture
                            getActivity()?.applicationContext?.let {
                                Glide.with(it)
                                    .load(image)
                                    .into(binding.imageInsert)
                            }

                        }

                    }
                })
            }
        } else if (bandSp == "vans") {
            this.model.all.observe(this, Observer { t ->
                t.forEach {
                    if (it.sneakyCategory == "vans") {
                        array.add(BandDetail(it.sneakyDetail));
                        binding.dataModel.text = it.sneakyDetail
                        image = it.sneakypicture
                        getActivity()?.applicationContext?.let {
                            Glide.with(it)
                                .load(image)
                                .into(binding.imageInsert)
                        }
                    }

                }
            })

        } else if (bandSp == "converse") {
            this.model.all.observe(this, Observer { t ->
                t.forEach {
                    if (it.sneakyCategory == "converse") {
                        array.add(BandDetail(it.sneakyDetail));
                        binding.dataModel.text = it.sneakyDetail
                        image = it.sneakypicture
                        getActivity()?.applicationContext?.let {
                            Glide.with(it)
                                .load(image)
                                .into(binding.imageInsert)
                        }
                    }

                }
            })

        } else if (bandSp == "birkenstock") {
            this.model.all.observe(this, Observer { t ->
                t.forEach {
                    if (it.sneakyCategory == "birkenstock") {
                        array.add(BandDetail(it.sneakyDetail));
                        binding.dataModel.text = it.sneakyDetail
                        image = it.sneakypicture
                        getActivity()?.applicationContext?.let {
                            Glide.with(it)
                                .load(image)
                                .into(binding.imageInsert)
                        }
                    }

                }
            })

        }
        return array
    }
}
