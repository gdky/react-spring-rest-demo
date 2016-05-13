import '../common/lib';
import React from 'react';
import ReactDOM from 'react-dom';
import {Router, Route, browserHistory, hashHistory, IndexRoute} from 'react-router'
import App from '../component/App';
import home from '../module/home'

/*路由配置*/
const routes = {
    path: '/',
    component: App,
    indexRoute: { component: home },
    ignoreScrollBehavior: true,
    breadcrumbName: '首页',
    childRoutes: [
        /* 模块预加载方式 */
        //{ path: '/gn1(/)', component: gn1 },


        /* 模块懒加载方式 */

        //财务报表
        require('../module/cwbb/lrb')


    ]
};

ReactDOM.render(<Router history={hashHistory} routes={routes}/>, document.getElementById('app'));



