/**
 * 主界面侧栏导航组件
 * props.data 菜单数据
 * @export LayoutSideNav
 */

import React from 'react';
import {Menu,Icon} from 'antd';
import {Link} from 'react-router';
import {getTreeData} from 'common/utils'
import './style.css';


//产生分类菜单

const SubMenu = Menu.SubMenu;

//使用es6语法定义组件
class AppSideNav extends React.Component {
    //在构造器中初始化state，不使用getInitialState()
    constructor(props) {
        super(props);
        this.state = {
            current: '1',
            openKeys: []
        }
    }

    //load:this.props.load , //'lazy','all'
    handleClick(e) {
        this.setState({
            current: e.key,
            openKeys: e.keyPath.slice(1)
        });
    }

    onToggle(info) {
        this.setState({
            openKeys: info.open ? info.keyPath : info.keyPath.slice(1)
        });
    }

    getMenu(data) {
        return data.map(function (item) {
            let title = <span><Icon type={item.icon}/>{item.name}</span>;
            if (item.children) {
                return <SubMenu key={item.id} title={title} children={this.getMenu(item.children)}/>;
            } else {
                return <Menu.Item key={item.id}><Link to={item.href||''}><span><Icon
                  type={item.icon}/>{item.name}</span></Link></Menu.Item>
            }
        }, this);
    }


    render() {
        const menuData = getTreeData(this.props.data);
         let asideMenu = this.getMenu(menuData);
        return (
          <aside className="app-sidenav">
              <Menu onClick={this.handleClick.bind(this)}
                    openKeys={this.state.openKeys}
                    onOpen={this.onToggle.bind(this)}
                    onClose={this.onToggle.bind(this)}
                    selectedKeys={[this.state.current]}
                    mode="inline">
                  {asideMenu}
              </Menu>
          </aside>
        );
    }
}

//兼容IE8的模块导出写法
module.exports = AppSideNav;