import React from "react";
import './Display.css';

export default class Display extends React.Component {

    render() {
        console.log(this.props)
        return (
            <div className="display">
                <div>{this.props.result ? this.props.value + "=" : this.props.value}</div>
                {this.props.result ? <div>{this.props.result}</div> : ``}
            </div>
        );
    }
}