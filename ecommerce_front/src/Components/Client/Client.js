import React,{Component} from 'react'
import {Switch,Route, BrowserRouter as Router } from 'react-router-dom';
import Articles from "../Admin/Articles"
import Cart from "./Cart"
import MyOrders from './MyOrders';
import OrderDetails from './OrderDetails';
import NavBar from './NavBar';
import Profile from '../Profile';
class Client extends Component{

    render(){

        return(
            <div>
                <NavBar/>
                <Router>
                <Switch>
                <Route exact path="/Client/Articles" render={(props)=><Articles {...props}/>}/>
                <Route exact path="/Client/Cart" render={(props)=><Cart {...props}/>}/>
                <Route exact path="/Client/MyOrders" render={(props)=><MyOrders {...props}/>}/>
                <Route exact path ="/Client/OrderDetails" render={(props)=><OrderDetails {...props}/>}/>
                <Route exact path ="/Client/Profile" render={(props)=><Profile {...props}/>}/>


                </Switch>
            </Router>
            </div>
        );
    }


}
export default Client