import axios from "axios";
import React, { useState } from "react";
import {useNavigate } from "react-router-dom";
const UpdateUser = () => {
    let user = JSON.parse(localStorage.getItem("currentUser"));
    let id = user.id;
    let [name,setName] = useState(user.name);
    let[phone,setPhone] = useState(user.phone);
    let email=user.email;
    let token=user.token;
    let status=user.status;
    let[password,setPassword] = useState(user.password);
    let[gender,setGender] = useState(user.gender);
    let cart = user.cart;
    let wishList = user.wishList;
    let data = {id, name, phone, email, token, status, password, gender,cart,wishList};
    const navigate = useNavigate()
    const handleClick=()=>{
        axios.put('http://localhost:8080/users',data)
        .then(()=>{
            alert("User data is updated.");
            navigate("/userhome");
        })
        .catch(()=>{
            alert("Something went wrong.")
        });
    }
    return ( 
        <div className="updateuser">
            <table>
                <tr>
                    <td>name  </td><td>:<input type="text" value={name} onChange={(x)=>{setName(x.target.value)}}/></td>
                </tr>
                <tr>
                    <td>Phone  </td><td>:<input type="number" value={phone} onChange={(x)=>{setPhone(x.target.value)}} /></td>
                </tr>
                <tr>
                    <td>Email  </td><td>:<input type="email" defaultValue={email} disabled/></td> {/*........... */}
                </tr>
                <tr>
                    <td>Password  </td><td>:<input type="password" value={password} onChange={(x)=>{setPassword(x.target.value)}}/></td>
                </tr>
                <tr>
                    <td>Gender  </td><td>:<input type="text" value={gender} onChange={(x)=>{setGender(x.target.value)}}/></td>
                </tr>
            </table>
            <button className="btn btn-success" onClick={handleClick}>Update</button>
        </div>
     );
}
 
export default UpdateUser;