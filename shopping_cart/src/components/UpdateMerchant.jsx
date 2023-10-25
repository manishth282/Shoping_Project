import axios from "axios";
import { useState } from "react";
import "../style/merchantupdate.css";
const UpdateMerchant = () => {
    let merchant = JSON.parse(localStorage.getItem("currentMerchant"));
    let id = merchant.id;
    let [name, setName] = useState(merchant.name);
    let [phone, setPhone] = useState(merchant.phone);
    let [email, setEmail] = useState(merchant.email);
    let [password, setPassword] = useState(merchant.password);
    let [gst, setGst] = useState(merchant.gst);
    let status = merchant.status;
    let token = merchant.token;
    let data = {id, name, phone, email, password, gst, status, token};
    let handleInput = ()=>{
        axios.put('http://localhost:8080/merchants', data)
        .then(()=>{alert("Data is added to Database")})
        .catch(()=>{alert("invalid input")})
    }
    return ( 
        <div className="update-merchant-container">
            Name :
            <input type="text" value={name} onChange={(e) => setName(e.target.value)}/> <br />
            Phone no :
            <input type="number" value={phone} onChange={(e) => setPhone(e.target.value)}/> <br />
            Email :
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} disabled/> <br />
            Password :
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)}/> <br />
            GST :
            <input type="text" value={gst} onChange={(e) => setGst(e.target.value)}/> <br />
            <button className="submit-button" onClick={handleInput}>Update</button>
        </div>
     );
}
 
export default UpdateMerchant;