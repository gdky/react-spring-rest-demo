/**
 * 检查类别下拉选择器
 */
import React from 'react'
import {Select} from 'antd'

const Option = Select.Option;

const selectorJCLB = React.createClass({
    render(){
        return <Select {...this.props} placeholder="选择检查类别" allowClear>
            <Option key="1">政策执行</Option>
            <Option key="2">违纪违规</Option>
            <Option key="3">情况反映</Option>
            <Option key="4">举报检查</Option>
            <Option key="5">其他</Option>
        </Select>
    }
});

module.exports = selectorJCLB;