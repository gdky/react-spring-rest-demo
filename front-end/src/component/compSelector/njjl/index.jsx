/**
 * 年检结论下拉选择器
 */
import React from 'react'
import {Select} from 'antd'

const Option = Select.Option;

const selectorNJJL = React.createClass({
    render(){
        return <Select {...this.props} placeholder="选择检查类别" allowClear>
            <Option key="1">通过</Option>
            <Option key="2">不通过</Option>
        </Select>
    }
});

module.exports = selectorNJJL;