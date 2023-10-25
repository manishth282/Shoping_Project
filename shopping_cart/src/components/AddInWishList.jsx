import axios from "axios";
import { useEffect, useState } from "react";
import FavoriteIcon from '@mui/icons-material/Favorite';
const AddInWishList = () => {

    let [content, setContent] = useState([]);
    let user = JSON.parse(localStorage.getItem('currentUser'));

    useEffect(()=>{
        let fetchData = ()=>{
            axios.get(`http://localhost:8080/products/wishlist/${user.id}`)
            .then((res)=>{
                setContent(res.data.data);
            })
            .catch(()=>{
                alert("Error");
            })
        }
        fetchData();
    },[])

    return ( 
        <div className="addinwishlist">
            <h3>WishList</h3>
            {content.map((x)=>{
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
                    <div className="buttons">
                    <button id="addtocart"  className="btn btn-success">Add To Cart</button>
                    <FavoriteIcon/>
                    </div>
                </div>
            </div>
        )
      })}
        </div>
     );
}
 
export default AddInWishList;