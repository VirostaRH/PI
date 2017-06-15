package com.proyecto.model;

import com.proyecto.model.Centro;

public class OTitulacion
{

	private int id_OTitulacion;
	private String nombre;
	private String descripcion;
	private Centro centro;
	private int fecha_fin;

	public OTitulacion()
	{
		this.id_OTitulacion = -1;
		this.nombre = "";
		this.descripcion = "";
		this.fecha_fin = -1;
	}
	
	public OTitulacion(int i, String nombre, String descripcion, int fecha_fin)
	{
		this.id_OTitulacion = i;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha_fin = fecha_fin;
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
	public void setCentro(Centro centro){
		this.centro = centro;
	}
	public void setFecha_fin(int fecha){
		this.fecha_fin = fecha;
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
	public Centro getCentro(){
		return centro;
	}
	public int getFecha_fin(){
		return fecha_fin;
	}
}