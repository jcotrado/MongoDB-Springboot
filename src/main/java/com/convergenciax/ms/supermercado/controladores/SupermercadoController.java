package com.convergenciax.ms.supermercado.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.convergenciax.ms.supermercado.entidad.SupermercadoItem;
import com.convergenciax.ms.supermercado.servicio.SupermercadoService;

@RestController
@RequestMapping("/supermercado")
public class SupermercadoController {

	
	@Autowired
	private SupermercadoService supermercadoService;
	
	@GetMapping
	public ResponseEntity<List<SupermercadoItem>> listarSupermercadoItems(){
		
		List<SupermercadoItem> items = 	supermercadoService.getAllSupermercadoItems();
		if (items ==null || items.isEmpty()) 			
			return ResponseEntity.notFound().build();
		
		//System.out.println(ResponseEntity.ok(items).toString());
		return ResponseEntity.ok(items);
	}

	@PostMapping("/item")
	public ResponseEntity<SupermercadoItem> insert(@RequestBody SupermercadoItem item){
		System.out.print("Insertar...["+item+"]");
		SupermercadoItem  supermercadoItem = supermercadoService.insert(item);
		//System.out.println(ResponseEntity.ok(supermercadoItem).toString());
		return ResponseEntity.ok(supermercadoItem);
	}
	
	@GetMapping("/item/{nombre}")
	public ResponseEntity<SupermercadoItem>  findItemByName(@PathVariable("nombre") String nombre) {
		System.out.print("Encontrar item por nombre ..:" + nombre);
		SupermercadoItem  supermercadoItem = supermercadoService.findItemByName(nombre);
		
		if (supermercadoItem == null ) {
			System.out.println("No encontrado" + ResponseEntity.notFound().build().toString());
			return ResponseEntity.notFound().build();
		}
		
		System.out.println(ResponseEntity.ok(supermercadoItem).toString());
		return ResponseEntity.ok(supermercadoItem);
	
	}


	@DeleteMapping("/item/{nombre}/del")
	public ResponseEntity <?> deleteSupermercadoItem(@PathVariable("nombre") String nombre) {
		System.out.println(" Controller - deleteSupermercadoItem : " + nombre);
		if ( !supermercadoService.deleteSupermercadoItem(nombre))
			return new ResponseEntity<>("Idem %s" + nombre + " No fue posible eliminarlo de la coleccion..", HttpStatus.NOT_MODIFIED);
		
	  return new ResponseEntity<>("Item " + nombre + " Eliminado de coleccion...", HttpStatus.OK);
	}
	
	
	@DeleteMapping("/item/{id}/delid")
	public ResponseEntity <?> deleteSupermercadoItemById(@PathVariable("id") String id) {
		System.out.println(" Controller - deleteSupermercadoItemById : id" + id);
		if ( !supermercadoService.deleteSupermercadoItemById(id))
			return new ResponseEntity<>("Item id:" + id + " No fue posible eliminarlo de la coleccion..", HttpStatus.NOT_MODIFIED);
		
	  return new ResponseEntity<>("Item " + id + " Eliminado de coleccion...", HttpStatus.OK);
	}
	
	
	
	@GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<SupermercadoItem>> getItemsByCategory(@PathVariable("categoria") String categoria) {
        System.out.println("Obtener Items de supermercado de categoria [" + categoria +"]");
        List<SupermercadoItem> list =supermercadoService.getItemsByCategory(categoria);
        
		if (list == null || list.isEmpty()) {
			System.out.println("No encontrado" + ResponseEntity.notFound().build().toString());
			return ResponseEntity.notFound().build();
		}
		
		System.out.println(ResponseEntity.ok(list).toString());
        
        return ResponseEntity.ok(list);
    }

	
	@PutMapping("/categoria/{categoria}/actualizar/{nuevaCategoria}")
    public ResponseEntity<?> getItemsByCategory(@PathVariable("categoria") String categoria, 
    											@PathVariable("nuevaCategoria") String nuevaCategoria) {
        
		System.out.println("Actualizar categoria en Items de [" + categoria +"] a nueva categoria ["+nuevaCategoria+"]");
        
        if ( supermercadoService.updateCategoryName(categoria,nuevaCategoria) )      
        	return new ResponseEntity<>("Actualización exitosa de valor de categoria ["+categoria+"] a ["+nuevaCategoria+"]", HttpStatus.OK);
            
          
        return new ResponseEntity<>("No se pudo actualizar el valor de categoria : ["+categoria+"] a ["+nuevaCategoria+"]", HttpStatus.NOT_MODIFIED);		
		
        
    }

 


	

}
