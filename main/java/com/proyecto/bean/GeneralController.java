package com.proyecto.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.proyecto.dao.LoginDAO;
import com.proyecto.dao.FinderDAO;
import com.proyecto.model.*;
import com.proyecto.utilidades.*;

import java.sql.ResultSet;

@ManagedBean
@SessionScoped
public class GeneralController implements Serializable {

  private static final long serialVersionUID = 1094801825228386363L;
  private String pwd;
  private String msg;
  private String user;
  private String aptitud;
  private String ciclo;
  private boolean dispo;
  private String edad;
  private ArrayList <Resultado> parametroAVista = new ArrayList <Resultado> ();

  public void setMsg(String msg){
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

  public ArrayList <Resultado> getParametroAVista()
  {
    return parametroAVista;
  }

  public void setParametroAVista(ArrayList <Resultado> p)
  {
    this.parametroAVista=p;
  }

  //views

  public String crearBusqueda()
  {
    Busqueda b = new Busqueda (ciclo,dispo,aptitud,edad);
    ArrayList <ResultSet> retorno = FinderDAO.buscar(b);
    parametroAVista = Parseador.transformaResulset(retorno);
    return "result";
  }
  
  //validate login
  public String validateUsernamePassword() 
  {
    boolean valid = LoginDAO.validate(user, pwd);
    
    if (valid) {
      HttpSession session = SessionUtils.getSession();
      session.setAttribute("username", user);
      if (LoginDAO.alumnoTipo(user))
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