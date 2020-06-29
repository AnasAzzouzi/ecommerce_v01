import React,{Component} from 'react'
import {Switch,Route, BrowserRouter as Router } from 'react-router-dom';

import axios from 'axios'
import Category from './Category';
import EditArticle from './EditArticle';
import Articles from './Articles';
import Orders from './Orders'
import OrderDetails from '../Client/OrderDetails'
import NavBar from './NavBar';



class Admin extends Component{

render(){

    return(
        <div style={{padding:"5%"}}>
            <NavBar/>
            <Router>
                <Switch>
                <Route exact path="/Admin" render={(props)=><Articles {...props}/>}/>
                <Route exact path='/Admin/Category' render ={(props)=><Category {...props}/>}/>  
                <Route exact path="/Admin/EditArticle" render={(props)=><EditArticle {...props}/>}/>
                <Route exact path="/Admin/Articles" render={(props)=><Articles {...props}/>}/>
                <Route exact path="/Admin/Orders" render={(props)=><Orders {...props}/>}/>
                <Route exact path ="/Admin/OrderDetails" render={(props)=><OrderDetails {...props}/>}/>



                </Switch>
            </Router>
        </div>
    );
}
}
export default Admin