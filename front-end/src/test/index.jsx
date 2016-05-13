/*
引入依赖库
 */
import 'common/lib.js'
import React from 'react'
import {Col,Row,Button,Affix,Checkbox} from 'antd'
import ReactDom from 'react-dom'
import BaseTable from 'component/compBaseTable'
import req from 'reqwest'
import './style.css'

/*
定义组件A
 */
const CompA = React.createClass({
    onClick(){
        this.props.onClick;// ==>compWrap.handleClick
    },
    render(){
        return <div>
            <h1> 组件1 标题</h1>
            <p>内容内容内容内容内容</p>
            <p> <Button type="primary" onClick={this.onClick}>主按钮</Button></p>
        </div>
    }
})

/*
 * 组件B
 */

const CompB = React.createClass({
    handleProp(data){
        let map =''
        if(data){
             map = data.map(item=>{
                return <p>{item.name}</p>
            })
        }
        return map
    },
    render(){
        const p = this.handleProp(this.props.content)
        return <div>
            <h2> 组件2标题</h2>
            {p}
        </div>
    }
})


/*
组件compWrap
 */
const CompWrap = React.createClass({
    //==============初始化事件==================
    getInitialState(){
        return {
            data: ''
        }
    },

    //**********事件处理****************
    handleClick(){

    },

    //==========生命周期事件=====================

    componentDidMount(){
        req({
            url:'api/fw/asidemenu',
            type:'json',
            method:'get'
        }).then(resp=>{
            console.log(resp)
            this.setState({
                data:resp
            })
        }).fail(err=>{
            console.log(err);
        })
    },

    // =========样式渲染==================
    render(){
        return <div>
            <CompA onClick={this.handleClick} />
            <CompB content={this.state.data}  />
        </div>
    }
})

ReactDom.render(<detailBox data={"this.state.data"} />
     , document.getElementById('react-content'));

