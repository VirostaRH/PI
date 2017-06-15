package com.proyecto.interfaces;

import java.util.ArrayList;

import com.proyecto.model.*;


public interface IAlumnosCRUD
{
    public ArrayList <Alumno> buscar(Busqueda data);
    public Alumno buscarUno(String user);
    public boolean actualizar(Alumno a);
}