import React,{Component} from 'react'
import axios from 'axios'
import { Form, FormGroup, FormControl, Button } from 'react-bootstrap'


class Profile extends Component{
    state={
        user:{ }

    }
    async componentDidMount(){
        if(window.location.pathname==="/Register"){
            document.getElementById("editButton").innerHTML="Register"
            document.getElementById("title").innerHTML="Register"
            document.getElementById("passwordblock").style.display="block"
            document.getElementById("editPassword").style.display="none"
        }
        else{
            document.getElementById("title").innerHTML="Edit Your Profile "
            document.getElementById("password").removeAttribute("required");
            document.getElementById("password2").removeAttribute("required");
            var user=JSON.parse(localStorage.getItem("user"))
            if(user!=null){
                await this.setState({user:user})
                document.getElementById("first_name").value=user.first_name
                document.getElementById("last_name").value=user.last_name
                document.getElementById("email").value=user.email
                document.getElementById("postcode").value=user.postcode
                document.getElementById("address").value=user.address
                document.getElementById("city").value=user.city
                document.getElementById("tel").value=user.tel

            }
            await this.setState({user:user})
            console.log(this.state)
    }
    }
    async onTextChange(e){
        e.preventDefault()
        
       const user = { ...this.state.user, [e.target.name]: e.target.value }
       await this.setState(() => ({ user }))
       console.log(this.state.user)
    }
    onEditClick(e){
        
        e.preventDefault()
        var user=this.state.user
        //Add a User
        if(window.location.pathname==="/Register"){
            if(document.getElementById("password").value!==document.getElementById("password2").value){
                alert("UnIdentical Passwords")
                return false
            }
        
            axios.post("http://localhost:8080/BasicProject/RestUsers/setUser",user)
        }
        
        //Edit The User 
        else{
            user.id_user=this.state.user.id
            console.log(user)
            axios.post("http://localhost:8080/BasicProject/RestUsers/updateUser",user)
            }

    }
    onEditPassword(e){
        e.preventDefault()
        if(document.getElementById("_password").value!==document.getElementById("_password2").value){
            alert("UnIdentical Passwords")
            return false
        
        }

        else{
            var data={
                user_id:this.state.user.id,
                oldPassword:document.getElementById("_oldpassword").value,
                newPassword:document.getElementById("_password2").value
            }
            axios.post("http://localhost:8080/BasicProject/RestUsers/updatePassword",data).then((res)=>localStorage.setItem("user",JSON.stringify(res.data)))

        }
    }
    render(){

        return(
            <div>
                <Form style={{padding:"10%"}} onSubmit={(e)=>this.onEditClick(e)}>
                    <h1 id="title"></h1>
                    <FormGroup>
                        <FormControl type="text" name="first_name" id="first_name" placeholder="First Name" onChange={(e)=>this.onTextChange(e)} required/>
                    </FormGroup>
                    <FormGroup>
                        <FormControl type="text" name="last_name" id="last_name"  placeholder ="Last Name" onChange={(e)=>this.onTextChange(e)} required />
                    </FormGroup>
                    <span id="passwordblock"style={{display:"none"}}>
                    <FormGroup>
                        <FormControl type="password" name="password" id="password"  placeholder ="Password" onChange={(e)=>this.onTextChange(e)} required  />
                    </FormGroup>
                    <FormGroup>
                        <FormControl type="password" name="password2" id="password2"  placeholder ="Confirm Password"  required  />
                    </FormGroup>
                    </span>
                    <FormGroup>
                        <FormControl type="email" name="email" id="email"  placeholder="Email" onChange={(e)=>this.onTextChange(e)} required/>
                    </FormGroup>
                    <FormGroup>
                        <FormControl type="text" name="postcode" id="postcode"  placeholder="postcode" onChange={(e)=>this.onTextChange(e)} required/>
                    </FormGroup>
                    <FormGroup>
                        <FormControl type="text" name="address" id="address"  placeholder="Address" onChange={(e)=>this.onTextChange(e)} required/>
                    </FormGroup>
                    <FormGroup>
                        <FormControl type="text" name="city" id="city"  placeholder="City" onChange={(e)=>this.onTextChange(e)} required/>
                    </FormGroup>
                    <FormGroup>
                        <FormControl type="text" name="tel" id="tel" placeholder="Phone Number" onChange={(e)=>this.onTextChange(e)}/>
                    </FormGroup>
                    <Button type="submit" id="editButton" size="lg">
                        Edit Profile
                    </Button>
                </Form>
                <span id="editPassword" style={{display:"block"}}>
                    <Form  style={{padding:"10%"}} onSubmit={(e)=>this.onEditPassword(e)}>
                        <h2>Edit PassWord </h2>
                        
                        <FormGroup>
                            <FormControl type="password" name="_oldpassword" id="_oldpassword"  placeholder ="Old Password" onChange={(e)=>this.onTextChange(e)} required  />
                        </FormGroup>
                        <FormGroup>
                            <FormControl type="password" name="_password" id="_password"  placeholder ="New Password" onChange={(e)=>this.onTextChange(e)} required  />
                        </FormGroup>
                        <FormGroup>
                            <FormControl type="password" name="_password2" id="_password2"  placeholder ="Confirm New Password"  required  />
                        </FormGroup>
                        <Button type="submit" id="editButton" size="lg">
                                Edit Password
                        </Button>

                    </Form>
                </span>
            </div>
        );
    }


}

export default Profile