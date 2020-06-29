import React,{Component} from 'react'
import axios from 'axios'
import Article from "../Admin/Article"
import { Button,Row,Col ,Form, FormControl} from 'react-bootstrap'


class Cart extends Component{
        state={
            articles:[]
        }
        async componentDidMount(){
            var cart=JSON.parse(localStorage.getItem("cart"))
            var articles=cart.filter(await function(item) {
                 return Object.values(item)
            })

            await this.setState({articles:articles})
            for(var i=0; i<articles.length;i++){
                if(articles[i].quantity!=undefined){
                   document.getElementById("quantity"+articles[i].id).value=articles[i].quantity
                }
            }
        }
        async onQuantityChange(e,art){
            e.preventDefault()
            var articles=this.state.articles
            var articleIndex = articles.findIndex((article => article.id == art.id));
            articles[articleIndex].quantity=e.target.value
            await this.setState({articles:articles})
            await localStorage.setItem('cart',JSON.stringify(articles))


        }
        async onRemoveFromCartClick(e,art){
            e.preventDefault()
            var articles=this.state.articles
            var articleIndex = articles.findIndex((article => article.id == art.id));
            articles.splice(articleIndex,articleIndex+1)
            await this.setState({articles:articles})
            await localStorage.setItem('cart',JSON.stringify(articles))
            window.location.reload(false)


        }
        onValidateOrderClick(e){
            e.preventDefault()
            var date=new Date()
            var fulldate=date.getDate()+'/'+(date.getMonth()+1)+'/'+date.getFullYear()
            var commandLine=[]
            for(var i=0;i<this.state.articles.length;i++){
                var commandObject={
                    article_id:this.state.articles[i].id,
                    quantity:this.state.articles[i].quantity
                    
                }
                commandLine.push(commandObject)

                
            var data={
                commandLine:commandLine,
                client_id:localStorage.getItem("userid"),
                date:fulldate
            }
            
        }
        axios.post("http://localhost:8080/BasicProject/RestCommands/addCommand",data)
        var cart=[]
        localStorage.setItem("cart",JSON.stringify(cart))
        window.location.replace("/Client/Articles")
        }

    render(){
        return(
            <div>
                <Form>
                <h1> Your cart </h1>
                
            <div style={{padding:"3%"}}>
                
                {this.state.articles.map((article)=>

                

                    <Row>
                    <Col>
                        <Article article={article}/>
                    </Col>
                    <Col>
                        <h2>Quantity</h2>
                        <FormControl  id={"quantity"+article.id} type="number" defaultValue="1" onChange={(e)=>this.onQuantityChange(e,article)}/>
                        <br/>
                        <Button variant="danger" onClick={(e)=>this.onRemoveFromCartClick(e,article)}>Remove From Cart </Button>
                    </Col>
                </Row>

                
                    
                    
                    
                )}

            </div>
                <div style={{padding:"3%"}} >
                <Button onClick={(e)=>this.onValidateOrderClick(e)}>Validate Order</Button>
                </div>
                </Form>
            </div>
        );
    }
}

export default Cart