import React,{Component} from 'react'
import axios from 'axios'
import { Row,Col } from 'react-bootstrap'
class MyOrders extends Component{
        state={
            commands:[]
        }
        async componentDidMount(){

            await axios.get("http://localhost:8080/BasicProject/RestCommands/CommandsByClient?id="+localStorage.getItem("userid"))
            .then((res)=>
                    this.setState({commands:res.data}))
        }
    render(){
        return(

            <div style={{padding:"7%"}}>
                    <h1>My Orders</h1>
                    <br/>
                    <Row>
                        <Col><b>Client id</b></Col>
                        <Col><b>Order Id </b></Col>
                        <Col><b>Order Date </b></Col>
                        <Col><b>Order details</b></Col>
                        
                    </Row>
                    <hr/>
                    { this.state.commands.map((cmd)=>
                    <span>
                        <Row>
                            <Col>{cmd.client_id}</Col>
                            <Col>{cmd.id}</Col>
                            <Col>{cmd.command_date}</Col>
                            <Col><a href={"/Client/OrderDetails?id="+cmd.id}>Order details</a></Col>
                            
                        </Row>
                        <hr/>
                    </span>
                    
                    )}

            </div>
        );
    }
}
export default MyOrders