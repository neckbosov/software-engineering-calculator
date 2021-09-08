import React from "react";
import './Display.css';

export default class Display extends React.Component {
    state = {
        currIndx: 0
    }

    onKeyUp = () => {
        const newIndx = this.state.currIndx + 1 < this.props.history.length ? this.state.currIndx + 1 : this.state.currIndx
        this.setState({
            currIndx: newIndx
        })
        this.props.onHistoryMove(newIndx)
    }

    render() {
        return (
            <div className="display" onKeyUp={this.onKeyUp}>
                <div>{this.props.history[this.state.currIndx]}</div>
                <div>{this.props.value}</div>
            </div>
        );
    }
}