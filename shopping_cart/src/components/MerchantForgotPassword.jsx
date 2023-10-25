import axios from 'axios';
import { useState } from 'react';
import Form from 'react-bootstrap/Form';
const MerchantForgotPassword = () => {
    let [email,setEmail] = useState("");
    let handleclick=()=>{
        axios.post('http://localhost:8080/merchant/verify',email).then(()=>{
            alert(`OTP has been sent to your ${email}`)
        }).catch(()=>{
            alert("please enter the valid email")
        })
    }
    return ( 
        <div className="merchantforgotpassword">
            <form action="">
            <Form.Floating className="mb-3">
                <Form.Control value={email} onChange={(f)=>{setEmail( f.target.value)}}id="floatingInputCustom" type="email"
                placeholder="name@example.com"
                />
                <label htmlFor="floatingInputCustom">Merchant Email address</label>
            </Form.Floating>
            <button onClick={handleclick} className='btn btn-success px-5 py'>Submit</button>
            </form>
        </div>
     );
}
export default MerchantForgotPassword;