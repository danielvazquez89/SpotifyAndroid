package com.example.actividadunidad3b

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividadunidad3b.databinding.FragmentBuscarBinding
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class BuscarFragment : Fragment() {
    private var _binding: FragmentBuscarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBuscarBinding.inflate(inflater, container, false)
        val view = binding.root
        setActivityTitle("PlayLists Populares")
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val json = readJsonFromFile("spotify.json")

        val datos = Gson().fromJson(json, PlayListResponse::class.java)


        val mAdapter = MainAdapter(datos.data) {
            val directions = BuscarFragmentDirections.actionBuscarFragmentToCancionesFragment(it)
            findNavController().navigate(directions)
        }
        val mainRecyclerView: RecyclerView = binding.mainRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(context, 2)
        mainRecyclerView.adapter = mAdapter
    }
}


    fun Fragment.setActivityTitle(@StringRes id: Int) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = getString(id)
    }

    fun Fragment.setActivityTitle(title: String) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }




