package com.proyecto.model;

import com.proyecto.model.Centro;
import com.proyecto.dao.FinderDAO;

public class OTitulacion
{

	private int id_OTitulacion;
	private String nombre;
	private String descripcion;
	private int id_centro;

	public OTitulacion(int i, String nombre, String descripcion, String n_centro)
	{
		this.id_OTitulacion = i;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.id_centro = FinderDAO.findCentro(n_centro).getId_centro();
	}

	public void setId_OTitulacion(int id)
	{
		this.id_OTitulacion = id;
	}
	public void setNombre(String n){

		this.nombre = n;
	}
	public void setDescripcion(String d){
		this.descripcion = d;
	}
	public void setId_centro(int n_centro){
		this.id_centro = n_centro;
	}
	public int getId_OTitulacion(){
		return id_OTitulacion;
	}
	public String getNombre(){
		return nombre;
	}
	public String getDescripcion(){
		return descripcion;
	}
	public int getId_centro(){
		return id_centro;
	}
}