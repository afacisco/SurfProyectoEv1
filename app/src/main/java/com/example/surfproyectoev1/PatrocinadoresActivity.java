package com.example.surfproyectoev1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/*
Autor: Juan Francisco Sánchez González
Fecha: 24/12/2023
Clase: Actividad que contiene una Toolbar con la opción de volver atrás y un RecyclerView para mostrar
un listado que cargará la información un adaptador personalizado.
*/

public class PatrocinadoresActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView reciclador;
    private RecyclerView.Adapter adaptadorReciclador;
    private RecyclerView.LayoutManager layManagerReciclador;

    // Datos a mostrar en el listado
    DatosReciclador[] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrocinadores);

        // Componente Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Carga de datos Reciclador
        datos = new DatosReciclador[] {
                new DatosReciclador(getResources().getStringArray(R.array.array_patrocinadores_nombre)[0], getResources().getStringArray(R.array.array_patrocinadores_descripcion)[0], getResources().getStringArray(R.array.array_patrocinadores_enlaces)[0]),
                new DatosReciclador(getResources().getStringArray(R.array.array_patrocinadores_nombre)[1], getResources().getStringArray(R.array.array_patrocinadores_descripcion)[1], getResources().getStringArray(R.array.array_patrocinadores_enlaces)[1]),
                new DatosReciclador(getResources().getStringArray(R.array.array_patrocinadores_nombre)[2], getResources().getStringArray(R.array.array_patrocinadores_descripcion)[2], getResources().getStringArray(R.array.array_patrocinadores_enlaces)[2]),
                new DatosReciclador(getResources().getStringArray(R.array.array_patrocinadores_nombre)[3], getResources().getStringArray(R.array.array_patrocinadores_descripcion)[3], getResources().getStringArray(R.array.array_patrocinadores_enlaces)[3]),
                new DatosReciclador(getResources().getStringArray(R.array.array_patrocinadores_nombre)[4], getResources().getStringArray(R.array.array_patrocinadores_descripcion)[4], getResources().getStringArray(R.array.array_patrocinadores_enlaces)[4]),
                new DatosReciclador(getResources().getStringArray(R.array.array_patrocinadores_nombre)[5], getResources().getStringArray(R.array.array_patrocinadores_descripcion)[5], getResources().getStringArray(R.array.array_patrocinadores_enlaces)[5])};

        // Componente Reciclador
        reciclador = (RecyclerView) findViewById(R.id.reciclador);
        reciclador.setHasFixedSize(true);
        layManagerReciclador = new LinearLayoutManager(this);
        reciclador.setLayoutManager(layManagerReciclador);

        adaptadorReciclador = new AdaptadorReciclador(datos);
        reciclador.setAdapter(adaptadorReciclador);
    }

    // OptionsMenu de la ToolBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options_act2, menu);
        return true;
    }

    // Listener OptionsMenu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.opcionAtras) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}