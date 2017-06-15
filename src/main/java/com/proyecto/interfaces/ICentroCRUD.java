package com.proyecto.interfaces;
import java.util.ArrayList;

import com.proyecto.model.Centro;


public interface ICentroCRUD
{
	public boolean insertCentro(String n);
	public Centro findCentroById(int id);
	public Centro findCentro(String n_centro);
}
