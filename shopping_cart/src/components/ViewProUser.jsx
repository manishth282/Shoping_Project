import React from "react";
import axios from "axios";
import '../style/viewproduct.css';
import { useEffect, useState } from "react";
import FavoriteIcon from '@mui/icons-material/Favorite';

const ViewProUser = () => {
let[products, setProducts]=useState([])
let user = JSON.parse(localStorage.getItem("currentUser"))
useEffect(()=>{
     let fetchData = () =>{
        axios.get(`http://localhost:8080/products/all`)
        .then((res)=>{
            setProducts(res.data.data)
            console.log(products);
        })
        .catch(()=>{
            alert("not a good request")
        })
    }
    fetchData()
},[])

let addProducts = (id)=>{
    axios.post(`http://localhost:8080/products/${id}/${user.id}`)
    .then(()=>{
        alert("product added to cart")
    })
    .catch(()=>{
        alert("something went wrong")
    });
}

let addProductswishlist = (id)=>{
    axios.post(`http://localhost:8080/products/add/${id}/${user.id}`)
    .then((res)=>{
        console.log(res.data.data);
        alert("added to wishlist")
    }).catch(()=>[
        alert("something went wrong")
    ]);
}
return(
    // name// brand// category// description// cost// image// rating
<div className="productbody">
    <div className="products">
      {products.map((x)=>{
        return(
            <div className="display">
                <div className="page">
                    <div className="image">
                        <img src={x.image} alt="" />
                    </div>
                    <div className="details">
                        <hr />
                        <h3>{x.name}</h3>
                        <span id="offer">Flat INR 2000 Off in ICICI CreditCard</span> <br />
                        <b>MRP Price: <strike>{x.cost}</strike></b> <br />
                        <h5 id="discount"> Discount Price: {x.cost - (x.cost * 12/100)}</h5>
                    </div>
                    <div className="buttons">
                    <button id="addtocart"  className="btn btn-success" onClick={()=>{addProducts(x.id)}}>Add To Cart</button>
                    <FavoriteIcon onClick={()=>{addProductswishlist(x.id)}}/>
                    </div>
                </div>
            </div>
        )
      })}
    </div>
</div>
);
}
 
export default ViewProUser;