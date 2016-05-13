import React from 'react'
import {Row,Col,Button,Icon} from 'antd'
import ToolBar from './toolBar'
import ReactDOM from 'react-dom'

const panel = React.createClass({
    getDefaultProps(){
        return ({
            closable: false,
            title: '',
            toolbar: '',
            onClose() {},
        })
    },
    getInitialState(){
        return ({
            closed: false
        })
    },
    handleClose(e){
        e.preventDefault();
        this.setState({
            closed: true
        });
        this.props.onClose.call(this, e);
    },

    render(){
        let {title,toolbar,closable} = this.props;
        let pt = null;
        if (title || toolbar || closable) {
            pt = <div className="panel-title">
                <Row>
                    <Col span="8"><h3>{title}</h3></Col>
                    <Col offspan="16">
                        {closable ? <a onClick={this.handleClose} className="close">
                            <Icon type="cross"/></a> : null}
                        {toolbar}
                    </Col>
                </Row>
            </div>;
        }


        return this.state.closed ? null : (<div className="panel">
            {pt}
            <div className="panel-body">
                {this.props.children}
            </div>
        </div>)
    }
});

module.exports = panel;
