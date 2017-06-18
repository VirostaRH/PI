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
import com.proyecto.interfaces.*;

import com.proyecto.dao.UpdatePasswdDAO;
import com.proyecto.model.*;
import com.proyecto.interfaces.*;
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

  private String otraT;
  private String descripcionOtraT;
  private String centro;
  private String fecha_fin_ot;

  private ArrayList <Alumno> alumnos;
  private ArrayList <Ciclo> ciclos;
  private ArrayList <OTitulacion> otra_titulacion = new ArrayList <OTitulacion>();
  private ArrayList <Apt> aptitudes = new ArrayList();

  
  private IAlumnosCRUD alumnosCRUD = new AlumnosCRUD();
  private IAptCRUD aptCRUD = new AptCRUD();
  private ICentroCRUD centroCRUD = new CentroCRUD();
  private ICiclosCRUD ciclosCRUD = new CiclosCRUD();
  private IOTitulacionCRUD oTitulacionCRUD = new OTitulacionCRUD();
  

  /*Listado de getter y setter del bean controller.*/
  /**/
  public void setMsg(String msg)
  {
    this.msg = msg;
  }

  public String getMsg(){
    return msg;
  }

  /**/
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

  /**/
  public void setAptitud(String aptitud)
  {
    this.aptitud = aptitud;
  }

  public String getAptitud()
  {
    return aptitud;
  }

  /**/
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

  /**/
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

  public void setOtraT(String n)
  {
    this.otraT = n;
  }
  public void setDescripcionOtraT(String d)
  {
    this.descripcionOtraT = d;
  }
  public void setCentro(String c)
  {
    this.centro = c;
  }

  public String getOtraT()
  {
    return otraT;
  }
  public String getDescripcionOtraT()
  {
    return descripcionOtraT;
  }
  public String getCentro()
  {
    return centro;
  }

  public String getFecha_fin_ot()
  {
    return fecha_fin_ot;
  }

  public void setFecha_fin_ot(String fecha_fin)
  {
    this.fecha_fin_ot = fecha_fin;
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
    setAlumnos(alumnosCRUD.buscar(b));
    return "result";
  }


  //crea ciclo
  public String guardarCiclo()
  {
    Ciclo cTemp = ciclosCRUD.buscarCiclo(ciclo);
    if(!fecha_fin.equals(null) && !fecha_fin.equals(""))
    {
      cTemp.setFecha_fin(getFecha_fin());
      if(ciclosCRUD.addCicloUser(cTemp, user))
        {
          ciclos.add(cTemp);
        }
    }
    else
    {
      System.out.println("Error.");
    }
    return null;
  }

  //crea aptitud
  public String guardarApt()
  {
    if(!descripcionApt.equals(""))
    {
      Apt aptTemp = aptCRUD.buscarApt(getNombreApt(), getDescripcionApt());
      aptTemp.setNivel(getNivel_apt());
      if (aptTemp.getId_apt() == -1)
      {
        aptCRUD.insertApt(getNombreApt(), getDescripcionApt());
        aptTemp = aptCRUD.buscarApt(getNombreApt(), getDescripcionApt());
      }
      else
      {
        if (aptCRUD.addAptUser(aptTemp, getUser()))
        {
          aptTemp.setNivel(getNivel_apt());
          aptitudes.add(aptTemp);
        }
        else
        {
          System.out.println("Error, aptitud duplicada");
        }
      }
    }
    return null;
  }

  //go to consulta todos los datos de un alumno
  public String consultarCV()
  {
    alumno=alumnosCRUD.buscarUno(getUser());
    alumnos = new ArrayList<Alumno>();
    alumnos.add(alumno);
    ciclos=ciclosCRUD.buscarCiclosUser(getUser());
    aptitudes =aptCRUD.buscarAptUser(getUser());
    otra_titulacion = oTitulacionCRUD.buscarOtrasTitulacionesUser(getUser());
    return "verCV";
  }
  
  //versión profesorado
  public String consultarCVprof(String u)
  {
    System.out.println("Entra en consultarCV");
    alumno=alumnosCRUD.buscarUno(u);
    alumnos = new ArrayList<Alumno>();
    alumnos.add(alumno);
    ciclos=ciclosCRUD.buscarCiclosUser(u);
    aptitudes =aptCRUD.buscarAptUser(u);
    otra_titulacion = oTitulacionCRUD.buscarOtrasTitulacionesUser(u);
    System.out.println("Llega a previa a vistaCVprof");
    return "vistaCVprof";
  }

  //elimina ciclo (TESTEADO OK)
  public String borrarCiclo(String s, String a)
  {
    ciclosCRUD.removeCicloUser(ciclosCRUD.buscarCiclo(s), getUser(), a);
    ciclos = ciclosCRUD.buscarCiclosUser(getUser());
    return null;
  }  

  //testeado
  public String borrarApt(String a)
  {
    Apt aux = aptCRUD.buscarApt(a, "");
    aptCRUD.removeAptUser(aux.getId_apt(), getUser());
    aptitudes = aptCRUD.buscarAptUser(getUser());
    return null;
  }

  public String guardarDatosPersonales()
  {
    System.out.println(alumnos.get(0));
    alumnosCRUD.actualizar(alumnos.get(0));
    return null;
  }

  //go to vista editarCV
  public String editarCV()
  {
    alumno= alumnosCRUD.buscarUno(getUser());
    alumnos = new ArrayList<Alumno>();
    alumnos.add(alumno);
    ciclos=ciclosCRUD.buscarCiclosUser(getUser());
    aptitudes=aptCRUD.buscarAptUser(getUser());
    otra_titulacion = oTitulacionCRUD.buscarOtrasTitulacionesUser(getUser());
    return "editaCV";
  }

  //crea OtraTitulacion
  public String guardaOtraTitulacion()
  {
    Centro obj_centro = centroCRUD.findCentro(getCentro());
    OTitulacion otraT = oTitulacionCRUD.findOT(getOtraT(), getDescripcionOtraT(), obj_centro, getUser(), Integer.parseInt(getFecha_fin_ot()));
    otraT.setFecha_fin(Integer.parseInt(getFecha_fin_ot()));
    otra_titulacion.add(otraT);
    System.out.println("alumno "+alumnos.get(0)+ "\nAptitudes"+aptitudes.size()+"\nOtrasTitulaciones"+otra_titulacion.size());
    return null;
  }

  //go to vistaAlumnado
  public String retornoPrincipal()
  {
    System.out.println("Vuelta a inicio");
    return "vistaAlumnado.hxtml";
  }

  //go to Cambiar contraseña (TESTEADO OK)
  public String editarPerfil()
  {
    return "edicionPerfil";
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