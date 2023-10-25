import axios from 'axios';
import { useState } from 'react';
import Form from 'react-bootstrap/Form';

const UpdateProduct = () => {
    const product = JSON.parse(localStorage.getItem("currentProduct"));
    const merchant = JSON.parse(localStorage.getItem("currentMerchant"));
    let id = product.id;
    let[category,setCategory]=useState(product.category);
    let[name,setName]=useState(product.name);
    let[cost,setMrp]=useState(product.cost);
    let[image,setThumnail]=useState(product.image);
    let[brand,setBrand]=useState(product.brand);
    let[description,setDescription]=useState(product.description);
    let data={id,name,brand,category,description,cost,image};
    let loadData=()=>{
        axios.put(`http://localhost:8080/products/${merchant.id}`,data)
        .then(()=>{alert("Product is updated.")})
        .catch(()=>{alert("Something went wrong.")});
    }
    return ( 
        <div className="updateproduct">
            <h2>Update Product {product.name}</h2>
            
            <form id="additems"action="">
                <Form>
                Category:
                    <select value={category} onChange={(e)=>{setCategory(e.target.value)}} >
                        <option>Mobile</option>
                        <option>Laptop</option>
                        <option>TV</option>
                        <option>Grocery</option>
                        <option></option>
                    </select>
                        
                    <Form.Group className="mb-3 boxinput" controlId="formBasicName">
                            <Form.Label>Product Name</Form.Label>
                            <Form.Control value={name} onChange={(e)=>{setName(e.target.value)}} type="text" placeholder="Product Name"/>
                    </Form.Group>
                    <Form.Group className="mb-3 boxinput" controlId="formBasicCost">
                            <Form.Label>M.R.P</Form.Label>
                            <Form.Control value={cost} onChange={(e)=>{setMrp(e.target.value)}} type="number" placeholder="Product Name"/>
                    </Form.Group>

                    <Form.Group className="mb-3 boxinput" controlId="formBasicImage">
                        <Form.Label>Thumbnail Image</Form.Label>
                        <Form.Control value={image} onChange={(e)=>{setThumnail(e.target.value)}} type="text" placeholder="Image url"/>
                    </Form.Group>

                    <Form.Group className="mb-3 boxinput" controlId="formBasicBrand">
                        <Form.Label>Brand Name</Form.Label>
                        <Form.Control value={brand} onChange={(e)=>{setBrand(e.target.value)}} type="text" placeholder="No. of stock"/>
                    </Form.Group>

                    <Form.Group className="mb-3 boxinput" controlId="formBasicDiscription">
                        <Form.Label>Discription</Form.Label>
                        <Form.Control value={description} onChange={(e)=>{setDescription(e.target.value)}} type="text" placeholder="Discription" />
                    </Form.Group>

                </Form>
                <button className="btn btn-success px-5" onClick={()=>loadData()}>Update Items</button>
                </form>
        </div>
     );
}
 
export default UpdateProduct;