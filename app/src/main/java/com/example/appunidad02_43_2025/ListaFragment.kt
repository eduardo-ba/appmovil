package com.example.appunidad02_43_2025

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.example.appunidad02_43_2025.database.AlumnoDB
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListaFragment : Fragment() {
    private lateinit var btnAgregarAlumno: FloatingActionButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_lista, container, false)
        iniciarComponentes(view)
        eventosClic()
        return view
    }


    fun iniciarComponentes(view: View) {
        btnAgregarAlumno = view.findViewById(R.id.btnAgregarAlumno)
    }


    fun eventosClic() {
        btnAgregarAlumno.setOnClickListener(View.OnClickListener {
            val mainActivity = activity as? MainActivity
            mainActivity?.seleccionarItemDelMenu(R.id.btnAlumnos)
        })
    }
}