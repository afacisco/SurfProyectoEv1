package com.example.surfproyectoev1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

/*
Autor: Juan Francisco Sánchez González
Fecha: 24/12/2023
Clase: Actividad que contiene una Toolbar con la opción de volver atrás y diferentes objetos para crear
un formulario de contacto (TextInputLayout, FAB, CheckBox, ...).
*/

public class ContactoActivity extends AppCompatActivity {

    private Button btnEnvio;
    private EditText etNombre;
    private EditText etCorreo;
    private EditText etAsunto;
    private EditText etMensaje;
    private TextInputLayout campoCorreo;
    private TextInputLayout campoNombre;
    private TextInputLayout campoAsunto;
    private TextInputLayout campoMensaje;
    private CheckBox campoTerminos;
    private Toolbar toolbar;
    final String COORDENADAS = "geo:41.382,2.170?z=16&q=41.382,2.170";
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        // Componente Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Componente Floating Action Button (FAB)
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            Uri intentUri = Uri.parse(COORDENADAS + "(" + getResources().getString(R.string.app_name) + ")");
            Intent intentMapa = new Intent(Intent.ACTION_VIEW, intentUri);
            @Override
            public void onClick(View v) {
                startActivity(intentMapa);
            }
        });

        // Componentes formulario
        campoNombre = (TextInputLayout) findViewById(R.id.textInputLayoutNombre);
        campoCorreo = (TextInputLayout) findViewById(R.id.textInputLayoutCorreo);
        campoAsunto = (TextInputLayout) findViewById(R.id.textInputLayoutAsunto);
        campoMensaje = (TextInputLayout) findViewById(R.id.textInputLayoutMensaje);
        campoTerminos = (CheckBox) findViewById(R.id.checkBoxTerminos);

        // Controlar cambio de foco en el formulario
        etNombre = (EditText) findViewById(R.id.editTextNombre);
        etNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    boolean error = esNombreValido();
                }
            }
        });
        etCorreo = (EditText) findViewById(R.id.editTextCorreo);
        etCorreo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    boolean error = esCorreoValido();
                }
            }
        });
        etAsunto = (EditText) findViewById(R.id.editTextAsunto);
        etAsunto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    boolean error = esAsuntoValido();
                }
            }
        });
        etMensaje = (EditText) findViewById(R.id.editTextMensaje);
        etMensaje.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    boolean error = esMensajeValido();
                }
            }
        });

        // Componente Button
        btnEnvio = (Button) findViewById(R.id.buttonEnvio);
        btnEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos(v);
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

    // Validación campos del formulario
    private void validarDatos(View v) {
        String cadError = getResources().getString(R.string.act2_cadena_error);
        boolean error = esNombreValido();
        if (error) {
            cadError += getResources().getString(R.string.act2_nombre_texto) + "\n";
        }
        error = esCorreoValido();
        if (error) {
            cadError += getResources().getString(R.string.act2_correo_texto) + "\n";
        }
        error = esAsuntoValido();
        if (error) {
            cadError += getResources().getString(R.string.act2_asunto_texto) + "\n";
        }
        error = esMensajeValido();
        if (error) {
            cadError += getResources().getString(R.string.act2_mensaje_texto) + "\n";
        }
        error = esTerminoValido();
        if (error) {
            cadError += getResources().getString(R.string.act2_terminos_dialogo) + "\n";
        }
        if (cadError != getResources().getString(R.string.act2_cadena_error)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ContactoActivity.this);
            builder.setMessage(cadError)
                    .setTitle(getResources().getString(R.string.act2_dialogo_tit))
                    .setIcon(R.mipmap.ic_inicio_round)
                    .setPositiveButton(getResources().getString(R.string.btn_positivo_texto), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            AlertDialog dialogo = builder.create();
            dialogo.show();
        } else {
            Snackbar.make(v, getResources().getString(R.string.act2_envform_texto), Snackbar.LENGTH_LONG).show();
        }
    }

    // Validar campo Nombre
    private boolean esNombreValido() {
        if (campoNombre.getEditText().getText().toString().isEmpty()) {
            campoNombre.setError(getResources().getString(R.string.error_nombre));
            return true;
        } else {
            campoNombre.setError(null);
        }
        return false;
    }

    // Validar campo Correo
    private boolean esCorreoValido() {
        // Patrón para validar el correo electrónico
        if (!Patterns.EMAIL_ADDRESS.matcher(campoCorreo.getEditText().getText().toString()).matches()) {
            campoCorreo.setError(getResources().getString(R.string.error_correo));
            return true;
        } else {
            campoCorreo.setError(null);
        }
        return false;
    }

    // Validar campo Asunto
    private boolean esAsuntoValido() {
        if (campoAsunto.getEditText().getText().toString().isEmpty()) {
            campoAsunto.setError(getResources().getString(R.string.error_asunto));
            return true;
        }else {
            campoAsunto.setError(null);
        }
        return false;
    }

    // Validar campo Mensaje
    private boolean esMensajeValido() {
        if (campoMensaje.getEditText().getText().toString().isEmpty()) {
            campoMensaje.setError(getResources().getString(R.string.error_mensaje));
            return true;
        } else {
            campoMensaje.setError(null);
        }
        return false;
    }

    // Validar campo Términos
    private boolean esTerminoValido() {
        if (!campoTerminos.isChecked()) {
            return true;
        }
        return false;
    }

}