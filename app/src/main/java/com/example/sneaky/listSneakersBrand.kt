package com.example.sneaky


import android.content.Context
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class listSneakersBrand (var context: Context?, var type: ArrayList<Sneakers>): BaseAdapter() {
    private class ViewHolder(row: View?){
        var txtName: TextView
        var imgLogo: ImageView

        init {
            this.txtName = row?.findViewById(R.id.sneakers_text) as TextView
            this.imgLogo = row?.findViewById(R.id.sneakers_img) as ImageView
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewHolder: ViewHolder
        if(convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.fragment_list_brand,parent,false)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var sneaker:Sneakers = getItem(position) as Sneakers
        viewHolder.txtName.text = sneaker.name
        viewHolder.imgLogo.setImageResource(sneaker.image)
        return view as View

    }

    override fun getItem(position: Int): Any {
        return type.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return type.count();
    }
}