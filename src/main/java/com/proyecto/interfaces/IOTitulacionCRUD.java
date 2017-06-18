package com.proyecto.interfaces;

import com.proyecto.model.OTitulacion;
import com.proyecto.model.Centro;
import java.util.ArrayList;

public interface IOTitulacionCRUD
{
    public OTitulacion findOT(String nombre_OT, String descripcionOt, Centro centro, String user, int fecha_fin_ot);
    public boolean insertOtraTitulacion(OTitulacion o, String user, int fecha_fin);    
    public ArrayList <OTitulacion> buscarOtrasTitulacionesUser(String user);
    public OTitulacion removeOTUser(OTitulacion ot, String user);
}