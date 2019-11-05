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
class ModelChoose : Fragment() {
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

        var title = arguments?.getString("modelChoose")!!.split("+")[0]
        val content = SpannableString(title)
        content.setSpan(UnderlineSpan(), 0, title.toString().length, 0)
        binding.titleModel.setText(content)
        setData(title)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var title = arguments?.getString("modelChoose")!!.split("+")[0]
        (activity as AppCompatActivity).supportActionBar?.title = title

    }

    private fun setData(title: String?): ArrayList<BandDetail> {
        Log.i("modelChooseLog", title)
        var bandSp = arguments?.getString("modelChoose")!!.split("+")[1]
        var image = ""
        var array: ArrayList<BandDetail> = ArrayList()
        var map = HashMap<String, Int>();
        var count = 0

        map["Air max"] = R.string.air_max
        map["original"] = R.string.original
        if (bandSp == "nike") {
            if (title == "Air max") {
                array.add(BandDetail(resources.getText(map.get(title)!!).toString()));
                binding.dataModel.text = resources.getText(map.get(title)!!)
                binding.imageInsert.setImageResource(R.drawable.airmax)
            } else {
                model.all.observe(this, Observer { t ->
                    t.forEach {
                        if (it.sneakyCategory == "nike") {
                            Log.i("nike2", it.sneakyCategory)
                            array.add(BandDetail(it.sneakyDetail));
                            Log.d("arraynike", array[0].title)
                            binding.dataModel.text = it.sneakyDetail
                            image = it.sneakypicture
                            getActivity()?.applicationContext?.let {
                                Glide.with(it)
                                    .load(image)
                                    .into(binding.imageInsert)
                            }
                        }
                        count++
                        if (count == t.size) {
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
                    }
                })
            }
        } else if (bandSp == "adidas") {
            if (title == "original") {
                array.add(BandDetail(resources.getText(map.get(title)!!).toString()));
                binding.dataModel.text = resources.getText(map.get(title)!!)
                binding.imageInsert.setImageResource(R.drawable.adidas_boot)
            } else {
                model.all.observe(this, Observer { t ->
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
                        count++
                        if (count == t.size) {
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
                    }
                })
            }
        } else if (bandSp == "vans") {
            model.all.observe(this, Observer { t ->
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
                    count++
                    if (count == t.size) {
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
                }
            })

        } else if (bandSp == "converse") {
            model.all.observe(this, Observer { t ->
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
                    count++
                    if (count == t.size) {
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
                }
            })

        } else if (bandSp == "birkenstock") {
            model.all.observe(this, Observer { t ->
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
                    count++
                    if (count == t.size) {
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
                }
            })

        }
        return array
    }
}
