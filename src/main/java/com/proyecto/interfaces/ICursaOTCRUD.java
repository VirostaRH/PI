package com.proyecto.interfaces;
import java.util.ArrayList;

import com.proyecto.model.*;


public interface ICursaOTCRUD
{
	public void insertCursaOT(OTitulacion id_OTitulacion, String user);
	public ArrayList <OTitulacion> buscarOtrasTitulacionesUser(String user);
	public boolean buscarOTCursadaByUserAndOT(OTitulacion ot, String user);
	public void deleteCursaOT(OTitulacion ot, String user);
}