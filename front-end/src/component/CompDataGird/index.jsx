import React from 'react'
import req from 'reqwest'
import {
  Table, Icon
}
from 'antd'

     
const CompDataGird = React.createClass({
  getDefaultProps() {//初始化某些传入值
    return {
      pageSetting: {page:false,pageSize : 10000,},
    };
  },

   getInitialState() {//初始化State状态，使用传入参数
        return {
            data: [],
            pagination: this.props.pageSetting,
            column:this.props.column,
            showtotal:this.props.pageSetting.showTotal,
            urls:this.props.dataProvider,
            style:this.props.girdStyle,
        };
    },

handleTableChange(pagination, filters, sorter) {//onChange方法，分页、排序、筛选变化时触发，必须三个参数才能准确获取
 req({
      url: '/api/zs/jgs?pagenum='+pagination.current+'&pagesize='+pagination.pageSize+'&sfield='+sorter.field+'&sorder='+sorter.order,//根据前端返回值更新后台查询方法和数据
      method: 'get',
      type: 'json',
      success: (result) => {
        const paper = this.state.pagination;     
         paper.pageSize = pagination.pageSize;//把页面Size更改为前端相应Size
        this.setState({
          data: result.data,
        });
      }
    });
  },

  fetch_jgcx() {
    req({
      url: this.state.urls+'?pagesize='+this.state.pagination.pageSize+'&pagenum=1&sfield=null&sorder=null',//默认数据查询后台返回JSON
      method: 'get',
      type: 'json',
      success: (result) => {
if (this.state.pagination.page){//判断是否进行分页
    const pagination = this.state.pagination;
        pagination.total = result.page.pageTotal;//要求后台返回json写法有属性page，该属性包含pageTotal（总条数值）
              if (this.state.showtotal){//判断是否显示总条数
                   function showTotal() {
                      return "共"+pagination.total+"条";
                    }
                    pagination.showTotal = showTotal;//调用总条数返回方法
                  }else{
                    pagination.showTotal = null;//不显示总条数处理
                  }
  }else{
    const pagination = this.state.pagination;
        pagination.total = result;//不分页总条数处理
  }
        // const pagination = this.state.pagination;
        // pagination.total = result.page.pageTotal;
        //  pagination.pageSize = 5;
        // pagination.showSizeChanger = true;
        // pagination.showQuickJumper = true;
        // pagination.size = 'small';
        // pagination.pageSizeOptions = ['5', '10', '20', '30', '40'];
        // console.log(pagination)
   
        this.setState({
          data: result.data,//要求后台返回json写法有属性data，该属性包含查询记录，每条查询记录必须拥有字段'key'
        });
      }
    });
  },
componentDidMount() {//REACT提供懒加载方法，懒加载时使用，且方法名必须为componentDidMount
    this.fetch_jgcx();//异步调用后台服务器方法fetch_jgcx
    // const dd = this.props.column;

  },

  render() {
    return <div className="dataGird">
           <Table columns={this.state.column} 
           dataSource={this.state.data} 
          pagination={this.state.pagination}
           onChange={this.handleTableChange} 
        bordered={this.state.style.bordered} size={this.state.style.size}  />
        </div>
  }
})

module.exports = CompDataGird;

// column={model} dataSource{model.datasource} <CompDataGird  />  dataProvider  