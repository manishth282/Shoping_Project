import Form from 'react-bootstrap/Form';
import '../style/additems.css';
import { useState } from 'react';
import axios from 'axios';

// name;
// 	private String brand;/////
// 	private String category;
// 	private String description;
// 	private double cost;
// 	private String image;
// 	private double rating;
// num_of_users;
const AddItems = () => {
    let[category,setCategory]=useState("");
    let[name,setName]=useState("");
    let[cost,setMrp]=useState("");
    let[image,setThumnail]=useState("");
    let[brand,setBrand]=useState("");
    let[description,setDescription]=useState("");
    let loadData=(e)=>{
        e.preventDefault();
        let merchant = JSON.parse(localStorage.getItem("currentMerchant"));
        let data={name,brand,category,description,cost,image};
        console.log(data);
        axios.post(`http://localhost:8080/products/${merchant.id}`,data)
        .then(()=>{alert("Product is added")})
        .catch(()=>{alert("Something went wrong")});
    }
    return ( 
    <div className="addItems">
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

                {/* <Form.Group className="mb-3 boxinput" controlId="formBasicDiscount">
                    <Form.Label>Discount Price</Form.Label>
                    <Form.Control value={discount} onChange={(e)=>{setDiscount(e.target.value)}} type="number" placeholder="Discount Price"/>
                </Form.Group> */}

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
            <button className="btn btn-success px-5" onClick={loadData}>Add Items</button>
            </form>
    </div> 
    );
}
 
export default AddItems;