import React ,{Component} from 'react'
import {Row, Col,Button } from 'react-bootstrap';
class NavBar extends Component {
    onLogoutClick(){
        localStorage.removeItem("user")
        localStorage.removeItem("cart")
        localStorage.removeItem("userid")
        window.location.replace("/")
    }
        render(){
            
            return(
                <Row>
                    <Col><Button variant="outline-primary" href='/Admin/Category' size="lg">Categories</Button></Col>
                    <Col><Button variant="outline-primary" href="/Admin/EditArticle" size="lg">Edit An Article</Button></Col>
                    <Col><Button variant="outline-primary" href="/Admin/Articles" size="lg">Articles</Button></Col>
                    <Col><Button variant="outline-primary" href="/Admin/Orders" size="lg">Orders</Button></Col>

                    <Col><Button variant="outline-primary" size="lg" href="/Client/Profile">Profile</Button></Col>
                    <Col><Button variant="outline-danger" size="lg" onClick={()=>this.onLogoutClick()}>LogOut</Button></Col>

                </Row>
            );
        }

}
export default NavBar