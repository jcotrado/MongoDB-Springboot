package com.convergenciax.ms.supermercado.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.convergenciax.ms.supermercado.entidad.SupermercadoItem;

public interface SupermercadoRepository extends MongoRepository<SupermercadoItem, String> {
	
	@Query("{nombre:'?0'}")
	SupermercadoItem  findItemByName(String nombre);
	
	@Query(value="{categoria:'?0'}", fields="{'nombre':1,'cantidad':1,'categoria':1}")
	List <SupermercadoItem> findAll(String categoria);
	 	
	@Query(value="{'nombre' : $0}", delete = true)
	boolean deleteSupermercadoItem(String nombre);
	
	@Query(value="{'id' : $0}", delete = true)
	boolean deleteSupermercadoItemById(String id);
	
	@Query(value="{categoria: '?0' , nuevaCategoria: '?0'}")
	boolean updateCategoryName(String categoria, String nuevaCategoria); 
	
	public long count();

	void deleteById(SupermercadoItem supermercadoItem);
	
	

}
