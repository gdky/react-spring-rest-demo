/**
 * 人员类别下拉选择器
 */
import React from 'react'
import {Select} from 'antd'

const Option = Select.Option;

const selectorRYLB = React.createClass({
    render(){
        return <Select {...this.props} placeholder="选择人员类别" allowClear>
            <Option key="1">执业税务师</Option>
            <Option key="2">非执业税务师</Option>
            <Option key="3">从业人员</Option>
            <Option key="4">离职</Option>
            <Option key="5">注销</Option>
            <Option key="6">死亡</Option>
        </Select>
    }
});

module.exports = selectorRYLB;