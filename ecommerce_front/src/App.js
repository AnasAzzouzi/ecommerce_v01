import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import {Switch,Route, BrowserRouter as Router } from 'react-router-dom';
import test from './Components/test';
import Admin from './Components/Admin/Admin';
import 'bootstrap/dist/css/bootstrap.min.css';
import Client from './Components/Client/Client';
import Login from './Components/Login';
import Profile from './Components/Profile';
class App extends Component {
  render(){
return(
        <div className="App">
          <Router>

            <Switch>
              <Route path='/Register' render={(props)=><Profile {...props}/>}/>
              <Route path='/Admin' render ={(props)=><Admin {...props}/>}/>
              <Route path='/Client' render ={(props)=><Client {...props}/>}/>
              <Route path="" render={(props)=><Login {...props}/>}/>
              
            </Switch>
          </Router>
        </div>
      );

  }
  
}

export default App;
