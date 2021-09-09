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
        inputValue: null,
        history: [],
        currIndx: -1
    };

    fetchHistory = () => {
        axios.get('http://localhost:8080/history', {validateStatus: false})
            .then(resp => {
                this.setState({
                    history: resp.data.history
                })
            }).catch((e) => console.log(e.message))
    }

    onHistoryMove = exprIndx => {
        if (exprIndx === -1) {
            this.setState({
                result: null,
            })
        } else {
            this.setState({
                result: this.state.history[exprIndx].result,
                value: this.state.history[exprIndx].stringExpression
            })
        }
    }

    handleClick = buttonName => {
        if (buttonName === '=') {
            axios.post('http://localhost:8080/calculate', {
                expression: this.state.inputValue === null ? 0 : this.state.inputValue
            }, {validateStatus: false})
                .then(resp => {
                    if (resp.status === 200) {
                        this.setState({
                            result: resp.data.result,
                            value: this.state.inputValue,
                            inputValue: null,
                            currIndx: 0
                        })
                    } else {
                        console.log("here")
                        this.setState({
                            result: resp.data.message,
                            value: null,
                            inputValue: null,
                            currIndx: -1
                        })
                    }
                }).catch(e => {
                console.log(e)
            })
            this.fetchHistory()
        } else if (buttonName === "DEL") {
            this.setState({
                result: null,
                value: null,
                inputValue: null,
                currIndx: -1
            })
        } else {
            if (this.state.currIndx !== -1) {
                this.setState({
                    result: null,
                    inputValue: this.state.result + buttonName,
                    currIndx: -1
                })
            } else {
                this.setState({
                    result: null,
                    inputValue: this.state.inputValue === null ? buttonName : this.state.inputValue + buttonName
                })
            }
        }
    };

    onKeyDown = (e) => {
        console.log("down", e.keyCode === 38)
        let d;
        if (e.keyCode === 38) {
            d = 1;
        } else if (e.keyCode === 40) {
            d = -1;
        } else {
            return;
        }
        const newIndx =
            ((this.state.currIndx + d >= -1) && (this.state.currIndx + d < this.state.history.length)) ? this.state.currIndx + d : this.state.currIndx
        console.log(newIndx, this.state.history.length);
        this.setState({
            currIndx: newIndx
        })
        console.log("lek")
        this.onHistoryMove(newIndx);
    }

    render() {
        window.addEventListener('keydown', this.onKeyDown, {capture: true})

        const getValue = () => {
            if (this.state.currIndx !== -1) {
                return this.state.history[this.state.currIndx].stringExpression
            } else {
                return this.state.inputValue === null ? '0' : this.state.inputValue;
            }
        }

        const getResult = () => {
            if (this.state.currIndx !== -1) {
                console.log("heres")
                return this.state.history[this.state.currIndx].result
            } else {
                return this.state.result;
            }
        }

        return (
            <div className="calculator">
                <Display value={getValue()} result={getResult()}/>
                <ButtonPanel clickHandler={this.handleClick}/>
            </div>
        );
    }
}