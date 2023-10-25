import Form from 'react-bootstrap/Form';
import '../style/usersignup.css';
import {useState} from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
const UserSignUp = () => {
    const  [name, setName]= useState("");
    const  [phone,setPhone]= useState("");
    const  [email, setEmail]= useState("");
    const  [password, setPassword]= useState("");
    const [gender, setGender] = useState("");
    let data = {name, phone, email, password,gender}
    let handleInput = (e)=>{
        console.log(data);
        axios.post('http://localhost:8080/users',data).then(()=>{alert("Data is added to Database")}).catch(()=>{alert("invalid input")});
    }
    return (

        <div className="usersignup">
            <form id="signup"action="">
            <Form>
                <Form.Group className="mb-3" controlId="formBasicName">
                        <Form.Label>Name</Form.Label>
                        <Form.Control type="text" placeholder="Name"value={name} onChange={(f)=>{setName(f.target.value)}}/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPhone">
                        <Form.Label>Phone</Form.Label>
                        <Form.Control type="tel" /*pattern="[789][0-9]{10}"*/ placeholder="phone"value={phone} onChange={(f)=>{setPhone(f.target.value)}} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email" value={email} onChange={(f)=>{setEmail(f.target.value)}} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password"value={password} onChange={(f)=>{setPassword( f.target.value)}}/>
                </Form.Group>
                
                Male <input type="radio" name="check" value="male" onChange={(e)=>{setGender(e.target.value)}}/>
                Female <input type="radio" name="check"  value="female" onChange={(e)=>{setGender(e.target.value)}}/>
            </Form>
            <br />
            <Link to="/userlogin"><button className="btn btn-success px-5" onClick={handleInput}>Register</button></Link>
            </form>
        </div>
    );
}
 
export default UserSignUp;