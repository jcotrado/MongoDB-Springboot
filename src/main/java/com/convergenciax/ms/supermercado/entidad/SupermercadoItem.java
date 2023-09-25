package com.convergenciax.ms.supermercado.entidad;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("SupermercadoItem")
public class SupermercadoItem {

	@Id	private String id;
	
	private String nombre;
	private int  cantidad;
	private String categoria;
	
	public SupermercadoItem(String id, String nombre, int cantidad, String categoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.categoria = categoria;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
