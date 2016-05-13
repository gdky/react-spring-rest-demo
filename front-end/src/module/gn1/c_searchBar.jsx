import  React from 'react';
import {Form, Input, Row, Col, Button} from 'antd';

const FormItem = Form.Item;

const C_searchBar = React.createClass({
    render() {
        return < Form horizontal className="advanced-search-form">
            <Row>
                <Col span="8">
                    <FormItem
                        label="搜索名称："
                        labelCol={{ span: 10 }}
                        wrapperCol={{ span: 14 }}>
                        <Input placeholder="请输入搜索名称"/>
                    </FormItem>
                    <FormItem
                        label="较长搜索名称："
                        labelCol={{ span: 10 }}
                        wrapperCol={{ span: 14 }}>
                        <Input placeholder="请输入搜索名称"/>
                    </FormItem>
                    <FormItem
                        label="搜索名称："
                        labelCol={{ span: 10 }}
                        wrapperCol={{ span: 14 }}>
                        <Input placeholder="请输入搜索名称"/>
                    </FormItem>
                </Col>
                <Col span="8">
                    <FormItem
                        label="搜索名称："
                        labelCol={{ span: 10 }}
                        wrapperCol={{ span: 14 }}>
                        <Input placeholder="请输入搜索名称"/>
                    </FormItem>
                    <FormItem
                        label="较长搜索名称："
                        labelCol={{ span: 10 }}
                        wrapperCol={{ span: 14 }}>
                        <Input placeholder="请输入搜索名称"/>
                    </FormItem>
                    <FormItem
                        label="搜索名称："
                        labelCol={{ span: 10 }}
                        wrapperCol={{ span: 14 }}>
                        <Input placeholder="请输入搜索名称"/>
                    </FormItem>
                </Col>
                <Col span="8">
                    <FormItem
                        label="搜索名称："
                        labelCol={{ span: 10 }}
                        wrapperCol={{ span: 14 }}>
                        <Input placeholder="请输入搜索名称"/>
                    </FormItem>
                    <FormItem
                        label="较长搜索名称："
                        labelCol={{ span: 10 }}
                        wrapperCol={{ span: 14 }}>
                        <Input placeholder="请输入搜索名称"/>
                    </FormItem>
                    <FormItem
                        label="搜索名称："
                        labelCol={{ span: 10 }}
                        wrapperCol={{ span: 14 }}>
                        <Input placeholder="请输入搜索名称"/>
                    </FormItem>
                </Col>
            </Row> < Row >
            <Col span="8" offset="16" style={{ textAlign: 'right' }}>
                <Button type="primary" htmlType="submit">搜索</Button>
                <Button type="ghost">清除条件</Button>
            </Col>
        </Row> </Form >;
    }
})

module.exports = C_searchBar;