import React,{Component} from 'react'
import axios from 'axios'
import { Form, FormGroup, FormControl, Button } from 'react-bootstrap'


class Login extends Component{
    state={
        email:"",
        password:""
    }
    async onTextChange(e){
        e.preventDefault()
        await this.setState({[e.target.name]:e.target.value})

    }
    onFormSubmit(e){
            e.preventDefault()
            var login={
                    email:this.state.email,
                    password:this.state.password
            }
            axios.post("http://localhost:8080/BasicProject/RestUsers/login",login).then((res)=>{
                    localStorage.setItem("userid",res.data.id)
                    localStorage.setItem("user",JSON.stringify(res.data))
                    if(res.data.profile=="Client"){
                        window.location.replace("/Client/Articles")
                    }
                    if(res.data.profile=="Admin"){
                        window.location.replace("/Admin/Articles")
                    }
                    var cart=[]

                    localStorage.setItem("cart",JSON.stringify(cart))
                }
            )

    }
    render(){
        return(
            <div style={{padding:"3%"}}>
                <Form onSubmit={(e)=>this.onFormSubmit(e)}>
                    <FormGroup>
                        <FormControl type="text" placeholder="Enter Your Email" name="email" id="email" onChange={(e)=>this.onTextChange(e)}/>
                        <FormControl type="password" placeholder="Enter Your Password" name="password" id="password" onChange={(e)=>this.onTextChange(e)} />
                        <Button type="submit">Login</Button>
                    </FormGroup>
                </Form>
            </div>
        );
    }
}
export default Login