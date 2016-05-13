/**
 * Created by ming on 2016/4/11.
 */
import numeral from 'Numeral'
const model = {
    columns: [
        {title: '序号', dataIndex: 'key', key: 'key'},
        {title: '事务所名称', dataIndex: 'dwmc', key: 'dwmc'},
        {title: '年度', dataIndex: 'nd', key: 'nd'},
        {title: '统计时间段', key: 'tjsjd', dataIndex: 'tjsjd'},
        {title: '城市', key: 'cs', dataIndex: 'cs'},
        {
            title: '主营业务收入',
            key: 'zgywsr',
            dataIndex: 'zgywsr',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            title: '主营业务利润',
            key: 'zgwylr',
            dataIndex: 'zgwylr',
            render(num){
                return numeral(num).format('0,0.00')
            }
        }
    ],
    entityModel: [
        {id: 'DWMC', name: '机构名称'},
        {id: 'USER_ID', name: '编号'},
        {id: 'KSSJ', name: '开始时间'},
        {
            id: 'JSSJ',
            name: '结束时间',
            render(num){
                let date = new Date(num);
                return date.toLocaleDateString()
            }
        },
        {id: 'TJRQ', name: '提交日期'},
        {id: 'ZTBJ', name: '状态(0:保存,1:提交)'},
        {
            id: 'ZGYWSR',
            name: '主管业务收入',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'ZGYWCB',
            name: '主管业务成本',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'ZGYWSJ',
            name: '主管业务税金及附加',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'ZGWYLR',
            name: '主管业务利润',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'QTYWLR',
            name: '其他业务利润',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'YYFY',
            name: '营业费用',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'GLFY',
            name: '管理费用',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'CWFY',
            name: '财务费用',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'YYLR',
            name: '营业利润',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'TZSY',
            name: '投资收益',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'BTSR',
            name: '补贴收入',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'YYWSR',
            name: '营业外收入',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'YYWZC',
            name: '营业外支出',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'LRZE',
            name: '利润总额',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'SDS',
            name: '所得税',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'JLR',
            name: '静利润',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'CSCZSY',
            name: '出售、处置部门或被投资单位所得收益',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'ZHSS',
            name: '自然灾害发生的损失',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'ZCBGLR',
            name: '会计政策变更增加（或减少）利润总额',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'GJBGLR',
            name: '会计估计变更增加（或减少）利润总额',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'ZWCZSS',
            name: '债务重组损失',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {id: 'QT', name: '其它'},
        {id: 'DLSWDJHS', name: '代理税务登记户数'},
        {
            id: 'DLSWDJSR',
            name: '代理税务登记收入',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'DLNSSBSR',
            name: '代理纳税申报收入',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {id: 'DLNSSBHS', name: '代理纳税申报户数'},
        {
            id: 'DLNSSCSR',
            name: '代理纳税审查收入',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {id: 'DLNSSCHS', name: '代理纳税审查户数'},
        {
            id: 'DLJZJZSR',
            name: '代理建帐建制收入',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {id: 'DLJZJZHS', name: '代理建帐建制户数'},
        {
            id: 'SPGWZXSR',
            name: '受聘顾问咨询收入',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {id: 'SPGWZXHS', name: '受聘顾问咨询户数'},
        {
            id: 'DLSQSWFYSR',
            name: '代理申请税务复议收入',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {id: 'DLSQSWFYHS', name: '代理申请税务复议户数'},
        {
            id: 'PXSR',
            name: '培训收入',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {id: 'PXHS', name: '培训户数'},
        {id: 'ND', name: '年度'},
        {id: 'TIMEVALUE', name: '时间0-上半年1全年'},
        {
            id: 'QTZYYWSR',
            name: '其它主营业务收入',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {id: 'SZ', name: '所长'},
        {id: 'ZGKJ', name: '主管会计'},
        {id: 'ZBR', name: '制表人'},
        {id: 'QTZYYWSRHS', name: '其它主营业务收入户数'},
        {
            id: 'ZGYWSR1',
            name: '主管业务收入1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'ZGYWCB1',
            name: '主管业务成本1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'ZGYWSJ1',
            name: '主管业务税金及附加1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'ZGWYLR1',
            name: '主管业务利润1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'QTYWLR1',
            name: '其他业务利润1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'YYFY1',
            name: '营业费用1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'GLFY1',
            name: '管理费用1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'CWFY1',
            name: '财务费用1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'YYLR1',
            name: '营业利润1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'TZSY1',
            name: '投资收益1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'BTSR1',
            name: '补贴收入1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'YYWSR1',
            name: '营业外收入1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'YYWZC1',
            name: '营业外支出1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'LRZE1',
            name: '利润总额1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'SDS1',
            name: '所得税1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'JLR1',
            name: '静利润1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'CSCZSY1',
            name: '出售、处置部门或被投资单位所得收益1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'ZHSS1',
            name: '自然灾害发生的损失1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'ZCBGLR1',
            name: '会计政策变更增加（或减少）利润总额1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'GJBGLR1',
            name: '会计估计变更增加（或减少）利润总额1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'ZWCZSS1',
            name: '债务重组损失1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'QT1',
            name: '其它1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {id: 'DLSWDJHS1', name: '代理税务登记户数1'},
        {
            id: 'DLSWDJSR1',
            name: '代理税务登记收入1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {
            id: 'DLNSSBSR1',
            name: '代理纳税申报收入1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {id: 'DLNSSBHS1', name: '代理纳税申报户数1'},
        {
            id: 'DLNSSCSR1',
            name: '代理纳税审查收入1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {id: 'DLNSSCHS1', name: '代理纳税审查户数1'},
        {
            id: 'DLJZJZSR1',
            name: '代理建帐建制收入1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {id: 'DLJZJZHS1', name: '代理建帐建制户数1'},
        {
            id: 'SPGWZXSR1',
            name: '受聘顾问咨询收入1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {id: 'SPGWZXHS1', name: '受聘顾问咨询户数1'},
        {
            id: 'DLSQSWFYSR1',
            name: '代理申请税务复议收入1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {id: 'DLSQSWFYHS1', name: '代理申请税务复议户数1'},
        {
            id: 'PXSR1',
            name: '培训收入1',
            render(num){
                return numeral(num).format('0,0.00')
            }
        },
        {id: 'PXHS1', name: '培训户数1'},
        {id: 'QTZYYWSR1', name: ''},
        {id: 'QTZYYWSRHS1', name: ''}
    ]

};

module.exports = model;