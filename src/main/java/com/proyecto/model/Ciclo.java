package com.proyecto.model;

public class Ciclo{
 
  private String id_ciclo;
  private String nombre_ciclo;
  private String siglas;
  private String fecha_fin;


  public Ciclo ()
  {
    this.id_ciclo = "";
    this.nombre_ciclo ="";
    this.siglas = "";
    this.fecha_fin = "";
  }

  public Ciclo (String id_ciclo, String nombre_ciclo, String siglas, String fecha_fin)
  {
  	this.id_ciclo = id_ciclo;
    this.nombre_ciclo = nombre_ciclo;
    this.siglas = siglas;
    this.fecha_fin = fecha_fin;
  }

  public String getId_ciclo()
  {
    return id_ciclo;
  }
  
  public String getNombre_ciclo()
  {
    return nombre_ciclo;
  }

  public String getSiglas()
  {
    return siglas;
  }

  public String getFecha_fin()
  {
    return fecha_fin;
  }

  public void setId_ciclo(String id)
  {
    this.id_ciclo = id;
  }
  
  public void setNombre_ciclo(String n)
  {
    this.nombre_ciclo = n;
  }

  public void setSiglas(String siglas)
  {
    this.siglas = siglas;
  }

  public void setFecha_fin(String f)
  {
    this.fecha_fin = f;
  }
}