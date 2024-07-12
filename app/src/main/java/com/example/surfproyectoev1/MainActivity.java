package com.example.surfproyectoev1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/*
Autor: Juan Francisco Sánchez González
Fecha: 24/12/2023
Clase: Actividad principal que contiene una Toolbar con diferentes opciones (salir de la aplicación,
cambio a otras actividades, visualizar diálogo, ...), un FAB, animación de texto, recurso de vídeo, ...
*/

public class MainActivity extends AppCompatActivity {

    private TextView mensajeAnimado;
    private Animation animacion;
    private VideoView video;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Componente Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        // Asignamos la animación de traslación
        mensajeAnimado = (TextView) findViewById(R.id.textViewAnimado);
        animacion = AnimationUtils.loadAnimation(this, R.anim.traslacion);
        animacion.setRepeatMode(Animation.RESTART);
        animacion.setRepeatCount(50);
        mensajeAnimado.startAnimation(animacion);

        //Inicializamos la clase VideoView asociandole el fichero de Video
        video = (VideoView) findViewById(R.id.videoView);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.video;
        video.setVideoURI(Uri.parse(path));
        video.start();

        // Componente Floating Action Button (FAB)
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            Intent intentContacto = new Intent(MainActivity.this, ContactoActivity.class);
            @Override
            public void onClick(View v) {
                startActivity(intentContacto);
            }
        });
    }

    // OptionsMenu de la ToolBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options_act1, menu);
        return true;
    }

    // Listener OptionsMenu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // About
        if (item.getItemId() == R.id.opcionAbout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage(getResources().getString(R.string.dialogo_about_mensaje))
                    .setTitle(getResources().getString(R.string.act1_menu_about))
                    .setIcon(R.mipmap.ic_inicio_round)
                    .setPositiveButton(getResources().getString(R.string.btn_positivo_texto), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            AlertDialog dialogo = builder.create();
            dialogo.show();
            return true;
        }
        // Salir
        if (item.getItemId() == R.id.opcionSalir) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage(getResources().getString(R.string.dialogo_salir_mensaje))
                    .setTitle(getResources().getString(R.string.act1_menu_salir))
                    .setIcon(R.mipmap.ic_inicio_round)
                    .setPositiveButton(getResources().getString(R.string.btn_positivo_texto), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
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
        // Cesta de la compra
        if (item.getItemId() == R.id.opcionCesta) {
            Intent intentCesta = new Intent(MainActivity.this, CestaActivity.class);
            startActivity(intentCesta);
            return true;
        }
        // Patrocinadores
        if (item.getItemId() == R.id.opcionPatrocinadores) {
            Intent intentPatrocinadores = new Intent(MainActivity.this, PatrocinadoresActivity.class);
            startActivity(intentPatrocinadores);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}