import React from "react";
import axios from 'axios';
import Display from "./Display";
import ButtonPanel from "./ButtonPanel";
import './Calculator.css';

export default class Calculator extends React.Component {

    constructor(props) {
        super(props);
        this.fetchHistory();
    }

    state = {
        result: null,
        value: null,
        history: []
    };

    fetchHistory = () => {
        axios.get('http://localhost:8080/history')
            .then(resp => {
                console.log(resp.data);
                this.setState({
                    history: JSON.parse(resp.data.history)
                })
            }).catch((e) => console.log(e.message))
    }
    
    onHistoryMove = exprIndx => {
        this.setState({
            result: null,
            value: this.state.history[exprIndx]
        })
    }

    handleClick = buttonName => {
        if (buttonName === '=') {
            axios.post('http://localhost:8080/calculate', {
                expression: this.state.value === null ? 0 : this.state.value
            })
                .then(resp => {
                    if (resp.status === 200) {
                        console.log("here")
                        this.setState({
                            result: resp.data.result,
                            value: null
                        })
                    } else {
                        this.setState({
                            result: resp.data.message,
                            value: null
                        })
                    }
                }).catch(e => {
                console.log(e)
            })
        } else {
            this.setState({
                result: null,
                value: this.state.value === null ? buttonName : this.state.value + buttonName
            })
        }
    };

    render() {
        return (
            <div className="calculator">
                <Display value={this.state.result === null ? (
                    this.state.value === null ? '0' : this.state.value
                ) : this.state.result} history={this.state.history} onHistoryMove={this.onHistoryMove}/>
                <ButtonPanel clickHandler={this.handleClick}/>
            </div>
        );
    }
}