package com.proyecto.model;

public class Apt{

	private int id_apt;
	private String nombre_apt;
	private int nivel;
	private String descripcion;

	public Apt()
	{
		int id_apt=-1;
		String nombre_apt=null;
		int nivel=-1;
		String descripcion=null;
	}

	public Apt (int id_apt, String nombre_apt, String descripcion)
	{
		this.id_apt = id_apt;
		this.nombre_apt = nombre_apt;
		this.descripcion = descripcion;
	}

	public Apt (int id_apt, String nombre_apt, int nivel, String descripcion)
	{
		this.id_apt = id_apt;
		this.nombre_apt = nombre_apt;
		this.nivel = nivel;
		this.descripcion = descripcion;
	}

	public void setId_apt(int id_apt)
	{
		this.id_apt = id_apt;
	}
	public void setNombre_apt(String nombre_apt)
	{
		this.nombre_apt = nombre_apt;
	}
	public void setNivel(int nivel) 
	{
		this.nivel = nivel;
	}
	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}

	public int getId_apt()
	{
		return this.id_apt;
	}
	public String getNombre_apt()
	{
		return this.nombre_apt;
	}
	public int getNivel() 
	{
		return this.nivel;
	}
	public String getDescripcion()
	{
		return this.descripcion;
	}
}