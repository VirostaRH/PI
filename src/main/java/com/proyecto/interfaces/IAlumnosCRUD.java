package com.proyecto.interfaces;

import java.util.ArrayList;

import com.proyecto.model.*;


public interface IAlumnosCRUD
{
    public ArrayList <Alumno> buscar(Busqueda data);
    public Alumno buscarUno(String user);
    public boolean actualizar(Alumno a);
    public boolean comprobarExiste(String alumno);
    public Alumno altaDD (String id_alumno, String nombre, String apellido1, String apellido2, String fnac, String direccion, String localidad, int cp, String provincia, int tlf, String email1, String email2, boolean disponibilidad, String observaciones);
}