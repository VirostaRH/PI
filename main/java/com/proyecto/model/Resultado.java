package com.proyecto.model;

import java.util.Date;
public class Resultado{
 
  // datos de alumno.
  private String nombre;
  private String apellido1;
  private String apellido2;
  private String fnac;
  private String direccion;
  private String localidad;
  private int cp;
  private String provincia;
  private int tlf;
  private String email1;
  private String email2;
  private boolean disponibilidad;
  private String observaciones;

	  public Resultado(String nombre, String apellido1, String apellido2, String fnac, String direccion, String localidad, int cp, String provincia, int tlf, String email1, String email2, boolean disponibilidad, String observaciones)
	  {
	    this.nombre = nombre;
	    this.apellido1 = apellido1;
	    this.apellido2 = apellido2;
	    this.fnac = fnac; 
	    this.direccion = direccion;
	    this.localidad = localidad;
	    this.cp = cp; 
	    this.provincia = provincia;
	    this.tlf = tlf;
	    this.email1 = email1;
	    this.email2 = email2;
	    this.disponibilidad = disponibilidad;
	    this.observaciones = observaciones;
	  }

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido1() {
		return apellido1;
	}
	
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	public String getApellido2() {
		return apellido2;
	}
	
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	public String getFnac() {
		return fnac;
	}
	
	public void setFnac(String fnac) {
		this.fnac = fnac;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getLocalidad() {
		return localidad;
	}
	
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	public int getCp() {
		return cp;
	}
	
	public void setCp(int cp) {
		this.cp = cp;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public int getTlf() {
		return tlf;
	}
	
	public void setTlf(int tlf) {
		this.tlf = tlf;
	}
	
	public String getEmail1() {
		return email1;
	}
	
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	
	public String getEmail2() {
		return email2;
	}
	
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	
	public boolean isDisponibilidad() {
		return disponibilidad;
	}
	
	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
	public String getObservaciones() {
		return observaciones;
	}
	
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	  
}