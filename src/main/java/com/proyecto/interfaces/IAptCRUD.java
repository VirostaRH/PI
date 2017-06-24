package com.proyecto.interfaces;

import java.util.ArrayList;

import com.proyecto.model.Apt;


public interface IAptCRUD
{
    public boolean insertApt(String n, String descripcion);
    public boolean addAptUser(Apt a, String user);
    public Boolean removeAptUser(int a, String user);
	public ArrayList <Apt> buscarAptUser(String user);
    public Apt buscarApt(String nombre_apt, String descripcionApt);
}
