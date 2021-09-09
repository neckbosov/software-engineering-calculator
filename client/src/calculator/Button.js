import React from "react";
import "./Button.css";
import "bulma/css/bulma.min.css";

export default class Button extends React.Component {
    handleClick = () => {
        this.props.clickHandler(this.props.name);
    };

    render() {
        const className = [
            "button",
            this.props.del ? "is-danger" : "",
            this.props.wide ? "wide" : "",
            this.props.operator ? "is-primary": "",
            this.props.count ? "is-link": ""
        ];

        return (
            <button className={className.join(" ").trim()} onClick={this.handleClick}>{this.props.name}</button>
        );
    }
}