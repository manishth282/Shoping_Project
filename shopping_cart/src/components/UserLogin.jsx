import Form from 'react-bootstrap/Form';
import '../style/userlogin.css';
import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import axios from 'axios';
const UserLogin =()=>{
    let [email, setEmail] = useState("");
    let [password, setPwd] = useState("");
    let navigate=useNavigate();
    let handleSubmit=(e)=>{
        e.preventDefault();
            // if(uname=='abc' && pwd==123){
            //         navigate("/merchanthome");
            //     }
            axios.post(`http://localhost:8080/users/verify-login?email=${email}&password=${password}`)
            .then((response)=>{
                localStorage.setItem("currentUser",JSON.stringify(response.data.data));
                navigate('/userhome')})
            .catch(()=>{alert("user not found.")})
    }
    

    return(
        <div className='userlogin'>
            <div className="input_user">
            <form action="">
            <Form.Floating className="mb-3">
                <Form.Control value={email} onChange={(f)=>{setEmail( f.target.value)}}id="floatingInputCustom" type="email"
                placeholder="name@example.com"
                />
                <label htmlFor="floatingInputCustom">User Email address</label>
                </Form.Floating>

                <Form.Floating>
                <Form.Control value={password} onChange={(f)=>{ setPwd(f.target.value)}}id="floatingPasswordCustom"type="password"
                placeholder="Password"
                />
                <label htmlFor="floatingPasswordCustom">Password</label>
            </Form.Floating>
            <button onClick={handleSubmit} className='btn btn-outline-success'>Sign in</button>
            <Link to="/usersignup"><button className='btn btn-danger'>Sign up</button></Link>
            </form>
            </div>
        </div>
    )
}
export default UserLogin;