package com.proyecto.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.proyecto.dao.LoginDAO;
import com.proyecto.dao.FinderDAO;
import com.proyecto.model.*;
import com.proyecto.utilidades.*;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;

@ManagedBean
@ApplicationScoped
public class GeneralController implements Serializable {

  private static final long serialVersionUID = 1094801825228386363L;
  private String pwd;
  private String msg;
  private String user;
  private String aptitud;
  private String ciclo;
  private boolean dispo;
  private String edad;
  private ArrayList <Alumno> alumnos;
  private Alumno alumno;
  
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

  public void setCiclo(String ciclo)
  {
    this.ciclo = ciclo;
  } 
  
  public String getCiclo()
  {
    return ciclo;
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

  public Alumno getAlumno()
  {
    return alumno;
  }

  //views 
  public String crearBusqueda()
  {
    Busqueda b = new Busqueda (ciclo,dispo,aptitud,edad);
    //anteriormente, declarábamos el arraylist aquí también, craso error, al hacer eso, reducíamos el arraylist al límite de este método (que estoy amamonado)
    setAlumnos(FinderDAO.buscar(b));
    System.out.print(alumnos.size());
    return "result";
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
        return "vistaAlumnado";
      }
      return "admin";
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
}