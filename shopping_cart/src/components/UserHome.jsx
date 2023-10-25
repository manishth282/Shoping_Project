// import Button from 'react-bootstrap/Button';
// import Container from 'react-bootstrap/Container';
// import Form from 'react-bootstrap/Form';
// import Nav from 'react-bootstrap/Nav';
// import Navbar from 'react-bootstrap/Navbar';
// import NavDropdown from 'react-bootstrap/NavDropdown';


// function UserHome() {
//   return (
//    <div>
//      <Navbar expand="lg" className="bg-body-tertiary">
//       <Container fluid>
//         <Navbar.Brand href="#">Navbar scroll</Navbar.Brand>
//         <Navbar.Toggle aria-controls="navbarScroll" />
//         <Navbar.Collapse id="navbarScroll">
//           <Nav
//             className="me-auto my-2 my-lg-0"
//             style={{ maxHeight: '100px' }}
//             navbarScroll
//           >
//             <Nav.Link href="#action1">Home</Nav.Link>
//             <Nav.Link href="#action2">Link</Nav.Link>
//             <NavDropdown title="Link" id="navbarScrollingDropdown">
//               <NavDropdown.Item href="#action3">Action</NavDropdown.Item>
//               <NavDropdown.Item href="#action4">
//                 Another action
//               </NavDropdown.Item>
//               <NavDropdown.Divider />
//               <NavDropdown.Item href="#action5">
//                 Something else here
//               </NavDropdown.Item>
//             </NavDropdown>
//             <Nav.Link href="#" disabled>
//               Link
//             </Nav.Link>
//           </Nav>
//           <Form className="d-flex">
//             <Form.Control
//               type="search"
//               placeholder="Search"
//               className="me-2"
//               aria-label="Search"
//             />
//             <Button variant="outline-success">Search</Button>
//           </Form>
//         </Navbar.Collapse>
//       </Container>
//     </Navbar>
//    </div>
//   );
// }

// export default UserHome;

import Navbar from "./Navbar";
import UserDashBorder from "./UserDashboard";
import { Routes, Route } from "react-router-dom";
import AddInCart from "./AddInCart";
import AddInWishList from "./AddInWishList";
import UpdateUser from "./UpdateUser";
const UserHome=()=>{
  return(
    <div className="userhome">
      <Navbar/>
      <Routes>
        <Route path="/" element={<UserDashBorder/>}/>
        <Route path ="/addincart" element={<AddInCart/>}/>
        <Route path ="/updateuser" element={<UpdateUser/>}/>
        <Route path ="/addinwishlist" element={<AddInWishList/>}/>
      </Routes>
    </div>
  )
}
export default UserHome;