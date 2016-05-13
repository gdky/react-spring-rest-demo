import React from 'react'
import {Calendar} from 'antd'

const gn2 = React.createClass({
    onPanelChange(value, mode) {
        console.log(value, mode);
    },
    render(){
        return <div className="wrap">
        <Calendar onPanelChange={this.onPanelChange} />
        </div>
    }
})

module.exports=gn2;