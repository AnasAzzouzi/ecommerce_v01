import React,{Component} from 'react'
import axios from 'axios'
import {Row ,Col, Button, Form, FormGroup} from 'react-bootstrap'

class Category extends Component{
    state={
        categories:[],
        id_category:0,
        category_name:"",
    }
    async componentDidMount(){
       await  axios.get("http://localhost:8080/BasicProject/RestCategories/getCategories").then(res=> this.setState({categories:res.data}))
       
    }
    onTextChange(e){
        e.preventDefault();
        this.setState({[e.target.name]:e.target.value})
    }
    onAddClick(){
        var category={
            id_category:this.state.id,
            category_name:this.state.name,
        }
        if(document.getElementById('addButton').value=="Edit Category"){
            axios.post("http://localhost:8080/BasicProject/RestCategories/updateCategory",category).then(()=>window.location.reload(false))
        }
       else{
        axios.post("http://localhost:8080/BasicProject/RestCategories/addCategory",category).then(()=>window.location.reload(false))
        }
    }
    async onEditClick(e,cat){
        e.preventDefault()
        document.getElementById('category_name').value=cat.name
        document.getElementById('addButton').value="Edit Category"
        await this.setState({id:cat.id})

    }
    async onDeleteClick(e,cat){
        e.preventDefault()
        await axios.delete("http://localhost:8080/BasicProject/RestCategories/deleteCategory?id="+cat.id).then(()=>window.location.reload(false))
    }
render(){
    
    return(
        <div style={{padding:"3%"}}>
            <Row>
                <Col>
                    <h2>Categories</h2>
                </Col>
            </Row>
            <Form style={{margin:"7%"}}>
                <FormGroup>
                <Form.Control type="text"id="category_name" name="category_name" onChange ={(e)=>this.onTextChange(e)} placeholder="Add A New Category"/>
                </FormGroup>
                <Button id="addButton"  size="lg" block  variant="info" onClick={()=>this.onAddClick()} >Add</Button>
            </Form>
            
                <Row>
                    <Col>
                    Id Category
                    </Col>
                    <Col>
                    Category Name 
                    </Col>
                    <Col>
                    Edit Category
                    </Col>
                    <Col>
                    Delete Category
                    </Col>
                </Row>
                <br></br>
                {this.state.categories.map((cat)=>
                <div>
                        <Row>
                            <Col>
                            {cat.id}
                            </Col>
                            <Col>
                            {cat.name}
                            </Col>
                            <Col>
                                <a href="#" onClick={(e)=>{this.onEditClick(e,cat)}}>Edit</a>
                            </Col>
                            <Col>
                                <a href="#" onClick={(e)=>{this.onDeleteClick(e,cat)}} >Delete</a>
                            </Col>
                        </Row>
                        <hr/>
                </div>
                
                
                
                )}
        </div>
    );
}
}
export default Category