import React from 'react'


const toolBar = React.createClass({
    handleBodyToggle(){

    },
    render(){
        let ul = '';
        let li = React.Children.map(this.props.children, child=> {
            return <li>{child}</li>;
        });
        if (li) {
            ul = <ul className="clearfix">{li}</ul>
        }
        return <div className="panel-toolbar">
            {ul}
        </div>
    }
});

module.exports = toolBar;
