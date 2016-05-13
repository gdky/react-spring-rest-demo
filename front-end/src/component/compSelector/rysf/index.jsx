/**
 * 人员身份下拉选择器
 */
import React from 'react'
import {Select} from 'antd'

const Option = Select.Option;

const selectorMZ = React.createClass({
    render(){
        return <Select {...this.props} placeholder="选择人员身份"   >
            <Option key="1">执业税务师</Option>
            <Option key="2">非执业税务师</Option>
            <Option key="3">从业人员</Option>
        </Select>
    }
});

module.exports = selectorMZ;