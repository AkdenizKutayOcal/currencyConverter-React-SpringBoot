import React, { Component } from 'react';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import Home from "./components/Home/Home"
import AdminPanel from "./components/AdminPanel/AdminPanel"

class App extends Component {
    render() {
        return (
            
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home} />
                    <Route path='/admin' exact={true} component={AdminPanel} />
                </Switch>
            </Router>
        );
    }
}

export default App;