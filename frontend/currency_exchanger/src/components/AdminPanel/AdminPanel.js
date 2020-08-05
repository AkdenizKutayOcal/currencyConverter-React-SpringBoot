import React, { Component } from 'react'
import Navi from "../Navi/Navi";
import CurrencyForm from "../CurrencyForm/CurrencyForm";

export default class AdminPanel extends Component {
    render() {
        return (
            <div>
                <Navi/>
                <CurrencyForm/>
            </div>
        )
    }
}
