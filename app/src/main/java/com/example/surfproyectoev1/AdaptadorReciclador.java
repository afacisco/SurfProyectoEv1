package com.example.surfproyectoev1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

/*
Autor: Juan Francisco Sánchez González
Fecha: 24/12/2023
Clase: Adaptador personalizado para cargar los datos en los CardView que utilizará el ReciclerView de la
actividad Patrocinadores.
*/

public class AdaptadorReciclador extends RecyclerView.Adapter<AdaptadorReciclador.ViewHolder> {

    private DatosReciclador[] items;

    public AdaptadorReciclador(DatosReciclador[] items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView marca;
        private final TextView descripcion;
        private final TextView enlace;

        public ViewHolder(View vista) {
            super (vista);

            marca = (TextView) vista.findViewById(R.id.textViewMarca);
            descripcion = (TextView) vista.findViewById(R.id.textViewDescripcion);
            enlace = (TextView) vista.findViewById(R.id.textViewEnlace);
        }

        public TextView getMarca() {
            return marca;
        }

        public TextView getDescripcion() {
            return descripcion;
        }
        public TextView getEnlace() {
            return enlace;
        }

    }


    @Override
    public int getItemCount () {
        return items.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_patrocinadores, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getMarca().setText(items[i].getMarca());
        viewHolder.getDescripcion().setText(items[i].getDescripcion());
        viewHolder.getEnlace().setText(items[i].getEnlace());

        viewHolder.marca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, items[i].getMarca(), Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
