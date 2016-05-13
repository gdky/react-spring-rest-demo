import React from 'react'
import { Tabs } from 'antd';
import ReactDOM from 'react-dom'
// import './style.css';

const TabPane = Tabs.TabPane;
const tabtest = [{ tab:"选项卡一", key:"1"},{ tab:"选项卡2", key:"2"},{ tab:"选项卡3", key:"3"}]

function callback(key) {
  console.log(key);
}

const CompTab = React.createClass({



  render() {
    return <div id="components-tabs-demo-card-top">
<div className="card-container">
    <Tabs type="card" >
    <TabPane tab="选项卡一" key="1">选项卡一内容</TabPane>
    <TabPane tab="选项卡二" key="2">选项卡二内容</TabPane>
   <TabPane tab="选项卡三" key="3">选项卡三内容</TabPane>
  </Tabs></div></div>
  }
})


module.exports = CompTab;

    // <TabPane tab="选项卡一" key="1">选项卡一内容</TabPane>
    // <TabPane tab="选项卡二" key="2">选项卡二内容</TabPane>
    // <TabPane tab="选项卡三" key="3">选项卡三内容</TabPane>