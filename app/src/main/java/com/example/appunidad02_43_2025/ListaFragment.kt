package com.example.appunidad02_43_2025

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appunidad02_43_2025.database.Alumno
import com.example.appunidad02_43_2025.database.AlumnoDB
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListaFragment : Fragment() {

    private lateinit var adapter : DbAdapter
    private lateinit var rcvLista: RecyclerView
    private lateinit var btnNuevoF: FloatingActionButton
    private lateinit var listaAlumno: ArrayList<Alumno>

    private lateinit var srv : SearchView
    private lateinit var db : AlumnoDB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_lista, container, false)
        iniciarComponentes(view)
        eventosClic()
        cargarAlumnos()
        return view
    }


    fun iniciarComponentes(view: View) {
        btnNuevoF = view.findViewById(R.id.btnAgregarAlumno)
        //srv = view.findViewById(R.id.srvAlumnos)
        rcvLista = view.findViewById(R.id.recId)
        rcvLista.layoutManager = LinearLayoutManager (requireContext())
        listaAlumno = ArrayList()
        adapter = DbAdapter(listaAlumno,requireContext())
        db = AlumnoDB(requireContext())
        rcvLista.adapter = adapter

    }


    fun eventosClic() {
        btnNuevoF.setOnClickListener(View.OnClickListener {
            val mainActivity = activity as? MainActivity
            mainActivity?.seleccionarItemDelMenu(R.id.btnAlumnos)
        })

    }

    fun cambiarFragment(){
        parentFragmentManager.beginTransaction().replace(R.id.frmContenedor,
            AlumnosFragment()).addToBackStack(null).commit()
    }

    fun cargarAlumnos(){
        listaAlumno.clear()
        db.openDataBase()
        listaAlumno = db.leerTodos()
        Toast.makeText(requireContext(), "cantidad reg" + listaAlumno.size, Toast.LENGTH_SHORT).show()
        adapter.notifyDataSetChanged()
        adapter.actualizarLista(listaAlumno)

    }
    fun cambiarAlumnos(){
        listaAlumno.clear()
        db.openDataBase()
        listaAlumno = db.leerTodos()
        adapter.notifyDataSetChanged()
        adapter.actualizarLista(listaAlumno)
    }
}