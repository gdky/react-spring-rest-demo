/**
 * 法规代码下拉选择器
 */
import React from 'react'
import {Select} from 'antd'

const Option = Select.Option;

const selectorFG = React.createClass({
    render(){
        return <Select {...this.props} placeholder="选择法规类型" allowClear>
            <Option key="1">内部法规类</Option>
            <Option key="2">外部法规类</Option>
        </Select>
    }
});

module.exports = selectorFG;