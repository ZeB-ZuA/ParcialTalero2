package com.udistrital.parcialtalero2.ui.list

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.udistrital.parcialtalero2.R
import com.udistrital.parcialtalero2.model.Mascota

class MascotaAdapter(private val mascotas: List<Mascota>, private val context: Context) :
    RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>() {
    class MascotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.mascota_image)
        val name: TextView = itemView.findViewById(R.id.mascota_name)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MascotaViewHolder(view).apply {
            itemView.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val mascota = mascotas[pos]
                    Log.d(
                        "MascotaAdapter",
                        "Mascota seleccionada: ${mascota.name}, URI: ${mascota.image}"
                    )
                    val builder = AlertDialog.Builder(context)
                    val inflater = LayoutInflater.from(context)
                    val view = inflater.inflate(R.layout.dialog, null)

                    val imageView = view.findViewById<ImageView>(R.id.dialog_image)
                    Glide.with(context)
                        .load(mascota.image)
                        .placeholder(R.drawable.download)
                        .error(R.drawable.error)
                        .into(imageView)


                    val textView = view.findViewById<TextView>(R.id.dialog_text)
                    textView.text = "NOMBRE: ${mascota.name}\n" +
                            "TIPO: ${mascota.type}\n" +
                            "EDAD: ${mascota.age}\n" +
                            "RAZA: ${mascota.breed}"
                    builder.setView(view)
                    builder.setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    builder.show()
                }
            }
        }
    }

    override fun onBindViewHolder(holder: MascotaViewHolder, position: Int) {
        val mascota = mascotas[position]
        holder.name.text = mascota.name
        Glide.with(context)
            .load(mascota.image)
            .placeholder(R.drawable.download)
            .error(R.drawable.error)
            .into(holder.image)

    }

    override fun getItemCount() = mascotas.size
}
