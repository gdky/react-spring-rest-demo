import React from 'react'
import req from 'reqwest'
import './style.css';
import { Row, Col } from 'antd';
import CompDataGird from 'component/CompDataGird';
import { Table } from 'antd'

const columns = [{ //设定列
    title: '部门名称', //设定该列名称
    dataIndex: 'BMMC', //设定该列对应后台字段名
    key: 'BMMC', //列key，必须设置，建议与字段名相同
}, {
    title: '基本职能',
    dataIndex: 'JBZN',
    key: 'JBZN',
}, {
    title: '人数',
    dataIndex: 'RS',
    key: 'RS',
}];


const girdStyle = {//表格样式
    bordered: true,//是否展示外边框和列边框
    size: 'small',//正常或迷你类型，default or small
}

const CompAutoForm = React.createClass({
    getDefaultProps() {//初始化某些传入值
        return {
            form: {},
        };
    },
    getInitialState() {//初始化State状态，使用传入参数
        return {
            data: [],
            nbjgsz: [],
        };
    },
    fetch_jgxx() {
        req({
            url: "api/zs/swsxx/53",//默认数据查询后台返回JSON
            method: 'get',
            type: 'json',
            success: (result) => {
                this.setState({
                    data: result.data,//要求后台返回json写法有属性data，该属性包含查询记录，每条查询记录必须拥有字段'key'
                    nbjgsz: result.data.nbjgsz,

                });
            }
        });
    },
    componentDidMount() {//REACT提供懒加载方法，懒加载时使用，且方法名必须为componentDidMount
        this.fetch_jgxx();//异步调用后台服务器方法fetch_jgcx
        console.log(this.state.nbjgsz)
    },

    render() {

        return (
            <div >
                <Row type="flex" className="rowFirst">
                    <Col className="colRight" span="3"><p>单位名称：</p></Col>
                    <Col className="colLeft" span="9"><p>{this.state.data.dwmc}</p></Col>
                    <Col className="colRight" span="3"><p >所在城市：</p></Col>
                    <Col className="colLeft" span="9"><p >{this.state.data.cs}</p></Col>
                </Row>
                <Row type="flex" className="rowSecnd">
                    <Col className="colRight" span="3"><p>单位名称：</p></Col>
                    <Col className="colLeft" span="9"><p>大信税务师亊务所（广州）有限公司东莞分公司</p></Col>
                    <Col className="colRight" span="3"><p >所在城市：</p></Col>
                    <Col className="colLeft" span="9"><p >1111</p></Col>
                </Row>
                <Row type="flex" className="rowFirst">
                    <Col className="colRight" span="3"><p>单位名称：</p></Col>
                    <Col className="colLeft" span="21"><p></p></Col>
                </Row>
                <div><Table columns={columns}
                            dataSource={this.state.nbjgsz}
                            bordered size="small"/>
                </div>
            </div>


        );
    }
});

module.exports = CompAutoForm;
