import React from 'react';
import {Menu, Dropdown, Icon,Badge}from 'antd';
import './style.css'
const menu = <Menu>
    <Menu.Item key="0"><a href="/url/profilo">个人资料</a></Menu.Item>
    <Menu.Divider/>
    <Menu.Item key="1"><a href="/url/logut">退出帐号</a></Menu.Item>
</Menu>;

const ComUserPanel = React.createClass({
    getInitialState() {
        return {
            show: true
        };
    },
    handleClick() {
        this.setState({
            show: !this.state.show
        });
    },
    render() {
        var value = this.props;
        return (
            <div className="user-panel">
                <ul>
                    <li><Badge dot={this.state.show}><a href="#" onClick={this.handleClick}><Icon
                        type="mail"/></a></Badge></li>
                    <li><Dropdown overlay={menu} trigger={['click']}>
                        <a href="/"> 注册管理科 <Icon className="toggle-icon" type="down"/></a>
                    </Dropdown></li>
                </ul>
            </div >
        );
    }
})

module.exports = ComUserPanel;