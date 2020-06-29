import React,{Component} from 'react'
import axios from'axios'
import Article from "./Article"
import { Col, FormControl } from 'react-bootstrap'

class Articles extends Component{
    state={
        articles:[],
        categories:[]
    }
    async componentDidMount(){
        await axios.get("http://localhost:8080/BasicProject/RestArticles/getArticles").then(res=> this.setState({articles:res.data}))
        await axios.get("http://localhost:8080/BasicProject/RestCategories/getCategories").then(res=> this.setState({categories:res.data}))
        var cs=document.getElementsByClassName("ClientSection")
        var user=JSON.parse(localStorage.getItem("user"))
        if(user.profile=="Admin"){
            for(var i=0;i<cs.length;i++){
                cs[i].style.display = "none"
            }
        }
        var as=document.getElementsByClassName("AdminSection")
        if(user.profile=="Client"){
            for(var i=0;i<cs.length;i++){
                as[i].style.display = "none"
            }
        }

        

        
    }
    async onSelectChange(e){
        
        e.preventDefault()
        var id=e.target.value
        if(id!=0){
        await this.setState({articles:[]})
        await axios.get("http://localhost:8080/BasicProject/RestArticles/articlesByCategory?id="+id).then(res=>this.setState({articles:res.data}))
        }
        else{
            await axios.get("http://localhost:8080/BasicProject/RestArticles/getArticles").then(res=> this.setState({articles:res.data}))

        }

    }

    render(){
        return(
            <div>
                <h1>Articles</h1>
                <br/>
                <FormControl as="select" onChange={(e)=>this.onSelectChange(e)}>
                    <option value={0}>Find Articles By Category </option>
                    {this.state.categories.map((cat)=>
                        <option value ={cat.id}>{cat.name}</option>
                    )}
                </FormControl>
                <br/>
                {this.state.articles.map((article)=>
                   
                    <Article article={article}/>
                   
                    
                )}
            </div>
        );
    }


}
export default Articles