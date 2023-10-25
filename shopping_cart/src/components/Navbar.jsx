import { Link} from "react-router-dom";
import '../style/navbar.css'
import { useState } from "react";
const Navbar = () => {
    let user = JSON.parse(localStorage.getItem("currentUser"));
    let [sub, setSub]=useState(false);
    return (
        <div className="navbar">
            <div className="nav">
                <div className="logo">
                    <img src="https://assets.ajio.com/static/img/Ajio-Logo.svg" alt="" />
                </div>

                <div className="options">
                    <div className="wishlist">
                        <Link to="addinwishlist"><h3>Wishlist</h3></Link>
                        <Link to='addincart'>AddInCart</Link>
                    </div>
                </div>

                <div className="search">
                    <input type="text" placeholder="Search for Product, Brands and More" />
                    <button className="btn btn-info px-3">Search</button>
                </div>
                {user.name}
                <div onClick={()=>{setSub(!sub)}} className="profile">
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ7LWK9ogxQmAkLPKxRvflB2-uQcLOz37ZN03JU5lQ&s" alt="" />
                    {sub &&<div className="list">
                    <Link to="" className="li" id="li1">Profile</Link>
                    <Link to="updateuser" className="li" id="li2">Update</Link>
                    <Link to="" className="li" id="li3">Check Order</Link>
                    <Link to="/" className="li" id="li4">Logout</Link>
                    </div>
                    }
                </div>
            </div>
        </div>
     );
}
 
export default Navbar;