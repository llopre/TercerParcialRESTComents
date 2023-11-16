package ar.edu.ubp.das.rest.coments.controllers;

import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.ubp.das.rest.coments.beans.SugerenciaBean;
import ar.edu.ubp.das.rest.coments.beans.TemasTipoServicioBean;
import ar.edu.ubp.das.rest.coments.beans.TipoServicioBean;
import ar.edu.ubp.das.rest.coments.respositories.ComentsRepository;


@RestController
@RequestMapping(
  path="/coments",
  produces={MediaType.APPLICATION_JSON_VALUE}
)
public class ComentsController {

	
	@Autowired
	ComentsRepository repository;
	
	
	@PostMapping(
		path="/temastiposervicio",
		consumes={MediaType.MULTIPART_FORM_DATA_VALUE}
	)
	public ResponseEntity<List<TemasTipoServicioBean>> getTemasTipoServicio(String cod) {
		return new ResponseEntity<>(repository.getTemasTipoServicio(cod), HttpStatus.OK);
	}
	
	@PutMapping(path=("/sugerencia"))
	 public ResponseEntity <Void> insSugerencia(@RequestBody SugerenciaBean sb) {
		repository.insertSugerencia(sb);
		 return new ResponseEntity<Void>(HttpStatus.OK);
	 }
	
	@GetMapping(path="/tiposserviciosWS")
	 public ResponseEntity<List<TipoServicioBean>> getTiposServiciosWS(){
		 try {
 
			 JaxWsDynamicClientFactory jdcf = JaxWsDynamicClientFactory.newInstance();
			 Client client = jdcf.createClient("http://localhost:8082/ComentsWS/services/?wsdl");
			 Object obj[] = client.invoke("getTiposServicios");
			 client.close();

			 //request.setAttribute("respuesta", obj[0]);
    
        
			 System.out.println(obj[0].toString());
			 return new ResponseEntity<List<TipoServicioBean>>((List<TipoServicioBean>) obj[0],HttpStatus.OK);
		 }
		 catch(Exception ex) {
			 System.out.println(ex.getMessage());
			 //response.setStatus(400);
			 //request.setAttribute("error", ex.getMessage());
			
		 }
	 	return null;
	}
	

	@GetMapping(path="/tiposservicios")
	 public ResponseEntity<List<TipoServicioBean>> getTiposServicios(){
		return new ResponseEntity<>(repository.getTiposServicios(), HttpStatus.OK);
	}
}
