/**
 * 行业下拉选择器
 */
import React from 'react'
import {Select} from 'antd'
import data from './model.js'

const Option = Select.Option;

const selectorHY = React.createClass({
    render(){
        const options = data.map(item=><Option key={item.id}>{item.mc}</Option>)
        return <Select {...this.props} placeholder="选择行业门类" allowClear>
            {options}
        </Select>
    }
});

module.exports = selectorHY;