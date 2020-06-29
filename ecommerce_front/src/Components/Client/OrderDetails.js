import React,{Component} from 'react'
import axios from 'axios'
import Article from '../Admin/Article'
class OrderDetails extends Component{

            state={
                commandLines:[],
                order_id:0
            }
            async componentDidMount(){
                var urlParams = new URLSearchParams(window.location.search)
                await this.setState({order_id:urlParams.get("id")})
                await axios.get("http://localhost:8080/BasicProject/RestCommands/CommandLineByCommand?id="+this.state.order_id).then((res)=>this.setState({commandLines:res.data}))

            }

    render(){
        return(
            <div>
                {
                    this.state.commandLines.map(cmdl=>
                        <div>
                        <Article article={cmdl.article}/> 
                        <h2>Quantity : {cmdl.quantity}</h2>   
                        </div>
                        
                        )
                }
            </div>
        );
    }
}
export default OrderDetails
