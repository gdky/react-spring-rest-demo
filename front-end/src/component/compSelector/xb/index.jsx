/**
 * 性别下拉选择器
 */
import React from 'react'
import {Select} from 'antd'
const Option = Select.Option;

const selectorMZ = React.createClass({
    render(){
        return <Select {...this.props} placeholder="选择性别"   >
            <Option key="1">男</Option>
            <Option key="2">女</Option>
        </Select>
    }
});

module.exports = selectorMZ;