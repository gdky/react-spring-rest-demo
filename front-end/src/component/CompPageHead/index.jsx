/**
 * 子模块标题栏组件
 * @props.heading 标题名称
 * @export CompPageHead
 */
import React from 'react'
import './style.css'

const CompPageHead = React.createClass({
    render(){
        return <div className="page-heading">
            <h2>{this.props.heading}</h2>
        </div>
    }
})

module.exports = CompPageHead;