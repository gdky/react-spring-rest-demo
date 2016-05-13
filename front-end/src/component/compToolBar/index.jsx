import React from 'react'
import {Button,Row,Col,Icon} from 'antd'
import './style.css'

const compToolBar = React.createClass({
    handleClick(){
        this.props.onClick()
    },
    render(){
        return <div className="tool-bar">
            <Row>
                <Col span="2" offset="14">
                    <Button type="ghost" onClick={this.handleClick}><Icon type="search"/>搜索</Button>
                </Col>
            </Row>

        </div>
    }
})

module.exports = compToolBar;