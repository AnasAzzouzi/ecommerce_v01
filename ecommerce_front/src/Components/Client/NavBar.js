import React ,{Component} from 'react'
import {Row, Col, Button } from 'react-bootstrap';
class NavBar extends Component {
        onLogoutClick(){
            localStorage.removeItem("user")
            localStorage.removeItem("cart")
            localStorage.removeItem("userid")
            window.location.replace("/")
        }
        render(){

            return(
                <Row style={{padding:"2%"}}>
                    <Col><Button variant="outline-primary" size="lg" href="/Client/Articles">Articles</Button></Col>
                    <Col> <Button variant="outline-primary" size="lg" href="/Client/Cart">Cart</Button></Col>
                    <Col><Button variant="outline-primary" size="lg" href="/Client/MyOrders">My Orders</Button></Col>
                    <Col><Button variant="outline-primary" size="lg" href="/Client/Profile">Profile</Button></Col>
                    <Col><Button variant="outline-danger" size="lg" onClick={()=>this.onLogoutClick()}>LogOut</Button></Col>
                </Row>
            );
        }

}
export default NavBar