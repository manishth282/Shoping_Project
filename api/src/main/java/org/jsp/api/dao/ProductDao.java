package org.jsp.api.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.api.dto.Product;
import org.jsp.api.repository.ProductRepository;
import org.jsp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository repository;
	@Autowired
	private UserRepository userRepository;
	
	public Product saveProduct(Product product) {
		return repository.save(product);
	}
	public Product updateProduct(Product product) {
		return repository.save(product);
	}
	public Optional<Product> findById(int id){
		return repository.findById(id);
	}
	public void deleteProduct(Integer id) {
		repository.deleteById(id);
	}
	public List<Product> findProductByMerchantId(int merchant_id){
		return repository.findProductsByMerchantId(merchant_id);
	}
	///////////
////	public Optional<List<Product>> findProductByBrand(String brand){\
//	public List<Product> findProductByBrand(String brand){
//		return repository.findProductByBrand(brand);
//	}
////	public Optional<List<Product>> findProductByCategory(String category){
//	public List<Product> findProductByCategory(String category){
//		return repository.findProductByCategory(category);
//	}
	/////
	public List<Product> findAllProducts(){
		return repository.findAll();
	}
	public List<Product> findProductsInCart(int id){
		return userRepository.findProductsInCart(id);
	}
	public List<Product> findProductInWishList(int id){
		return userRepository.findProductInWishList(id);
	}
}
