/**
 * 通过状态下拉选择器
 */
import React from 'react'
import {Select} from 'antd'
const Option = Select.Option;

const selectorMZ = React.createClass({
    render(){
        return <Select {...this.props} placeholder="选择状态"   >
            <Option key="0">保存</Option>
            <Option key="1">提交</Option>
            <Option key="2">通过</Option>
            <Option key="3">退回</Option>
        </Select>
    }
});

module.exports = selectorMZ;