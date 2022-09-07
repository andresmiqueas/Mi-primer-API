package com.init.products.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.products.dao.ProductsDAO;
import com.init.products.entitys.Product;


@RestController
@RequestMapping("products")
public class ProductsREST {

	
	
	@Autowired
	private ProductsDAO productDAO;
	
	
	@GetMapping
	public ResponseEntity<List<Product>> getProduct(){
		List<Product> products = productDAO.findAll(); //Conexion a la base datos, consulto registros, convirtio registros en objeto y lo regreso como una lista
		return ResponseEntity.ok(products);
		
	}
	
	
	@RequestMapping(value= "{productId}") // /products/{productId} -> /products/1
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId){
		Optional<Product> optionalProduct = productDAO.findById(productId);
		if (optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		}else {
			return ResponseEntity.noContent().build();
	}
	}

	@PostMapping
	public ResponseEntity <Product> createProduct(@RequestBody Product product){
		Product newProduct = productDAO.save(product);
		return ResponseEntity.ok(newProduct);
		
	}
	
	//@GetMapping
	//@RequestMapping(value="hello", method=RequestMethod.GET)
	public String hello() {
		return "Hello Word";
	}
	
}
