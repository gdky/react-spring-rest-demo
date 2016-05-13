/**
 * 单实体数据表格展示组件
 * @props.data {Object} 单个实体数据
 * @props.model {Object} 描述实体的属性定义，以及每行显示的属性组数
 * @props.bordered {boolean} 是否有边框
 * @props.striped ｛boolean｝ 是否有间隔背景条纹
 * 参考用法: const demo = <BaseTable data={data} model={model} bordered striped/>
 */
import React from 'react'
import ReactDom from 'react-dom'
import './style.css'


/*数据样式
const data = {
    name: '胡延兵',
    age: '23',
    sex: '男',
    xl: '本科',
    cs: '广州',
    dz: '中山三路',
    byyx: '北京大学',
    zgzh: '34454394',
    barq: '2016-01-01'

}
const model = {
    colGroupNum: 2,
    props: [{
        id: 'name',
        name: '姓名'
    }, {
        id: 'age',
        name: '年龄'
    }, {
        id: 'sex',
        name: '性别'
    }, {
        id: 'xl',
        name: '学历'
    }, {
        id: 'cs',
        name: '城市'
    }, {
        id: 'dz',
        name: '地址'
    }, {
        id: 'byyx',
        name: '毕业院校'
    }, {
        id: 'zgzh',
        name: '证书编号',
        groupspan: 2

    }, {
        id: 'barq',
        name: '备案日期'
    }]
}*/

const TrWrapper = React.createClass({
    render(){
        return <tr>{this.props.children}</tr>
    }
})

const baseTable = React.createClass({
    render(){
        let colCount = 0;
        const colgroup = [];
        const tr = [];
        let td = [];
        const colGroupNum = this.props.model.colGroupNum < 5 ? this.props.model.colGroupNum : 2
        //设置colgroup样式
        for (let i = 0; i < colGroupNum; i++) {
            let spanKey = 24 / (colGroupNum * 3);
            let spanValue = 24 * 2 / (colGroupNum * 3);
            if (colGroupNum == 3) {
                spanKey = 3;
                spanValue = 5;
            }
            colgroup.push(<col key={'c-k-'+i} className={'col-'+spanKey}></col>);
            colgroup.push(<col key={'c-v-'+i} className={'col-'+spanValue}></col>);
        }
        //将实体内容以key:value放置到对应的td组中，再按照colGroupNum分列
        for (let i = 0; i < this.props.model.props.length; i++) {
            let prop = this.props.model.props[i];
            //处理跨列项目
            if (prop.groupspan) {
                if (colGroupNum < colCount + prop.groupspan) {
                    tr.push(<TrWrapper key={'tr-'+tr.length+1}>{td}</TrWrapper>);
                    td = [];
                    colCount = 0;
                }
                td.push(<td key={'td-k-'+prop.id} className="prop-name">{prop.name}</td>);
                td.push(<td key={'td-v-'+prop.id} colSpan={prop.groupspan*2-1}>{this.props.data[prop.id]}</td>);
                colCount += prop.groupspan
                //处理非跨列项目
            } else {
                td.push(<td key={'td-k-'+prop.id} className="prop-name">{prop.name}</td>);
                td.push(<td key={'td-v-'+prop.id}>{this.props.data[prop.id]}</td>);
                colCount += 1;
            }
            if (colCount == colGroupNum) {
                tr.push(<TrWrapper key={'tr-'+tr.length+1}>{td}</TrWrapper>);
                td = [];
                colCount = 0;
            } else if (i == this.props.model.props.length - 1) {
                tr.push(<TrWrapper key={'tr-'+tr.length+1}>{td}</TrWrapper>);
            }
        }
        return <div className={'base-table '+
             (this.props.bordered?'table-bordered ':' ')+
             (this.props.striped?'table-striped ':' ')}>
            <table>
                <colgroup>
                     {colgroup}
                </colgroup>
                <tbody>
                {tr}
                </tbody>
            </table>
        </div>
    }
})
module.exports = baseTable;