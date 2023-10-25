package org.jsp.api.controller;

import java.util.List;

import org.jsp.api.dto.Product;
import org.jsp.api.dto.ResponseStructure;
import org.jsp.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000/")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@PostMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product, @PathVariable int id){
		return service.saveProduct(product,id);
	}
	@PutMapping("/products/{mid}")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product, @PathVariable int mid){
		return service.updateProduct(product,mid);
	}
	@GetMapping("/productsid/{pid}")
	public ResponseEntity<ResponseStructure<Product>> findProductById(@PathVariable int pid){
		return service.findById(pid);
	}
	@GetMapping("/products/{merchant_id}")
	public ResponseEntity<ResponseStructure<List<Product>>> findById(@PathVariable int merchant_id){
		return service.findProductsByMerchantId(merchant_id);
	}
	@GetMapping("/products/all")
	public ResponseEntity<ResponseStructure<List<Product>>> findAll(){
		return service.findAllProducts();
	}
	@DeleteMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int id){
		return service.deleteProduct(id);
	}
	
	/////////
//	@GetMapping("/products/bybrand/{brand}")
//	public ResponseEntity<ResponseStructure<List<Product>>> findProductByBrand(@PathVariable String brand){
//		return service.findProductByBrand(brand);  ////
//	}
//	@GetMapping("/products/bycategory/{category}")
//	public ResponseEntity<ResponseStructure<List<Product>>> findProductByCategory(@PathVariable String category){
//		return service.findProductByCategory(category); ////
//	}
	/////////
	
	@PostMapping("/products/{product_id}/{user_id}")
	public ResponseEntity<ResponseStructure<String>> addToCart(@PathVariable int user_id, @PathVariable int product_id){
		return service.addToCart(product_id, user_id);
	}
	@PostMapping("/products/add/{product_id}/{user_id}")
	public ResponseEntity<ResponseStructure<String>> addToWishList(@PathVariable int user_id, @PathVariable int product_id){
		return service.addToWishList(product_id, user_id);
	}
	@PutMapping("/products/rate/{pid}/{uid}")
	public ResponseEntity<ResponseStructure<Product>> rateProduct(@PathVariable int pid, @PathVariable int uid,
			@RequestParam double rating){
		return service.rateProduct(pid, uid, rating);
	}
	@GetMapping("/products/cart/{id}")
	public ResponseEntity<ResponseStructure<List<Product>>> findProductsInCart(@PathVariable int id){
		return service.findProductInCart(id);
	}
	@GetMapping("/products/wishlist/{id}")
	public ResponseEntity<ResponseStructure<List<Product>>> findProductsInWishList(@PathVariable int id){
		return service.findProductInWishList(id);
	}
}
