package com.proyecto.model;

public class Busqueda{
 
  private String aptitud;
  private String ciclo;
  private boolean dispo;
  private String edad;

  public Busqueda (String ciclo, boolean dispo, String aptitud, String edad)
  {
  	this.aptitud = aptitud;
  	this.ciclo = ciclo;
  	this.dispo = dispo;
  	this.edad = edad;
  }

  public String getAptitud()
  {
    return this.aptitud;
  }
  
  public String getCiclo()
  {
    return this.ciclo;
  }

  public boolean getDispo()
  {
    return this.dispo;
  }

  public String getEdad()
  {
    return this.edad;
  }
}