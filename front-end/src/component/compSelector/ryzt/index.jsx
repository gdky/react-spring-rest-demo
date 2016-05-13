/**
 * 人员状态下拉选择器
 */
import React from 'react'
import {Select} from 'antd'
import data from './model.js'

const Option = Select.Option;

const selectorRYZT = React.createClass({
    render(){
        const options = data.map(item=><Option key={item.id}>{item.mc}</Option>)
        return <Select {...this.props} placeholder="选择人员状态" allowClear>
            {options}
        </Select>
    }
});

module.exports = selectorRYZT;