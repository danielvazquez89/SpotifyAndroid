package com.example.actividadunidad3b

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividadunidad3b.databinding.FragmentCancionesBinding
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class CancionesFragment : Fragment() {
    private var _binding: FragmentCancionesBinding? = null
    private val binding get() = _binding!!
    val args: CancionesFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setActivityTitle(args.playList.nombrePlayList)
        _binding = FragmentCancionesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private fun readJsonFromFile(fileName: String): String {
        var json = ""
        try {
            val bufferedReader = BufferedReader(
                InputStreamReader(context?.assets?.open(fileName))
            )
            val paramsBuilder = StringBuilder()
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                paramsBuilder.append(line)
                line = bufferedReader.readLine()
            }
            json = paramsBuilder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val json = readJsonFromFile("spotify.json")

        val datos = Gson().fromJson(json, Canciones::class.java)
        var checked = false
        val mAdapter = MainAdapterCanciones(args.playList.canciones) {
          //  val directions = BuscarFragmentDirections.actionBuscarFragmentToCancionesFragment(it)
            //findNavController().navigate(directions)
            if (checked) {

            } else {

            }
        }
        val mainRecyclerView: RecyclerView = binding.mainRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(context, 1)
        mainRecyclerView.adapter = mAdapter
    }
}

