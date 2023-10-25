import Form from 'react-bootstrap/Form';
import '../style/merchantlogin.css';
import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import axios from 'axios';
const MerchantLogin =()=>{
        let [uname, setUname] = useState("");
        let [pwd, setPwd] = useState("");
        let navigate=useNavigate();
        let handleSubmit=(e)=>{
            e.preventDefault();
            // if(uname=='abc' && pwd==123){
            //         navigate("/merchanthome");
            //     }
            axios.post(`http://localhost:8080/merchants/verify-login?email=${uname}&password=${pwd}`)
            .then((response)=>{
                localStorage.setItem("currentMerchant",JSON.stringify(response.data.data));
                navigate('/merchanthome')})
            .catch(()=>{alert("merchant not found.")})
        }
    return(
        <div className='merchantlogin'>
            <div className="input_merchant">
            <form action="">
            <Form.Floating className="mb-3">
                <Form.Control value={uname} onChange={(f)=>{setUname( f.target.value)}}id="floatingInputCustom" type="email"
                placeholder="name@example.com"
                />
                <label htmlFor="floatingInputCustom">Merchant Email address</label>
                </Form.Floating>

                <Form.Floating>
                <Form.Control value={pwd} onChange={(f)=>{ setPwd(f.target.value)}}id="floatingPasswordCustom"type="password"
                placeholder="Password"
                />
                <label htmlFor="floatingPasswordCustom">Password</label>
            </Form.Floating>
            <button onClick={handleSubmit} className='btn btn-outline-success'>Sign in</button>
            <Link to="/merchantsignup"><button className='btn btn-danger'>Sign up</button></Link>
            </form>
            </div>
        </div>
    )
}
export default MerchantLogin;