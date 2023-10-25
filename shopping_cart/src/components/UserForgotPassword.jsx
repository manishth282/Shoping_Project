import axios from 'axios';
import { useState } from 'react';
import Form from 'react-bootstrap/Form';

const UserForgotPassword = () => {
    let [email, setEmail] = useState();
    let handleclick = ()=>{
        axios.post('http://localhost:8080/user/verify',email).then(()=>{
            
        }).catch();
    }
    return ( 
        <div className="userforgotpassword">
            <form action="">
            <Form>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email"value={email} onChange={(f)=>{setEmail(f.target.value)}} />
                </Form.Group>
            </Form>
                <button onClick={handleclick} className='btn btn-success px-5 py-3'></button>
            </form>
        </div>
     );
}
 
export default UserForgotPassword;