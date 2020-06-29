import React,{Component} from 'react'
import { Form, FormGroup, FormControl, Row ,Col, Button} from 'react-bootstrap'
import axios from 'axios'


class EditArticle extends Component{
    state={
        categories:[],
        id_article:null,
        article_name:"",
        price:"",
        stock:null,
        photo:"",
        category_id:null
    }
    async componentDidMount(){
        await  axios.get("http://localhost:8080/BasicProject/RestCategories/getCategories").then(res=> this.setState({categories:res.data}))
        var article=JSON.parse(localStorage.getItem("article"))
        localStorage.removeItem("article")
        if(article!==null){
            await this.setState({
                                id_article:article.id,
                                article_name:article.name,
                                price:article.price,
                                stock:article.stock,
                                photo:article.photo,
                                category_id:article.category.id
            })
            document.getElementById('article_name').value=this.state.article_name
            document.getElementById('price').value=this.state.price
            document.getElementById('stock').value=this.state.stock
            document.getElementById('category').value=this.state.category_id
            document.getElementById('ArticleImage').src=this.state.photo
            document.getElementById("addButton").innerHTML="Edit Article"
        }
     }
      async getBase64(file) {
        var reader = new FileReader();
        reader.readAsDataURL(file);
        var image64="no"
         reader.onload = ()=> {
            image64=reader.result
            this.setState({photo:image64})
        };
        
        reader.onerror = function (error) {
          console.log('Error: ', error);
        };
     }
        onImageChange(e){
        e.preventDefault()
        this.getBase64(e.target.files[0])
        var reader = new FileReader();
        reader.onload = function (e) {
            document.getElementById('ArticleImage').src= e.target.result;
            };
        reader.readAsDataURL(e.target.files[0]);
         
        }
        onTextChange(e){
            e.preventDefault()
            this.setState({[e.target.name]:e.target.value})
        }
        onFormSubmit(e){
            
            e.preventDefault()

            var article={
                article_name:this.state.article_name,
                price:this.state.price,
                stock:this.state.stock,
                category_id:this.state.category_id,
                photo:this.state.photo
            }
            if(this.state.id_article!==null){
                article.id_article=this.state.id_article
                axios.post("http://localhost:8080/BasicProject/RestArticles/updateArticle",article).then(()=>window.location.replace("/Admin/Articles"))

            }
            else{
            axios.post("http://localhost:8080/BasicProject/RestArticles/addArticle",article).then(()=>window.location.replace("/Admin/Articles"))
            }
        }

render(){

    return(

        <div>
            <Form onSubmit={(e)=>this.onFormSubmit(e)}>
                <h2>Edit An Article </h2>
                <FormGroup>
                    <FormControl type="text" id="article_name" name="article_name" placeholder="Name" onChange={(e)=>this.onTextChange(e)}/>
                </FormGroup>
                <FormGroup>
                    <FormControl type="text" id="price" name="price" placeholder="Price" onChange={(e)=>this.onTextChange(e)} />
                </FormGroup>
                <FormGroup>
                    <FormControl type="number" id="stock" name="stock" placeholder="Available Quantity" onChange={(e)=>this.onTextChange(e)} />
                </FormGroup>
                <FormGroup>
                    <FormControl as="select" id="category" name="category_id" placeholder="Available Quantity" onChange={(e)=>this.onTextChange(e)} >
                    {this.state.categories.map((cat)=>
                    <option value={cat.id}>{cat.name}</option>
                    )}
                    </FormControl>
                </FormGroup>
                <Row>
                    <Col>
                        <FormGroup>
                        <FormControl type="file" id="photo" placeholder="Available Quantity" accept="image/*" onChange={(e)=>this.onImageChange(e)}/>
                        </FormGroup>
                    </Col>
                    <Col>
                         <img width="200px" height="200px" id="ArticleImage"/>
                    </Col>
                </Row><br/>
                <Button type="submit"  id="addButton" size="lg" block  variant="info" >Add</Button>
                
                
            </Form>
        </div>
    );
}

}
export default EditArticle