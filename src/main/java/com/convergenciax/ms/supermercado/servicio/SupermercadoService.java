package com.convergenciax.ms.supermercado.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.convergenciax.ms.supermercado.entidad.SupermercadoItem;
import com.convergenciax.ms.supermercado.repositorio.SupermercadoRepository;

@Service
public class SupermercadoService {
	
	@Autowired
	private SupermercadoRepository supermercadoItemRepository;
	
   
	public List<SupermercadoItem> getAllSupermercadoItems() {         
		//supermercadoItemRepository.findAll().forEach(item -> System.out.println(getItemDetails((SupermercadoItem) item)));
		List<SupermercadoItem> items =	supermercadoItemRepository.findAll();
		return items;
    }
	
	public SupermercadoItem insert(SupermercadoItem item) {         		
		SupermercadoItem supermercadoItem =	supermercadoItemRepository.insert(item);
		findCountOfSupermercadoItems();
		return supermercadoItem;
    }
	
	
	public SupermercadoItem findItemByName(String nombre) {
	         System.out.println("Obteniendo items por nombre : " + nombre);
	         SupermercadoItem item =  supermercadoItemRepository.findItemByName(nombre);
	         
	         if (item != null && item.getId().length() > 0)
	        	 System.out.println(getItemDetails(item));
	         return item;
	}
	
	
    public List<SupermercadoItem>  getItemsByCategory(String category) {
        System.out.println("Getting items for the category " + category);
        List<SupermercadoItem> list = supermercadoItemRepository.findAll(category);
        
        list.forEach(item -> System.out.println("Name: " + item.getNombre() + ", Quantity: " + item.getCantidad()));
        return list;
    }
	
    public void findCountOfSupermercadoItems() {
        long count = supermercadoItemRepository.count();
        System.out.println("Numero de documentos en la collection:[" + count+"]");
    }
    
	    
    /**
     * Actualizar el nombre de la categoria
     * 
     * @param category
     * @param newCategory
     */
    public boolean updateCategoryName(String categoria, String nuevaCategoria) {
        
        // Obtiene todas los items de la categoria y lo actualiza con nuevo valor de categoria
        List<SupermercadoItem> list = supermercadoItemRepository.findAll(categoria);
        
        if (list.isEmpty()) {
        	System.out.println("No se encontro el valor de categoria ["+categoria+"], no se actualizan items");
        	return false;
        }
        
        list.forEach( item -> {
            // Update the category in each document
            item.setCategoria(nuevaCategoria);
        });
        
        // Save all the items in database
        List<SupermercadoItem> itemsUpdated = supermercadoItemRepository.saveAll(list);
        
        if(itemsUpdated != null) {
            System.out.println("Actualización exitosa por categoria, actualiza valor de ["+categoria+"] a ["+nuevaCategoria+"]  Nro Items actializados ["+
            					itemsUpdated.size() + "] items.");
            return true;
        }
        
        System.out.println("No se logro actualizar el  valor de ["+categoria+"] por ["+nuevaCategoria+"]");
        return false;
            
            
    }
    
    /**
     * 
     * @param nombre
     * @return
     */


 	public boolean deleteSupermercadoItem(String nombre) {
    	    	
    	SupermercadoItem item =  supermercadoItemRepository.findItemByName(nombre);
    	  	
    	if (item == null  ) 
    		return false;
    	
    	try {
    	System.out.print(" Service - deleteSupermercadoItem : " + item.getId());
    	
    	supermercadoItemRepository.deleteById(item.getId());
  
     	System.out.println("Item con nombre %s " + item.getNombre() + " id ["+ item.getId() +"] eliminado...");
    	findCountOfSupermercadoItems();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    	
    	return true;
    }
 	
 	/**
 	 * 
 	 * @param id
 	 * @return
 	 */

 	public boolean deleteSupermercadoItemById(String id) {
    	
 		Optional<SupermercadoItem> item =  supermercadoItemRepository.findById(id);
    	  	
    	if (item == null || item.isEmpty()) 
    		return false;
    	
    	try {
    	System.out.print(" Service - deleteSupermercadoItem : " + item.get().getId());
    	
    	supermercadoItemRepository.deleteById(item.get().getId());
    	 
  
     	System.out.println("Item con nombre %s " + item.get().getNombre() + " id ["+ item.get().getId() +"] eliminado...");
    	findCountOfSupermercadoItems();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    	
    	return true;
    }
 	
    
    public String getItemDetails(SupermercadoItem item) {

        System.out.println(
        "Item Nombre:[ " + item.getNombre() + 
        "], \nCantidad: " + item.getCantidad() +
        ", \nItem Category: " + item.getCategoria()
        );
 
        return "";
    }
	
}
