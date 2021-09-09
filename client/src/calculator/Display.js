import React from "react";
import './Display.css';

import "bulma/css/bulma.min.css";

export default class Display extends React.Component {

    render() {
        return (
            <div className="box display">
                <div>{this.props.result ? this.props.value + "=" : this.props.value}</div>
                {this.props.result ? <div>{this.props.result}</div> : ``}
            </div>
        );
    }
}