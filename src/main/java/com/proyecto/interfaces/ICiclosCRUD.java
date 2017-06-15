package com.proyecto.interfaces;
import java.util.ArrayList;

import com.proyecto.model.Ciclo;


public interface ICiclosCRUD
{
    public ArrayList <Ciclo> buscarCiclosUser(String user);
    public Ciclo buscarCiclo(String siglas);
    public boolean addCicloUser(Ciclo c, String user);
	public Ciclo removeCicloUser(Ciclo c, String user, String anio);
}