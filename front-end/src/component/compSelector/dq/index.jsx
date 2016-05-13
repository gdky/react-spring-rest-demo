/**
 * 地区下拉选择器 对应dm_cs
 */
import React from 'react'
import {Cascader} from 'antd'
import csList from './model.js'

function getTreeFromList(arr, rootValue) {
    let key = 0;
    var root = {};
    if (arr.length > 0) {
        var objMap = {};
        root.items = [];
        arr.forEach(function (item) {
            var node = {
                value: item.ID,
                pid: item.PARENT_ID,
                label: item.MC,
                glzxmc: item.GLZXMC,
                key: key++
            };
            objMap[item.ID] = node;
            if (node.pid === rootValue) {
                root.items.push(node);
            } else {
                var parent = objMap[node.pid];
                if (parent["children"]) {
                    parent["children"].push(node);
                } else {
                    parent["children"] = [];
                    parent["children"].push(node);
                }
            }
        });
    } else {
        root.items = [];
    }
    return root.items;
}

const selectorDQ = React.createClass({

    getDefaultProps(){
        return {
            onChange: {}
        }
    },

    render(){
        const data = getTreeFromList(csList,null);
        return <Cascader {...this.props} options={data} expandTrigger="hover" changeOnSelect />
    }
});

module.exports = selectorDQ;