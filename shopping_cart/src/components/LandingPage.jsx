import { Link } from "react-router-dom";
import '../style/landingpage.css';
const LandingPage=()=>{
    return (
        <div className="landingpage">
            <div className="landing">
                <div className="user">
                    <Link to= "/userlogin">
                        <img src="https://w7.pngwing.com/pngs/81/570/png-transparent-profile-logo-computer-icons-user-user-blue-heroes-logo-thumbnail.png" alt="" />
                        <h2>user login</h2>
                    </Link>
                </div>

                <div className="merchant">
                    <Link to="/merchantlogin"> 
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ7LWK9ogxQmAkLPKxRvflB2-uQcLOz37ZN03JU5lQ&s" alt="" />
                    <h2>merchant login</h2>
                    </Link>
                </div>
            </div>

        </div>
    )
}
export default LandingPage;