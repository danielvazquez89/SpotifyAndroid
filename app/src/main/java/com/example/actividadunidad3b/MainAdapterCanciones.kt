package com.example.actividadunidad3b

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MainAdapterCanciones(private val mDataSet: List<Canciones>, var onClick: (Canciones) -> Unit) :
    RecyclerView.Adapter<MainAdapterCanciones.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_canciones, parent, false)
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = mDataSet.get(position)
       // val mytexto = View.findViewById<TextView>(R.id.tituloCancion)
        var liked = false

        data.let { holder.bindItems(it) }
        holder.mytexto.setOnClickListener {
           onClick(data)
            //if (liked)
               //holder.corazon.setImageDrawable(ContextCompat.getDrawable(requireContext(getActivity()), R.drawable.ic_baseline_favorite_border_24))
            //else
                //holder.corazon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_favorite_24))
        }
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    inner class MainViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        val corazon = v.findViewById<ImageView>(R.id.corazon)
        //val corazonRelleno =
        val mytexto = v.findViewById<TextView>(R.id.tituloCancion)
        val miFotoCancion = v.findViewById<ImageView>(R.id.imagenCancion)
        val artista = v.findViewById<TextView>(R.id.subtituloCancion)
        fun bindItems(data: Canciones) {
            mytexto.text = data.tituloCancion
            artista.text = data.nombreArtista
            Glide.with(miFotoCancion.context).load(data.fotoCancion).into(miFotoCancion)
        }
    }
}