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
@SessionScoped
public class GeneralController implements Serializable {

  private static final long serialVersionUID = 1094801825228386363L;

  //datos login  
  private String pwd;
  private String msg;
  private String user;

  //datos altaCV
  private String id_alumno; 
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

  //datos busqueda
  private String aptitud;
  private String ciclo;
  private String fecha_fin;
  private boolean dispo;
  private String edad;

  //objeto alumno
  private Alumno alumno;
  private boolean existeCV;

  private String oldPasswd;
  private String newPasswd;

  //datos curriculares
  private String nombreApt;
  private String descripcionApt;
  private int id_apt;
  private int nivel_apt;

  private String otraT;
  private String descripcionOtraT;
  private String centro;
  private String fecha_fin_ot;

  //exclusivo para resultado de búsqueda y poder aplicar id.
  private String mailForm;
  private String mailForm2;

  private ArrayList <Alumno> alumnos;
  private ArrayList <Alumno> lista_alumnos_busqueda;
  private ArrayList <Ciclo> ciclos;
  private ArrayList <OTitulacion> otra_titulacion = new ArrayList <OTitulacion>();
  private ArrayList <Apt> aptitudes = new ArrayList();

  private IUsuariosC usuariosC = new UsuariosC();
  private IAlumnosCRUD alumnosCRUD = new AlumnosCRUD();
  private IAptCRUD aptCRUD = new AptCRUD();
  private ICentroCRUD centroCRUD = new CentroCRUD();
  private ICiclosCRUD ciclosCRUD = new CiclosCRUD();
  private IOTitulacionCRUD oTitulacionCRUD = new OTitulacionCRUD();
  private ICursaOTCRUD cursaOTCRUD = new Cursa_OTCRUD();
  

  //CRUD's vistas alumnos

  public String altaCV()
  {
    return "altaCV";
  }

  public String darAltaDeCV()
  {
    System.out.println(getUser() + "-" + nombre+ "-" + apellido1+ "-" + apellido2+ "-" + fnac+ "-" + direccion+ "-" + localidad+ "-" + cp+ "-" + provincia+ "-" + tlf+ "-" + email1+ "-" + email2+ "-" + disponibilidad+ "-" + observaciones);

    alumnosCRUD.altaDD(getUser(), nombre, apellido1, apellido2, fnac, direccion, localidad, cp, provincia, tlf, email1, email2, disponibilidad, observaciones);
    setExisteCV(alumnosCRUD.comprobarExiste(getUser()));
    return "vistaAlumnado";
  }

  public String consultarCV()
  {
    alumno=alumnosCRUD.buscarUno(getUser());
    alumnos = new ArrayList<Alumno>();
    alumnos.add(alumno);
    ciclos=ciclosCRUD.buscarCiclosUser(getUser());
    aptitudes =aptCRUD.buscarAptUser(getUser());
    otra_titulacion = cursaOTCRUD.buscarOtrasTitulacionesUser(getUser());
    return "verCV";
  }

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
    return "verCV";
  }

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
    return "verCV";
  }
  
  public String borrarCiclo(String s, String a)
  {
    ciclosCRUD.removeCicloUser(ciclosCRUD.buscarCiclo(s), getUser(), a);
    ciclos = ciclosCRUD.buscarCiclosUser(getUser());
    return null;
  }  

  public String borrarApt(String a)
  {
    Apt aux = aptCRUD.buscarApt(a, "");
    aptCRUD.removeAptUser(aux.getId_apt(), getUser());
    aptitudes = aptCRUD.buscarAptUser(getUser());
    return null;
  }

  public String guardarDatosPersonales()
  {
    alumnosCRUD.actualizar(alumnos.get(0));
    return null;
  }

  public String editarCV()
  {
    alumno= alumnosCRUD.buscarUno(getUser());
    alumnos = new ArrayList<Alumno>();
    alumnos.add(alumno);
    ciclos=ciclosCRUD.buscarCiclosUser(getUser());
    aptitudes=aptCRUD.buscarAptUser(getUser());
    otra_titulacion = cursaOTCRUD.buscarOtrasTitulacionesUser(getUser());
    return "editaCV";
  }

  public String guardaOtraTitulacion()
  {
    Centro obj_centro = centroCRUD.findCentro(getCentro());

    if(oTitulacionCRUD.insertOT(getOtraT(), getDescripcionOtraT(), obj_centro))
    {
      OTitulacion ot = oTitulacionCRUD.buscarOTbyNameAndCentroObj(getOtraT(), obj_centro, fecha_fin_ot);
      cursaOTCRUD.insertCursaOT(ot, getUser());
      otra_titulacion = cursaOTCRUD.buscarOtrasTitulacionesUser(getUser());
    }
    return null;
  }

  //borra OtraTitulacion
  public String borraOtraTitulacion(String ot, String centro, String fecha_fin_ot)
  {
    Centro obj_centro = centroCRUD.findCentro(centro);
    OTitulacion oTit = oTitulacionCRUD.buscarOTbyNameAndCentroObj(ot, obj_centro, fecha_fin_ot);
    cursaOTCRUD.deleteCursaOT(oTit, getUser());

    otra_titulacion = cursaOTCRUD.buscarOtrasTitulacionesUser(getUser());
    return null;
  }
  
  //go to listado alumnos
  public String crearBusqueda()
  {
    Busqueda b = new Busqueda (ciclo,dispo,aptitud,edad);
    lista_alumnos_busqueda = alumnosCRUD.buscar(b);
    return "result";
  }

  //ver cv versión profesorado (al retornar, mantendrá la lista de búsqueda)
  public String consultarCVprof(String u)
  {
    alumno=alumnosCRUD.buscarUno(u);
    alumnos = new ArrayList<Alumno>();
    alumnos.add(alumno);
    ciclos=ciclosCRUD.buscarCiclosUser(u);
    aptitudes =aptCRUD.buscarAptUser(u);
    otra_titulacion = cursaOTCRUD.buscarOtrasTitulacionesUser(u);
    return "vistaCVprof";
  }

  public String exportarCSV()
  {
    System.out.println("Ataca ExportarCSV con resultado ");
    try{
      boolean resultado = ExportarCSV.creaCSV(alumnos, ciclos, aptitudes, otra_titulacion);
      System.out.println("Supera ExportarCSV con resultado" +resultado);
    }catch (Exception e)
    {
      System.out.println("KO ExportarCSV");
    }
    return null;
  }

