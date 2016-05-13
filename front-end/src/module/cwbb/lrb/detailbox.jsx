import React from 'react'
import './style.css'

const detailBox = React.createClass({

    render(){
        const obj = this.props.data;

        return <div className="fix-table table-bordered table-striped">
            <table>
                <tbody>
                <tr>
                    <td colSpan="3">{obj.DWMC}</td>
                    <td>{obj.JSSJ}</td>
                    <td>单位:元</td>
                </tr>
                <tr style={{textAlign:'center'}}>
                    <th colSpan="2">项目</th>
                    <th>行次</th>
                    <th>本月数</th>
                    <th>本年累计数</th>
                </tr>
                <tr>
                    <td colSpan="2">一、主营业务收入</td>
                    <td>1</td>
                    <td>{obj.ZGYWSR1}</td>
                    <td>{obj.ZGYWSR}</td>
                </tr>
                <tr>
                    <td style={{paddingLeft:'3em'}} colSpan="2">减：主营业务成本</td>
                    <td>2</td>
                    <td>{obj.ZGYWCB1}</td>
                    <td>{obj.ZGYWCB}</td>
                </tr>
                <tr>
                    <td style={{paddingLeft:'5em'}} colSpan="2">主营业务税金及附加</td>
                    <td>3</td>
                    <td>{obj.ZGYWSJ1}</td>
                    <td>{obj.ZGYWSJ}</td>
                </tr>
                <tr>
                    <td colSpan="2">二、主营业务利润（亏损以“—”号填列）</td>
                    <td>4</td>
                    <td>{obj.ZGWYLR1}</td>
                    <td>{obj.ZGWYLR}</td>
                </tr>
                <tr>
                    <td style={{paddingLeft:'1em'}} colSpan="2">加：其它业务利润（亏损以“—”号填列）</td>
                    <td>5</td>
                    <td>{obj.QTYWLR1}</td>
                    <td>{obj.QTYWLR}</td>
                </tr>
                <tr>
                    <td style={{paddingLeft:'3em'}} colSpan="2">减：营业费用</td>
                    <td>6</td>
                    <td>{obj.YYFY1}</td>
                    <td>{obj.YYFY}</td>
                </tr>
                <tr>
                    <td style={{paddingLeft:'5em'}} colSpan="2">管理费用</td>
                    <td>7</td>
                    <td>{obj.GLFY1}</td>
                    <td>{obj.GLFY}</td>
                </tr>
                <tr>
                    <td style={{paddingLeft:'5em'}} colSpan="2">财务费用</td>
                    <td>8</td>
                    <td>{obj.CWFY1}</td>
                    <td>{obj.CWFY}</td>
                </tr>
                <tr>
                    <td colSpan="2">三、营业利润（亏损以“—”号填列）</td>
                    <td>9</td>
                    <td>{obj.YYLR1}</td>
                    <td>{obj.YYLR}</td>
                </tr>
                <tr>
                    <td style={{paddingLeft:'3em'}} colSpan="2">加：投资收益（损失以“—”号填列）</td>
                    <td>10</td>
                    <td>{obj.TZSY1}</td>
                    <td>{obj.TZSY}</td>
                </tr>
                <tr>
                    <td style={{paddingLeft:'5em'}} colSpan="2">补贴收入</td>
                    <td>11</td>
                    <td>{obj.BTSR1}</td>
                    <td>{obj.BTSR}</td>
                </tr>
                <tr>
                    <td style={{paddingLeft:'5em'}} colSpan="2">营业外收入</td>
                    <td>12</td>
                    <td>{obj.YYWSR1}</td>
                    <td>{obj.YYWSR}</td>
                </tr>
                <tr>
                    <td style={{paddingLeft:'3em'}} colSpan="2">减：营业外支出</td>
                    <td>13</td>
                    <td>{obj.YYWZC1}</td>
                    <td>{obj.YYWZC}</td>
                </tr>
                <tr>
                    <td colSpan="2">四、利润总额（亏损总额以“—”号填列）</td>
                    <td>14</td>
                    <td>{obj.LRZE1}</td>
                    <td>{obj.LRZE}</td>
                </tr>
                <tr>
                    <td style={{paddingLeft:'3em'}} colSpan="2">减：所得税</td>
                    <td>15</td>
                    <td>{obj.SDS1}</td>
                    <td>{obj.SDS}</td>
                </tr>
                <tr>
                    <td colSpan="2">五、净利润（亏损以“—”号填列）</td>
                    <td>16</td>
                    <td>{obj.JLR1}</td>
                    <td>{obj.JLR}</td>
                </tr>
                <tr>
                    <td style={{textAlign:'center'}} colSpan="2">补充资料</td>
                    <td> </td>
                    <td> </td>
                    <td> </td>
                </tr>
                <tr>
                    <td colSpan="2">项目</td>
                    <td> </td>
                    <td>本年累计数</td>
                    <td>上年累计数</td>
                </tr>
                <tr>
                    <td colSpan="2">1、出售、处置部门或被投资单位所得收益</td>
                    <td> </td>
                    <td>{obj.CSCZSY1}</td>
                    <td>{obj.CSCZSY}</td>
                </tr>
                <tr>
                    <td colSpan="2">2、自然灾害发生的损失</td>
                    <td> </td>
                    <td>{obj.ZHSS1}</td>
                    <td>{obj.ZHSS}</td>
                </tr>
                <tr>
                    <td colSpan="2">3、会计政策变更增加（或减少）利润总额</td>
                    <td> </td>
                    <td>{obj.ZCBGLR1}</td>
                    <td>{obj.ZCBGLR}</td>
                </tr>
                <tr>
                    <td colSpan="2">4、会计估计变更增加（或减少）利润总额</td>
                    <td> </td>
                    <td>{obj.GJBGLR1}</td>
                    <td>{obj.GJBGLR}</td>
                </tr>
                <tr>
                    <td colSpan="2">5、债务重组损失</td>
                    <td> </td>
                    <td>{obj.ZWCZSS1}</td>
                    <td>{obj.ZWCZSS}</td>
                </tr>
                <tr>
                    <td colSpan="2">6、其它</td>
                    <td> </td>
                    <td>{obj.QT1}</td>
                    <td>{obj.QT}</td>
                </tr>
                <tr>
                    <td style={{textAlign:'center'}} colSpan="2">主营业务收入项目</td>
                    <td> </td>
                    <td>本月数</td>
                    <td>本年累计数</td>
                </tr>
                <tr>
                    <td>代理税务登记收入</td>
                    <td>户次</td>
                    <td>{obj.DLSWDJHS}</td>
                    <td>{obj.DLSWDJSR1}</td>
                    <td>{obj.DLSWDJSR}</td>
                </tr>
                <tr>
                    <td>代理纳税申报收入</td>
                    <td>户次</td>
                    <td>{obj.DLNSSBHS}</td>
                    <td>{obj.DLNSSBSR1}</td>
                    <td>{obj.DLNSSBSR}</td>
                </tr>
                <tr>
                    <td>代理纳税审查收入</td>
                    <td>户次</td>
                    <td>{obj.DLNSSCHS}</td>
                    <td>{obj.DLNSSCSR1}</td>
                    <td>{obj.DLNSSCSR}</td>
                </tr>
                <tr>
                    <td>代理建帐建制收入</td>
                    <td>户次</td>
                    <td>{obj.DLJZJZHS}</td>
                    <td>{obj.DLJZJZSR1}</td>
                    <td>{obj.DLJZJZSR}</td>
                </tr>
                <tr>
                    <td>受聘顾问咨询收入</td>
                    <td>户次</td>
                    <td>{obj.SPGWZXHS}</td>
                    <td>{obj.SPGWZXSR1}</td>
                    <td>{obj.SPGWZXSR}</td>
                </tr>
                <tr>
                    <td>代理申请税务复议收入</td>
                    <td>户次</td>
                    <td>{obj.DLSQSWFYHS}</td>
                    <td>{obj.DLSQSWFYSR1}</td>
                    <td>{obj.DLSQSWFYSR}</td>
                </tr>
                <tr>
                    <td>培训收入</td>
                    <td>户次</td>
                    <td>{obj.PXHS}</td>
                    <td>{obj.PXSR1}</td>
                    <td>{obj.PXSR}</td>
                </tr>
                <tr>
                    <td>其它主营业务收入</td>
                    <td>户次</td>
                    <td>{obj.QTZYYWSRHS}</td>
                    <td>{obj.QTZYYWSR1}</td>
                    <td>{obj.QTZYYWSR}</td>
                </tr>
                <tr>
                    <td colSpan="2">所长：{obj.SZ}</td>
                    <td colSpan="2">主管会计：{obj.ZGKJ}</td>
                    <td>制表人：{obj.ZBR}</td>
                </tr>
                </tbody>
            </table>
        </div>
    }
});

module.exports = detailBox;