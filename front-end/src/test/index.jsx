/*
引入依赖库
 */
import React from 'react'
import ReactDom from 'react-dom' //ES6  ->  ES5
import req from 'reqwest'

const API_URL = '/api';

/*
定义组件A
 */
const Comment = React.createClass({
    getDefaultProps() {
        return ({
            title: "title"
        })
    },
    render() {
        return <div className="comment">
            <h1>{this.props.title}</h1>
            <p>内容内容内容内容内容</p>
        </div>
    }
})

//组件PIC
const Pic = React.createClass({
    render() {
        return <div className="pic">
            <div>{this.props.src}</div>
            {this.props.children}
        </div >

    }
})

//最外层组件
const Wrap = React.createClass({

    getInitialState() {
        return { data: 1, json: [{ id: 0, content: "content" }] }
    },
    handleClick() {
        req({
            url: 'api/todos',
            method: 'get',
            type: 'json'
        }).then(resp => {
            this.setState({ json: resp });
        })
    },

    render() {
        let json = this.state.json[0]
        return <div className="wrap">
            <Pic src="localhost/img"> {json.content} </Pic>
            <Comment />
            <button onClick={this.handleClick}> 点击 </button>
        </div >
    }
})

ReactDom.render(<Wrap  />
    , document.getElementById('react-content'));