//vuelta a vista principal alumnado
  public String irVistaAlumnado()
  {
    setExisteCV(alumnosCRUD.comprobarExiste(getUser()));
    return "vistaAlumnado";
  }

  //vuelta a vista principal profesorado
  public String returnToAdmin()
  {
    return "admin";
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

      setExisteCV(alumnosCRUD.comprobarExiste(getUser()));
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

  //registro de usuario nuevo!

  public String createUser()
  {
    System.out.println("saveUser + " + getUser() + getPwd());
    boolean ok = usuariosC.alta(getUser(), getPwd());
    System.out.println(ok);
    return null;
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

  /*Getter y setter del generalController.*/
  
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

  public ArrayList <Alumno> getLista_alumnos_busqueda()
  {
    return lista_alumnos_busqueda;
  }

  public void setLista_alumnos_busqueda(ArrayList <Alumno> p)
  {
    this.lista_alumnos_busqueda=p;
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

  //dd personales alumno alta
  public void setId_alumno(String i){
    this.id_alumno = i;
  } 
  
  public void setNombre(String i){
    this.nombre = i;
  } 
  
  public void setApellido1(String i){
    this.apellido1 = i;
  } 
  
  public void setApellido2(String i){
    this.apellido2 = i;
  } 
  
  public void setFnac(String f){
    this.fnac = f;
  } 
  
  public void setDireccion(String i){
    this.direccion = i;
  } 
  
  public void setLocalidad(String i){
    this.localidad = i;
  } 
  
  public void setCp(int c){
    this.cp = c;
  } 
  
  public void setProvincia(String i){
    this.provincia = i;
  } 
  
  public void setTlf(int tlf){
    this.tlf = tlf;
  } 
  
  public void setEmail1(String i){
    this.email1 = i;
  } 
  
  public void setEmail2(String i){
    this.email2 = i;
  } 

  public void setDisponibilidad(boolean b){
    this.disponibilidad = b;
  }

  public void setObservaciones(String i){
    this.observaciones = i;
  }

   public String getId_alumno(){
    return id_alumno;
  } 
  
  public String getNombre(){
    return nombre;
  } 
  
  public String getApellido1(){
    return apellido1;
  } 
  
  public String getApellido2(){
    return apellido2;
  } 
  
  public String getFnac(){
    return fnac;
  } 
  
  public String getDireccion(){
    return direccion;
  } 
  
  public String getLocalidad(){
    return localidad;
  } 
  
  public int getCp(){
    return cp;
  } 
  
  public String getProvincia(){
    return provincia;
  } 
  
  public int getTlf(){
    return tlf;
  } 
  
  public String getEmail1(){
    return email1;
  } 
  
  public String getEmail2(){
    return email2;
  } 

  public boolean getDisponibilidad(){
    return disponibilidad;
  }

  public String getObservaciones(){
    return observaciones;
  }

  public String getMailForm()
  {
    return mailForm;
  }

  public void setMailForm(String mailF)
  {
    this.mailForm =mailF;
  }

  public String getMailForm2()
  {
    return mailForm2;
  }

  public void setMailForm2(String mailF)
  {
    this.mailForm2 =mailF;
  }
}