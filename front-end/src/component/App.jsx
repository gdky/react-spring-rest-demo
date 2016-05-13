import './app.css'
import React from 'react';
import AppHeader from './AppHeader';
import AppSideNav from './AppSideNav';
import AppFooter from './AppFooter';
import {QueueAnim, Breadcrumb,Alert} from 'antd'
import req from 'reqwest'
import config from 'common/configuration'



const url  = config.HOST + config.URI_API_FRAMEWORK + '/asidemenu';
const errorAlert = <div className="sys-alert"><Alert
  message="数据读取错误：无法获取所需数据，应用服务工作情况可能不正常"
  type="error" /></div>

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            asideMenu : [],
            errorAlert:''
        };
    }

    componentDidMount(){
        req({
            url:url + '?l=A', //获取中心端模块菜单
            type:'json',
            method:'get'
        }).then(resp=>{
            this.setState({
                asideMenu:resp
            })
        }).fail(err=>{
            this.setState({
                errorAlert:errorAlert
            })
        })
    }

    render() {
        return <div className="app-main">
            {this.state.errorAlert}
            <AppHeader/>
            <AppSideNav data={this.state.asideMenu}/>
            <div className="app-breadcrumb"><Breadcrumb  {...this.props} /></div>

            <QueueAnim type={['bottom', 'top']} duration={450} className="app-content">
            {React.cloneElement(this.props.children, {
            key: this.props.location.pathname
            })}
            </QueueAnim>
            <AppFooter/>
        </div>
    }
}

module.exports = App;