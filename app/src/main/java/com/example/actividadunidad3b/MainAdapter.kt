package com.example.actividadunidad3b

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class MainAdapter(private val mDataSet: List<PlayList>, var onClick: (PlayList) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.listas, parent, false)
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = mDataSet.get(position)
        data.let { holder.bindItems(it) }
        holder.itemView.setOnClickListener {
            onClick(data)
            //if (data)
              //  holder.mytexto.text = "\uFEFF\uD83D\uDCA5\uFEFF"
            //else
              //  holder.mytexto.text = "\uFEFF\uD83D\uDEA9\uFEFF "
        }
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    inner class MainViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        val mytexto = v.findViewById<TextView>(R.id.miTexto)
        val miFoto = v.findViewById<ImageView>(R.id.miImagen)
        val seguidores = v.findViewById<TextView>(R.id.miTextoSeguidores)
        fun bindItems(data: PlayList) {
            //mytexto.text = data
            mytexto.text = data.nombrePlayList
            seguidores.text = data.seguidores + " seguidores"
            Glide.with(miFoto.context).load(data.fotoAlbum).into(miFoto)
        }
    }
}