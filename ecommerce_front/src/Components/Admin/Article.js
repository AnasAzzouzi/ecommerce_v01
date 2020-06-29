import React,{Component} from 'react'
import {Card,Button, Row,Col, FormControl} from 'react-bootstrap'

import axios from 'axios'

class Article extends Component {
    state={
        article:{},
        category:{}
    }
    async componentDidMount(){
        await this.setState({article:this.props.article})
        await this.setState({category:this.state.article.category})
        if(window.location.pathname==="/Client/Articles"){
            
            document.getElementById("ClientSection"+this.state.article.id).style.display="block"

        }
        if(window.location.pathname==="/Admin/Articles" ||window.location.pathname==="/Admin" ){
            
            document.getElementById("AdminSection"+this.state.article.id).style.display="block"

        }
        
    }
    onEditClick(){
        localStorage.setItem("article",JSON.stringify(this.state.article))
        window.location.replace("/Admin/EditArticle")
        }
    onAddToCart(e,article){
        e.preventDefault()
        var cart=[]
        cart=JSON.parse(localStorage.getItem("cart"))
        cart = cart.filter(function(item) {
            if (item!=="1" && item!=="2")
            return item 
        })
        article.quantity=1
        cart.push(article)
        console.log(cart)
        localStorage.setItem("cart",JSON.stringify(cart))
        alert("Added to Cart")
        


    }
 render(){
        return(
            
            
                
                <Card style={{ width: '18rem',display:'inline-block' }}>
                    <Card.Img variant="top"  width="300px" height ="300px" src={this.state.article.photo} />
                    <Card.Body>
                    <Card.Title>Name : {this.state.article.name}</Card.Title>
                    <Card.Text>Category : {this.state.category.name}</Card.Text>
                    <Card.Text>Price : {this.state.article.price} MAD</Card.Text>
                    <Card.Text>Available Quantity : {this.state.article.stock}</Card.Text>
                    <div id={"AdminSection"+this.state.article.id} style={{display:"none"}}>
                        <Row>
                            <Col>
                                <Button variant="info" size="md" onClick={()=>this.onEditClick()}>Edit&nbsp;</Button>
                            </Col>
                        </Row>
                        <br/>
                        <Row>
                            <Col>
                                <Button variant="danger" size="md" onClick={()=>{}}>Remove</Button>
                            </Col>
                        </Row>
                        <br/>
                    </div>
                    <div id={"ClientSection"+this.state.article.id} style={{display:"none"}}>
                        <Row>
                            <Col>
                                <Button variant="primary" size="md" onClick={(e)=>this.onAddToCart(e,this.state.article)}>Add To Cart </Button>
                            </Col>
                        </Row>

                    </div>
                   
                    

                </Card.Body>
                </Card>

        );
 }

}
export default Article