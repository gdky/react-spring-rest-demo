import React from 'react'
import {Icon} from 'antd'
import './style.css'

const AppLogo = React.createClass({
    render(){
        return <div className="app-logo">
            <a href="#" className="logo-icon"><Icon type="solution" />&nbsp;注师管理中心</a>
            <span className="logo-small">cta.gd</span>
            </div>
    }
})

module.exports = AppLogo;