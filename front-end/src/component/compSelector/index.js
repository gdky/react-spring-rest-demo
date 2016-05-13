/**
 * Created by ming on 2016/4/16.
 */
'use strict'

var selector = {
    SelectorDQ:require('./dq'), //地区
    SelectorCS: require('./cs'), //城市
    SelectorYear: require('./year'), //年度
    SelectorDF: require('./df'), //省份
    SelectorFG: require('./fg'), //法规代码
    SelectorFZYZXLB: require('./fzyzxlb'), //非执业注销类别
    SelectorHY: require('./hy'), // 行业门类
    SelectorJCLB: require('./jclb'), //检查类别
    SelectorJGXZ: require('./jgxz'), //机构性质
    SelectorMZ: require('./mz'),//民族
    SelectorNJJL: require('./njjl'), //年检结论
    SelectorRYZT: require('./ryzt'), //人员状态
    SelectorRYLB: require('./rylb'), //人员类别
    SelectorXL: require('./xl'), //学历
    SelectorXB: require('./xb'), //性别
    SelectorRYSF: require('./rysf'), //人员身份
    SelectorYWLX:require('./ywlx'), //业务类型
    SelectorTGZT:require('./tgzt'), //通过状态
};

module.exports = selector;