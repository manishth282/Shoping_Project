package org.jsp.api.service;

import java.util.List;
import java.util.Optional;

import org.jsp.api.dao.MerchantDao;
import org.jsp.api.dao.ProductDao;
import org.jsp.api.dao.UserDao;
import org.jsp.api.dto.Merchant;
import org.jsp.api.dto.Product;
import org.jsp.api.dto.ResponseStructure;
import org.jsp.api.dto.User;
import org.jsp.api.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {
	@Autowired
	private ProductDao dao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private UserDao udao;
	
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product,int mid){
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(mid);
		if(recMerchant.isPresent()) {
			recMerchant.get().getProducts().add(product);
			product.setMerchant(recMerchant.get());
			dao.saveProduct(product);
			merchantDao.updateMerchant(recMerchant.get());//////////why it need to update merchant product is owning side
			
			structure.setData(product);
			structure.setStatuscode(HttpStatus.CREATED.value());
			structure.setMessage("Product added");
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product, int mid){
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(mid);
		if(recMerchant.isPresent()) {
			Product recProduct = dao.findById(product.getId()).get();
			recProduct.setBrand(product.getBrand());
			recProduct.setName(product.getName());
			recProduct.setCategory(product.getCategory());
			recProduct.setDescription(product.getDescription());
			recProduct.setCost(product.getCost());
			recProduct.setImage(product.getImage());
			
			recMerchant.get().getProducts().add(recProduct);//////
			product.setMerchant(recMerchant.get());
			structure.setData(dao.updateProduct(recProduct));
			merchantDao.updateMerchant(recMerchant.get());//////////
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			structure.setMessage("Product updated");
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Product>> findById(int id){
		Optional<Product> recProduct = dao.findById(id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if(recProduct.isPresent()) {
			structure.setData(recProduct.get());
			structure.setMessage("Product Found");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteProduct(int id){
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Product> recProduct = dao.findById(id);
		if(recProduct.isPresent()) {
			dao.deleteProduct(id);
			structure.setMessage("Product deleted");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findProductsByMerchantId(int merchant_id){
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(dao.findProductByMerchantId(merchant_id));
		structure.setStatuscode(HttpStatus.OK.value());
		structure.setMessage("Product loaded");
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts(){
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(dao.findAllProducts());
		structure.setMessage("Products Found");
		structure.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}
	
	/////////////////////
//	public ResponseEntity<ResponseStructure<List<Product>>> findProductByBrand(String brand){
//		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
////		Optional<List<Product>> listProducts = dao.findProductByBrand(brand);
//		List<Product> listProducts = dao.findProductByBrand(brand);
////		if(listProducts.isPresent()) {
//		if(listProducts.size()!=0) {
////			structure.setData(listProducts.get());
//			structure.setData(listProducts);
//			structure.setMessage("Products Found");
//			structure.setStatuscode(HttpStatus.OK.value());
//			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
//		}
//		throw new IdNotFoundException();
//	}
//	public ResponseEntity<ResponseStructure<List<Product>>> findProductByCategory(String category){
//		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
////		Optional<List<Product>> listProducts = dao.findProductByCategory(category);
//		List<Product> listProducts = dao.findProductByCategory(category);
////		if(listProducts.isPresent()) {
//		if(listProducts.size()!=0) {
////			structure.setData(listProducts.get());
//			structure.setData(listProducts);
//			structure.setMessage("Products Found");
//			structure.setStatuscode(HttpStatus.OK.value());
//			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
//		}
//		throw new IdNotFoundException();
//	}
	///////////////
	
	public ResponseEntity<ResponseStructure<String>> addToCart(int product_id, int user_id){
		Optional<User> recUser = udao.findById(user_id);/////
		Optional<Product> recProduct = dao.findById(product_id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if(recUser.isPresent() && recProduct.isPresent()) {
			recUser.get().getCart().add(recProduct.get());////
			udao.updateUser(recUser.get());////
			structure.setData("Product added to the cart.");
			structure.setMessage("User and Product found.");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);
		}
		structure.setData("Cannot add Product to the cart.");
		structure.setMessage("Invalid UserId or Product Id.");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	public ResponseEntity<ResponseStructure<String>> addToWishList(int product_id, int user_id){
		Optional<User> recUser = udao.findById(user_id); 
		Optional<Product> recProduct = dao.findById(product_id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if(recUser.isPresent() && recProduct.isPresent()) {
			recUser.get().getWishList().add(recProduct.get());
			udao.updateUser(recUser.get());///
			structure.setData("Product added to the WishList.");
			structure.setMessage("User and Product found.");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);
		}
		structure.setData("Cannot add Product to the WishList");
		structure.setMessage("Invalid User Id or Product Id.");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	public ResponseEntity<ResponseStructure<List<Product>>>findProductInCart(int id){
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(dao.findProductsInCart(id));
		structure.setMessage("Following are the list of products in cart.");
		structure.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>>findProductInWishList(int id){
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(dao.findProductInWishList(id));
		structure.setMessage("Following are the list of products in wishList.");
		structure.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Product>> rateProduct(int product_id, int user_id, double ratings){
		Optional<User> recUser = udao.findById(user_id);
		Optional<Product> recProduct = dao.findById(product_id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if(recUser.isPresent() && recProduct.isPresent()) {
			Product p = recProduct.get();
			int n = p.getNum_of_users();
			double r = p.getRating();
			ratings = (r + ratings)/++n;
			p.setNum_of_users(n);
			p.setRating(ratings);
			dao.updateProduct(p);
			
			structure.setData(p);
			structure.setMessage("Product Rated.");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
}
