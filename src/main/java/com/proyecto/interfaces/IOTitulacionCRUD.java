package com.proyecto.interfaces;

import com.proyecto.model.OTitulacion;
import com.proyecto.model.Centro;
import java.util.ArrayList;

public interface IOTitulacionCRUD
{
    public OTitulacion findOT(String nombre_OT, String descripcionOt, Centro centro);

    public boolean oTitulacionUser(OTitulacion ot, String user, String fecha_fin);
    public boolean insertOtraTitulacion(OTitulacion o);
    public ArrayList <OTitulacion> buscarOtrasTitulacionesUser(String user);
    public OTitulacion removeOTUser(OTitulacion ot, String user);
}