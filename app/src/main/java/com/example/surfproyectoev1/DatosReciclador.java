package com.example.surfproyectoev1;

/*
Autor: Juan Francisco Sánchez González
Fecha: 24/12/2023
Clase: Modelo de datos para el ReciclerView de la actividad Patrocinadores.
*/

public class DatosReciclador {
    private String marca;
    private String descripcion;
    private String enlace;

    public DatosReciclador(String texto1, String texto2, String texto3) {
        marca = texto1;
        descripcion = texto2;
        enlace = texto3;
    }

    public String getMarca() {
        return marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEnlace() {
        return enlace;
    }
}

