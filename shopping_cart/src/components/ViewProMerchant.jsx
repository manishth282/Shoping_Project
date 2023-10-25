import React from "react";
import axios from "axios";
import '../style/viewproduct.css';
import { useEffect, useState } from "react";
import { useNavigate} from "react-router-dom";

const ViewProMerchant = () => {
let[products, setProducts]=useState([])
let merchant = JSON.parse(localStorage.getItem("currentMerchant"))
let navigate = useNavigate();

useEffect(()=>{
     let fetchData = () =>{
        axios.get(`http://localhost:8080/products/${merchant.id}`)
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

let updateButton=(x)=>{
    axios.get(`http://localhost:8080/productsid/${x}`)
    .then((res)=>{
        localStorage.setItem("currentProduct",JSON.stringify(res.data.data));
        navigate('/merchanthome/updateproduct');
    })
    .catch(()=>{
        alert("Something went wrong.");
    })
}
return(
    // name// brand// category// description// cost// image// rating
<div className="productbody">
    {/* <div className="products">
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
                    <div className="button">
                        <button id="updateButton" onClick={()=>updateButton(x.id)} className="btn btn-success">Update</button>
                    </div>
                </div>
            </div>
        )
      })}
    </div> */}

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
                    <div className="button">
                        <button id="updateButton" onClick={()=>updateButton(x.id)} className="btn btn-success">Update</button>
                    </div>
                </div>
            </div>
        )
      })}
    </div>



</div>
);
}
 
export default ViewProMerchant;