import { Route, Routes } from "react-router-dom";
import MerchantDashbord from "./MerchantDashbord";
import MerchantNavbar from "./MerchantNavbar";
import AddItems from "./AddItems";
import UpdateMerchant from "./UpdateMerchant";
import ViewProMerchant from "./ViewProMerchant";
import UpdateProduct from "./UpdateProduct";

const MerchantHome = () => {
    return ( 
        <div className="merchanthome">
            <MerchantNavbar/>
            <Routes>
                <Route path="/" element={<MerchantDashbord/>}></Route>
                <Route path='/additems'element={<AddItems/>}/>
                <Route path='/updatemerchant' element={<UpdateMerchant/>}></Route>
                <Route path="/viewpromerchant/*" element={<ViewProMerchant/>}></Route>
                <Route path="/updateproduct" element={<UpdateProduct/>}></Route>
            </Routes>
        </div>
     );
}
 
export default MerchantHome;