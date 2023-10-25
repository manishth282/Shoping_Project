import {BrowserRouter, Routes, Route} from 'react-router-dom';
import LandingPage from './components/LandingPage';
import UserLogin from './components/UserLogin';
import UserHome from './components/UserHome';
import UserSignUp from './components/UserSignUp';

import MerchantLogin from './components/MerchantLogin';
import 'bootstrap/dist/css/bootstrap.min.css';
import MerchantSignUp from './components/MerchantSignUp';
import MerchantHome from './components/MerchantHome'
import Error from './components/Error';
import MerchantForgotPassword from './components/MerchantForgotPassword';
// import AddItems from './components/AddItems';

function App() {
  return (
    <div >
      <BrowserRouter>
        <Routes>
            <Route path='/' element={<LandingPage/>}></Route>
            <Route path='/userlogin' element={<UserLogin/>}/>
            <Route path='/usersignup' element={<UserSignUp/>}/>
            <Route path='/userhome/*' element={<UserHome/>}/>

            <Route path='/merchantlogin' element={<MerchantLogin/>}/>
            <Route path='/merchantsignup' element={<MerchantSignUp/>}/>
            <Route path='/merchanthome/*' element={<MerchantHome/>}></Route>
            <Route path='*' element={<Error/>}/>
            <Route path='/merchantforgotpassword'element ={<MerchantForgotPassword/>}/>
            {/* <Route path='/userforgotpassword'element ={<UserForgotPassword/>}/> */}
        </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
