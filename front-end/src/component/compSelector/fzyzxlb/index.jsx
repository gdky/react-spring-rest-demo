/**
 * 非执业注销类别下拉选择器
 */
import React from 'react'
import {Select} from 'antd'

const Option = Select.Option;

const selectorFZYZXLB = React.createClass({
    render(){
        return <Select {...this.props} placeholder="非执业注销类别" allowClear>
            <Option key="1">违纪违法</Option>
            <Option key="2">年检</Option>
            <Option key="3">死亡</Option>
            <Option key="4">其它</Option>
        </Select>
    }
});

module.exports = selectorFZYZXLB;