import { Table} from 'antd';
import React from 'react';
import C_searchBar from './c_searchBar';
import ReactDOM from 'react-dom';
import CompPageHead from '../../component/CompPageHead'
const columns = [{
    title: '姓名',
    dataIndex: 'name',
    filters: [{
        text: '姓李的',
        value: '李',
    }, {
        text: '姓胡的',
        value: '胡',

        
    }, {
        text: '子菜单',
        value: '子菜单',
        children: [{
            text: '姓陈的',
            value: '陈',
        }, {
            text: '姓王的',
            value: '王',
        }]
    }],
    // 指定确定筛选的条件函数
    // 这里是名字中第一个字是 value
    onFilter: function (value, record) {
        return record.name.indexOf(value) === 0;
    },
    sorter: function (a, b) {
        return a.name.length - b.name.length;
    }
}, {
    title: '年龄',
    dataIndex: 'age',
    sorter: function (a, b) {
        return a.age - b.age;
    }
}, {
    title: '地址',
    dataIndex: 'address',
    filters: [{
        text: '南湖',
        value: '南湖'
    }, {
        text: '西湖',
        value: '西湖'
    }],
    filterMultiple: false,
    onFilter: function (value, record) {
        return record.address.indexOf(value) === 0;
    },
    sorter: function (a, b) {
        return a.address.length - b.address.length;
    }
}];

let data = [];
for (var i = 0; i < 50; i++) {
    data.push({
        key: i,
        name: '姓名' + i,
        age: 'age' + i,
        address: '南湖区湖底公园' + i + '号'
    });
}
let pagination = {
    total: 1000,
    showSizeChanger: true,
    showQuickJumper: true,
    onShowSizeChange: function (current, pageSize) {
        console.log('Current: ', current, '; PageSize: ', pageSize);
    },
    onChange: function (current, pageSize) {
        console.log({Current: current, pageSize: pageSize});
    }
};
const gn1 = React.createClass({
    render() {
        return <div>
            <CompPageHead heading={'功能一'}/>
            <div className="wrap">
                <C_searchBar />
                <Table columns={columns} dataSource={data} pagination={pagination}/>
            </div>
        </div>;
    }
})
module.exports = gn1;