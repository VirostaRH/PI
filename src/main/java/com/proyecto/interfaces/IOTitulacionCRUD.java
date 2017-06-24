package com.proyecto.interfaces;

import com.proyecto.model.OTitulacion;
import com.proyecto.model.Centro;
import java.util.ArrayList;

public interface IOTitulacionCRUD

{
	public boolean insertOT(String nombre, String descripcion, Centro centro);
    public boolean buscarOTbyNameAndCentro(String nombre_ot, Centro centro);
	public OTitulacion buscarOTbyNameAndCentroObj(String nombre_ot, Centro centro, String fecha_fin);
    public OTitulacion buscarById(int id_ot);
}