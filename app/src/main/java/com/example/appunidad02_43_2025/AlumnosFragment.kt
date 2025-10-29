package com.example.appunidad02_43_2025

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.appunidad02_43_2025.database.Alumno
import com.example.appunidad02_43_2025.database.AlumnoDB
import androidx.core.net.toUri

class AlumnosFragment : Fragment() {
    private lateinit var btnGuardar : Button
    private lateinit var btnBuscar : Button
    private lateinit var btnBorrar : Button

    private lateinit var btnLimpiar : Button


    private lateinit var txtMatricula : EditText
    private lateinit var txtNombre : EditText
    private lateinit var txtDomicilio : EditText
    private lateinit var txtEspecialidad : EditText

    private lateinit var imgFoto: ImageView

    private lateinit var db: AlumnoDB

    private lateinit var txtFoto: TextView


    companion object{
        private const val PICK_IMAGE_REQUEST = 1
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate (R.layout.fragment_alumnos, container, false)
        iniciarComponentes(view)
        db = AlumnoDB(requireContext())
        eventosClic()
        return view
    }

    fun iniciarComponentes(view: View){
        btnGuardar = view.findViewById(R.id.btnGuardar)
        btnBorrar = view.findViewById(R.id.btnBorrar)
        btnBuscar = view.findViewById(R.id.btnBuscar)
        btnLimpiar = view.findViewById(R.id.btnLimpiar)

        txtNombre = view.findViewById(R.id.txtNombre)
        txtMatricula = view.findViewById(R.id.txtMatricula)
        txtDomicilio = view.findViewById(R.id.txtDomicilio)
        txtEspecialidad = view.findViewById(R.id.txtEspecialidad)
        imgFoto = view.findViewById(R.id.imgAlumno)
        txtFoto = view.findViewById(R.id.txtFoto)

    }

    fun eventosClic(){
        // boton guardar
        btnGuardar.setOnClickListener(View.OnClickListener{
            // 1. Obtenemos los datos de los campos
            val matricula = txtMatricula.text.toString()
            val nombre = txtNombre.text.toString()
            val domicilio = txtDomicilio.text.toString()
            val especialidad = txtEspecialidad.text.toString()
            val foto = imgFoto.tag?.toString() ?: ""
                txtFoto.text=foto

            if (matricula.isEmpty() || nombre.isEmpty() || domicilio.isEmpty() || especialidad.isEmpty()) {
                Toast.makeText(context, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
            } else {

                val db = AlumnoDB(requireContext())
                db.openDataBase()

                val alumnoDatos = Alumno().apply {
                    this.matricula = matricula
                    this.nombre = nombre
                    this.domicilio = domicilio
                    this.especialidad = especialidad
                    this.foto = foto
                }
                val alumnoExistente = db.getAlumno(matricula)

                if (alumnoExistente.id != 0) {

                    val filasAfectadas = db.actualizarAlumno(alumnoDatos, alumnoExistente.id)

                    if (filasAfectadas > 0) {
                        Toast.makeText(requireContext(), "Alumno actualizado con éxito", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Error al actualizar alumno", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    val id = db.insertAlumno(alumnoDatos)

                    if (id > 0) {
                        Toast.makeText(requireContext(), "Se agregó con éxito ID= $id", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Error al agregar alumno", Toast.LENGTH_SHORT).show()
                    }
                }
                db.close()
                }
            })

        btnBuscar.setOnClickListener(View.OnClickListener{
            if (txtMatricula.text.isEmpty()){
                Toast.makeText(requireContext(), "Faltaron datos", Toast.LENGTH_SHORT).show()
            }
            else{
                db.openDataBase()
                val alumno = db.getAlumno(txtMatricula.text.toString())
                db.close()
                if (alumno.id != 0){ // verificar si es diferente de 0
                    txtNombre.setText(alumno.nombre)
                    txtDomicilio.setText(alumno.domicilio)
                    txtEspecialidad.setText(alumno.especialidad)



                    Glide.with(requireContext())
                        .load(alumno.foto.toUri()) // checar si esta bien
                        .apply(RequestOptions().override(100, 100))
                        .into(imgFoto)

                    imgFoto.tag = alumno.foto
                    txtFoto.text = alumno.foto

                } else{
                    Toast.makeText(requireContext(), "No se encontro alumno con esa matricula", Toast.LENGTH_SHORT).show()
                }
            }
        }) // termina btn buscar


        btnBorrar.setOnClickListener(View.OnClickListener{
            if (txtMatricula.text.isEmpty()) Toast.makeText(requireContext(), "Falta la matricula", Toast.LENGTH_SHORT).show()
            else {
                db = AlumnoDB(requireContext())
                db.openDataBase()
                val alumno: Alumno = db.getAlumno(txtMatricula.text.toString())
                if (alumno.id != 0) {
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("Bien")
                    builder.setMessage("¿Quieres borrar el alumno?")
                    builder.setPositiveButton("Si") { dialog, which ->
                        val filasA = db.borrarAlumno(alumno.id)
                        if (filasA > 0) {
                            Toast.makeText(requireContext(), "Alumno borrado con exito", Toast.LENGTH_SHORT).show()
                            txtMatricula.setText("")
                            txtNombre.setText("")
                            txtDomicilio.setText("")
                            txtEspecialidad.setText("")
                            imgFoto.setImageResource(R.drawable.alumno)
                            imgFoto.tag = null
                    } else {
                        Toast.makeText(requireContext(), "Error al borrar alumno", Toast.LENGTH_SHORT).show()
                    }
                    db.close()
                }
                    builder.setNegativeButton("No")
                    { dialog, which -> db.close() }
                    val dialog = builder.create()
                    dialog.show()
                } else {
                    Toast.makeText(requireContext(), "No se encontro", Toast.LENGTH_SHORT).show()
                    db.close()
                }
            }
        })

        btnLimpiar.setOnClickListener(View.OnClickListener{
            txtMatricula.setText("")
            txtNombre.setText("")
            txtDomicilio.setText("")
            txtEspecialidad.setText("")
            //limpiar foto
            txtFoto.setText("")
            imgFoto.setImageResource(R.drawable.alumno)
            imgFoto.tag = null
        })

        imgFoto.setOnClickListener(View.OnClickListener{
            val intent = Intent (Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        })



    }// termina eventos click

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null){
                val uri: Uri? = data.data
                imgFoto.setImageURI(uri)
                imgFoto.tag = uri.toString()
                txtFoto.text = imgFoto.tag?.toString()
            }
    }

}