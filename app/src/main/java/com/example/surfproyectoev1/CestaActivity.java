package com.example.surfproyectoev1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/*
Autor: Juan Francisco Sánchez González
Fecha: 24/12/2023
Clase: Actividad que contiene una Toolbar con la opción de volver atrás y diferentes objetos para crear
un formulario (AutoCompleteTextView, FAB, ListView, ...) que simulará la cesta o lista de la compra.
*/

public class CestaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AutoCompleteTextView campoProducto;
    private FloatingActionButton fab;
    private EditText campoCantidad;
    private EditText campoPrecio;
    private TextView cadenaTotal;
    private ListView listaCompra;
    private ArrayList productosLista;
    private ArrayList cantidadesLista;
    private ArrayList preciosLista;
    private ArrayAdapter<String> adaptadorLista;
    private int totalProductos;
    private double totalPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesta);

        // Componente Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Componente AutoCompleteTextView
        campoProducto = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextProducto);
        String[] opciones = getResources().getStringArray(R.array.array_productos);
        ArrayAdapter<String> adaptadorProducto = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, opciones);
        campoProducto.setAdapter(adaptadorProducto);

        // Componente ListView
        listaCompra = (ListView) findViewById(R.id.listViewListaCompra);
        productosLista = new ArrayList();
        cantidadesLista = new ArrayList();
        preciosLista = new ArrayList();
        adaptadorLista = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productosLista);
        listaCompra.setAdapter(adaptadorLista);

        // Asociar menu contextual al ListView
        registerForContextMenu(listaCompra);

        // Componentes EditText
        campoCantidad = (EditText) findViewById(R.id.editTextCantidad);
        campoPrecio = (EditText) findViewById(R.id.editTextPrecio);

        cadenaTotal = (TextView) findViewById(R.id.textViewTotal);
        totalProductos = 0;
        totalPrecio = 0;

        // Componente Floating Action Button (FAB)
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (campoProducto.getText().toString().isEmpty()) {
                    Toast.makeText(CestaActivity.this,getResources().getString(R.string.act3_error_toast), Toast.LENGTH_LONG).show();
                } else {
                    String cant = (campoCantidad.getText().toString().isEmpty()) ? "1" : campoCantidad.getText().toString();
                    String pre = (campoPrecio.getText().toString().isEmpty()) ? "0" : campoPrecio.getText().toString();
                    double precioProducto = Integer.parseInt(cant) * Double.parseDouble(pre);
                    totalProductos += Integer.parseInt(cant);
                    totalPrecio += precioProducto;
                    String cadenaDatos = campoProducto.getText().toString() + " (" + cant + ") = " + String.format("%.2f", precioProducto) + "€";
                    cadenaTotal.setText(getResources().getString(R.string.act3_totalcesta_texto) + " " + totalProductos + " productos = " + String.format("%.2f", totalPrecio) + "€");
                    productosLista.add(cadenaDatos);
                    cantidadesLista.add(campoCantidad.getText().toString());
                    preciosLista.add(campoPrecio.getText().toString());
                    adaptadorLista.notifyDataSetChanged();
                    campoProducto.setText("");
                    campoCantidad.setText("");
                    campoPrecio.setText("");
                    campoProducto.requestFocus();
                }
            }
        });
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

    // ContextMenu de la lista
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual_act3, menu);
    }

    // Listener ContextMenu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.opcionEliminar) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CestaActivity.this);
            builder.setMessage(getResources().getString(R.string.act3_dialogo_texto))
                    .setTitle(getResources().getString(R.string.act3_dialogo_tit))
                    .setIcon(R.mipmap.ic_inicio_round)
                    .setPositiveButton(getResources().getString(R.string.btn_positivo_texto), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            productosLista.remove(info.position);
                            String cantidad = (String) cantidadesLista.get(info.position);
                            String precio = (String) preciosLista.get(info.position);
                            double precioProducto = Integer.parseInt(cantidad) * Double.parseDouble(precio);
                            totalProductos -= Integer.parseInt(cantidad);
                            totalPrecio -= precioProducto;
                            cadenaTotal.setText(getResources().getString(R.string.act3_totalcesta_texto) + " " + totalProductos + " productos = "+ String.format("%.2f", totalPrecio) + "€");
                            adaptadorLista.notifyDataSetChanged();

                        }
                    })
                    .setNegativeButton(getResources().getString(R.string.btn_negativo_texto), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            AlertDialog dialogo = builder.create();
            dialogo.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}