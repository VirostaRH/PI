package com.proyecto.model;

public class Centro{
	
	private int id_centro;
	private String nombre_centro;

	public Centro()
	{
		this.id_centro = -1;
		this.nombre_centro = null;
	}

	public Centro (int id, String n)
	{
		this.id_centro = id;
		this.nombre_centro = n;
	}

	public void setId_centro(int id_centro)
	{
		this.id_centro = id_centro;
	}
	public void setNombre_centro(String n)
	{
		this.nombre_centro = n;
	}

	public int getId_centro()
	{
		return this.id_centro;
	}

	public String getNombre_centro()
	{
		return this.nombre_centro;
	}
}