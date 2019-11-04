package com.example.waterreview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.sneaky.R
import com.example.sneaky.SneakersBand

class listSneakersBand(var context: Context?, var sneakersBand: ArrayList<SneakersBand>): BaseAdapter() {
    private class ViewHolder(row: View?) {
        var txtName: TextView
        var imgLogo: ImageView

        init {
            this.txtName = row?.findViewById(R.id.list_detail_text) as TextView
            this.imgLogo = row?.findViewById(R.id.img_band) as ImageView

        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewHolder: ViewHolder
        if (convertView == null) {
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.list_band, parent, false)

            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var sneakers: SneakersBand = getItem(position) as SneakersBand
         viewHolder.txtName.text = sneakers.name


        viewHolder.imgLogo.setImageResource(R.drawable.sneaker)
        return view as View

    }

    override fun getItem(position: Int): Any {
        return sneakersBand.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return sneakersBand.count();
    }
}
