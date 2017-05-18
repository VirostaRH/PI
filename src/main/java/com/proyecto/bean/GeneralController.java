package com.proyecto.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.sql.ResultSet;

import javax.annotation.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.*;
import javax.servlet.http.HttpSession;

import com.proyecto.dao.*;
import com.proyecto.dao.UpdatePasswdDAO;
import com.proyecto.model.*;
import com.proyecto.utilidades.*;


@ManagedBean
@ApplicationScoped
public class GeneralController implements Serializable {

  private static final long serialVersionUID = 1094801825228386363L;
  
  private String pwd;
  private String msg;
  private String user;
  private String aptitud;
  private String ciclo;
  private String fecha_fin;
  private boolean dispo;
  private String edad;
  private Alumno alumno;
  private boolean existeCV;

  private String oldPasswd;
  private String newPasswd;

  private String nombreApt;
  private String descripcionApt;
  private int id_apt;
  private int nivel_apt;


  private ArrayList <Alumno> alumnos;
  private ArrayList <Ciclo> ciclos;
  private ArrayList <OTitulacion> otra_titulacion = new ArrayList <OTitulacion>();
  private ArrayList <Apt> aptitudes = new ArrayList();

  public void setMsg(String msg)
  {
    this.msg = msg;
  }

  public String getMsg(){
    return msg;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public void setAptitud(String aptitud)
  {
    this.aptitud = aptitud;
  }

  public String getAptitud()
  {
    return aptitud;
  }

  public void setCiclo(String c)
  {
    this.ciclo = c;
  } 
  
  public String getCiclo()
  {
    return ciclo;
  }

  public String getFecha_fin()
  {
    return fecha_fin;
  }

  public void setFecha_fin(String fecha_fin)
  {
    this.fecha_fin = fecha_fin;
  }

  public void setCiclos(ArrayList <Ciclo> c)
  {
    this.ciclos = c;
  } 
  
  public ArrayList <Ciclo> getCiclos()
  {
    return ciclos;
  }

  public void setOtra_titulacion(ArrayList <OTitulacion> o)
  {
    this.otra_titulacion = o;
  } 

  public ArrayList <OTitulacion> getOtra_titulacion()
  {
    return otra_titulacion;
  }
  
  public ArrayList <Apt> getAptitudes()
  {
    return aptitudes;
  }
 
  public void setAptitudes(ArrayList <Apt> a)
  {
    this.aptitudes = a;
  } 
  
  public void setDispo(boolean disp)
  {
    dispo = disp;
  }

  public boolean getDispo()
  {
    return this.dispo;
  }

  public void setEdad(String e)
  {
    this.edad = e;
  }

  public String getEdad()
  {
    return edad;
  }

  public ArrayList <Alumno> getAlumnos()
  {
    return alumnos;
  }

  public void setAlumnos(ArrayList <Alumno> p)
  {
    this.alumnos=p;
  }


  public void setAlumno(Alumno a)
  {
    this.alumno = a;
  }

  public Alumno getAlumno()
  {
    return alumno;
  }

  public boolean isExisteCV()
  {
    return existeCV;
  }

  public void setExisteCV(boolean existe)
  {
    this.existeCV = existe;
  }

  public String getOldPasswd() {
    return oldPasswd;
  }

  public void setOldPasswd(String pwd) {
    this.oldPasswd = pwd;
  }

  public String getNewPasswd() {
    return newPasswd;
  }

  public void setNewPasswd(String pwd) {
    this.newPasswd = pwd;
  }

  public String getNombreApt()
  {
    return nombreApt;
  }

  public void setNombreApt(String n)
  {
    this.nombreApt = n;
  }
  
  public String getDescripcionApt()
  {
    return descripcionApt;
  }
  
  public void setDescripcionApt(String d)
  {
    this.descripcionApt = d;
  }
  
  public int getId_apt()
  {
    return id_apt;
  }

  public void setId_apt(int i)
  {
    this.id_apt = i;
  }
  
  public int getNivel_apt()
  {
    return nivel_apt;
  }

  public void setNivel_apt(int n)
  {
    this.nivel_apt = n;
  }

  //go to admin
  public String crearBusqueda()
  {
    Busqueda b = new Busqueda (ciclo,dispo,aptitud,edad);
    //anteriormente, declarábamos el arraylist aquí también, craso error, al hacer eso, reducíamos el arraylist al límite de este método (que estoy amamonado)
    setAlumnos(FinderDAO.buscar(b));
    return "result";
  }


  //guarda ciclo
  public void guardarCiclo()
  {
    if(!fecha_fin.equals(""))
    {
      Ciclo cTemp = FinderDAO.buscarCiclo(ciclo);
      cTemp.setFecha_fin(getFecha_fin());
      ciclos.add(cTemp);
      InsertDAO.addCicloUser(cTemp, user);
    }
    else
    {
      System.out.println("Error.");
    }
  }

  //guarda aptitud
  public String guardarApt()
  {
    if(!descripcionApt.equals(""))
    {
      Apt aptTemp = FinderDAO.buscarApt(getNombreApt(), getDescripcionApt());
      aptTemp.setNivel(getNivel_apt());
      if (aptTemp.getId_apt() == -1)
      {
        InsertDAO.insertApt(getNombreApt(), getDescripcionApt());
        aptTemp = FinderDAO.buscarApt(getNombreApt(), getDescripcionApt());
      }
      InsertDAO.addAptUser(aptTemp, getUser());
      aptTemp.setNivel(getNivel_apt());
      aptitudes.add(aptTemp);
    }
    else
    {
      System.out.println("Error.");
    }
    return "editaCV";
  }

  public void actualizarCiclo()
  {}
 
  //go to verCV
  public String consultarCV()
  {
    alumno=FinderDAO.buscarUno(user);
    alumnos = new ArrayList<Alumno>();
    alumnos.add(alumno);
    ciclos=FinderDAO.buscarCiclosUser(user);
    aptitudes =FinderDAO.buscarAptUser(user);
    System.out.println(aptitudes.size());
    return "verCV";
  }
  
  //go to editarCV
  public String editarCV()
  {    
   
    alumno=FinderDAO.buscarUno(user);
    alumnos = new ArrayList<Alumno>();
    alumnos.add(alumno);
    ciclos=FinderDAO.buscarCiclosUser(user);
    aptitudes =FinderDAO.buscarAptUser(user);
    System.out.println(aptitudes.size());
    return "editaCV";
  }

  //go to Cambiar contraseña 
  public String editarPerfil()
  {
    return "edicionPerfil";
  }

  
  //elimina ciclo
  public void borrarCiclo(String s, String a)
  {
    System.out.println("Intentar borrar con "+s+" - "+ getUser() + " - "+ a);
    RemoveDAO.removeCicloUser(FinderDAO.buscarCiclo(s), getUser(), a);
    ciclos.remove(FinderDAO.buscarCiclo(getCiclo()));
  }  
  //go to vistaAlumnado
  public String retornoPrincipal()
  {
    return "vistaAlumnado";
  }

  public String crearNuevaAptitud()
  {
    //Apt a = FinderDAO.buscarApt(getNombreApt());
    //a.setNivel(getNivel_apt());
    //InsertDAO.insertaAptitud(a);
    return "creaAptitud";
  }

  public String crearNuevaTitulacion()
  {
    //Apt a = FinderDAO.buscarApt(getNombreApt());
    //a.setNivel(getNivel_apt());
    //InsertDAO.insertaAptitud(a);
    return "creaOTitulacion";
  }
  //validate login
  public String validateUsernamePassword() 
  {
    boolean valid = LoginDAO.validate(user, pwd);
    
    if (valid) {
      HttpSession session = SessionUtils.getSession();
      session.setAttribute("username", user);
      if (LoginDAO.alumnoTipo(user).equals("alumno"))
      {
        //insertar aquí la prueba de alumno para ver si tiene cv
        return "admin";
      }
      return "vistaAlumnado";
    } else {
      FacesContext.getCurrentInstance().addMessage(
          null,
          new FacesMessage(FacesMessage.SEVERITY_WARN,
              "Usuario incorrecto",
              "Prueba a insertar los datos de nuevo"));
      return "login";
    }
  }

  //logout event, invalidate session
  public String logout() {
    HttpSession session = SessionUtils.getSession();
    session.invalidate();
    return "login";
  }

  //cambia la password, go to updateOk / go to updateKo

  public String actualizar()
  {
    if (oldPasswd.equals(getPwd()))
    {
      if (UpdatePasswdDAO.update(getUser(), getNewPasswd()))
      {
        setPwd(getNewPasswd());
        return "updateOk";
      }
      else
      {
        return "updateKo";
      }
    }
    else
    {
      FacesContext.getCurrentInstance().addMessage(
      "Error",
      new FacesMessage(FacesMessage.SEVERITY_WARN,
      "Error coincidencia",
      "Prueba a insertar los datos de nuevo"));
      return "edicionPerfil";
    }
  }
}