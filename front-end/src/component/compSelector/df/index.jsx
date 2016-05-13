/**
 * 省份下拉选择器 对应dm_df
 */
import React from 'react'
import {Select} from 'antd'
import data from './model.js'

const Option = Select.Option;

const selectorDF = React.createClass({
    render(){
        const options = data.map(item=><Option key={item.ID}>{item.MC}</Option>)
        return <Select {...this.props} placeholder="选择省份" allowClear showSearch optionFilterProp='children'>
            {options}
        </Select>
    }
});

module.exports = selectorDF;