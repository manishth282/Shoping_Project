import Form from 'react-bootstrap/Form';
import '../style/merchantsignup.css';
import {useState} from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
const MerchantSignUp = () => {
    const  [name, setName]= useState("");
    const  [phone,setPhone]= useState("");
    const  [email, setEmail]= useState("");
    const  [password, setPassword]= useState("");
    const  [gst, setGst]= useState("");

    let data = {name, phone, email, password, gst}
    let handleInput = ()=>{
        // show(data)
        console.log(data);
        axios.post('http://localhost:8080/merchants',data).then(()=>{alert("Data is added to Database")}).catch(()=>{alert("invalid input")})

    }
    // let show=(e)=>{
    //     console.log(e)
    // }
    return (

        <div className="merchantsignup">
            <form id="signup"action="">
            <Form>
                <Form.Group className="mb-3" >
                        <Form.Label>Name</Form.Label>
                        <Form.Control type="text" placeholder="Name"value={name} onChange={(f)=>{setName(f.target.value)}}/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicName">
                        <Form.Label>Phone</Form.Label>
                        <Form.Control type="tel" placeholder="phone"value={phone} onChange={(f)=>{setPhone(f.target.value)}} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email"value={email} onChange={(f)=>{setEmail(f.target.value)}} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password"value={password} onChange={(f)=>{setPassword( f.target.value)}}/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicGst">
                    <Form.Label>GST</Form.Label>
                    <Form.Control type="Gst " placeholder="Gst"value={gst} onChange={(f)=>{setGst(f.target.value)}} />
                </Form.Group>
            </Form>
            <Link to="/merchantlogin"><button className="btn btn-success px-5" onClick={handleInput}>Register</button></Link>
            </form>
        </div>
    );
}
 
export default MerchantSignUp;