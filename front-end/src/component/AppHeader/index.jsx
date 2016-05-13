import React from 'react';
import {Icon} from 'antd';
import CompUserPanel from '../CompUserPanel';
import AppLogo from '../AppLogo'
import './style.css';
const AppHeader = React.createClass({
    render() {
        return (
            <header className="app-header box-shadow">
                <AppLogo/>
                <CompUserPanel />
            </header>
        );
    }
});
module.exports = AppHeader;