import React,{Component} from 'react'
import axios from 'axios'


class test extends Component{
    componentDidMount(){
        axios.get("http://localhost:8080/BasicProject/RestUsers/getUsers").then(res=> console.log(res.data))
    }
    render(){

        return(
            <div>
                hey
            </div>
        );
    }


}

export default test