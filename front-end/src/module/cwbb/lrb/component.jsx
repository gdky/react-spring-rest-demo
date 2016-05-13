import React from 'react'
import {Table,Modal,Row,Col,Button,Icon,Alert} from 'antd'
import CompPageHead from 'component/CompPageHead'
import Panel from 'component/compPanel'
import {columns,entityModel} from './model'
import req from 'reqwest';
import SearchForm from './searchForm'
import config from 'common/configuration'
import BaseTable from 'component/compBaseTable'
import {entityFormat} from 'common/utils'
import DetailBox from './detailbox.jsx'


const API_URL = config.HOST + config.URI_API_PROJECT + '/cwbb/lrb';
const ToolBar = Panel.ToolBar;
const ButtonGroup = Button.Group;


const lrb = React.createClass({
    //初始化state
    getInitialState(){
        return {
            data: [],
            pagination: {
                current: 1,
                showSizeChanger: true,
                pageSize: 5,
                showQuickJumper: true,
                pageSizeOptions: ['5', '10', '20']

            },
            searchToggle: false,
            detailViewToggle: false,
            where: '',
            helper: false,
            entity: '',
            detailHide: true
        }
    },

    //改变页码
    handleChange(pagination, filters, sorter){
        const pager = this.state.pagination;
        pager.current = pagination.current;
        pager.pageSize = pagination.pageSize;
        this.setState({pagination: pager});

        this.fetchData({
            page: pager.current,
            pageSize: pager.pageSize,
            where: encodeURIComponent(JSON.stringify(this.state.where))
        })
    },

    //查询按钮
    handleSearchToggle(){
        this.setState({searchToggle: !this.state.searchToggle});
    },

    //刷新按钮
    handleRefresh(){
        const pager = this.state.pagination;
        pager.current = 1;
        this.setState({pagination: pager, where: ''});
        this.fetchData();
    },

    //帮助按钮
    handleHelper(){
        this.setState({helper: !this.state.helper})
    },
    //手动关闭帮助提示
    handleHelperClose(){
        this.setState({helper: false})
    },

    //提交条件查询
    handleSearchSubmit(value){
        const pager = this.state.pagination;
        pager.current = 1;
        const params = {
            page: 1,
            pageSize: pager.pageSize,
            where: encodeURIComponent(JSON.stringify(value))
        };
        this.setState({pagination: pager, where: value});
        this.fetchData(params);
        this.setState({searchToggle: false})
    },

    //点击某行
    handleRowClick(record){
        req({
            url: API_URL + '/' + record.id,
            type: 'json',
            method: 'get'
        }).then(resp=> {
            let entity = entityFormat(resp,entityModel);
            this.setState({entity: entity,detailHide:false});
        }).fail(err=> {
            Modal.error({
                title: '数据获取错误',
                content: (
                    <div>
                        <p>无法从服务器返回数据，需检查应用服务工作情况</p>
                        <p>Status: {err.status}</p>
                    </div>  )
            });
        })
    },
    //明细表关闭
    handleDetailClose(){
        this.setState({detailHide: true})
    },

    //通过API获取数据
    fetchData(params = {page: 1, pageSize: this.state.pagination.pageSize}){
        this.setState({loading: true});
        req({
            url: API_URL,
            type: 'json',
            method: 'get',
            data: params
        }).then(resp=> {
            const p = this.state.pagination;
            p.total = resp.total > 1000 ? 1000 : resp.total;
            p.showTotal = total => {
                return `共 ${resp.total} 条，显示前 ${total} 条`
            };
            this.setState({
                data: resp.data,
                pagination: p,
                loading: false
            })
        }).fail(err=> {
            this.setState({loading: false});
            Modal.error({
                title: '数据获取错误',
                content: (
                    <div>
                        <p>无法从服务器返回数据，需检查应用服务工作情况</p>
                        <p>Status: {err.status}</p>
                    </div>  )
            });
        })
    },

    componentDidMount(){
        this.fetchData();
    },

    render(){
        //定义工具栏内容
        let toolbar = <ToolBar>
            <Button onClick={this.handleSearchToggle}>
                <Icon type="search"/>查询
                { this.state.searchToggle ? <Icon className="toggle-tip" type="circle-o-up"/> :
                    <Icon className="toggle-tip" type="circle-o-down"/>}
            </Button>

            <ButtonGroup>
                <Button type="primary" onClick={this.handleHelper}><Icon type="question"/></Button>
                <Button type="primary" onClick={this.handleRefresh}><Icon type="reload"/></Button>
            </ButtonGroup>
        </ToolBar>;

        //定义提示内容
        let helper = [];
        helper.push(<p key="helper-0">点击查询结果查看利润表明细</p>);
        helper.push(<p key="helper-1">检索功能只显示前1000条记录</p>);

        return <div className="cwbb-lrb">
            <div className="wrap">
                {this.state.helper && <Alert message="利润表检索查询帮助"
                                             description={helper}
                                             type="info"
                                             closable
                                             onClose={this.handleHelperClose}/>}

                <Panel title="利润表" toolbar={toolbar}>
                    {this.state.searchToggle && <SearchForm
                        onSubmit={this.handleSearchSubmit}/>}
                    <div className="h-scroll-table">
                        <Table columns={columns}
                               dataSource={this.state.data}
                               pagination={this.state.pagination}
                               loading={this.state.loading}
                               onChange={this.handleChange}
                               onRowClick={this.handleRowClick}/>
                    </div>
                </Panel>
                {this.state.detailHide ? null : <Panel title="利润表明细"
                                                       onClose={this.handleDetailClose}
                                                       closable>
                    <DetailBox data={this.state.entity}/>
                </Panel>}
            </div>
        </div>
    }
});

module.exports = lrb;