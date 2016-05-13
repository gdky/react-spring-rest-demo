const asideMenuInit = {
  data: [{
    id: '1',
    name: '机构管理',
    href: '/gn1'
  }, {
    id: '2',
    name: '人员管理',

    children: [{
      id: '8',
      name: '人员查询',
      
      children: [{
      id: '9',
      name: '执业税务师查询',
      href:'/rygl/rycx/zyswscx'
    }]
    }]
  }, {
    id: '6',
    name: '模块设置',
    href: '/xtgl/mksz'
  },{
    id: '10',
    name: '会员会费管理',
    
    children:[{
      id:'11',
      name: '会费缴纳情况',
      href: '/hyhfgl/hfjlqk',
    },{ id: '12',
      name: '执业会员会费管理',
      href: '/hyhfgl/grhyhfgl'
    }, { id: '13',
      name: '非执业会员会费管理',
      href: '/hyhfgl/fzyhyhfgl'}]
  }, {
    id: '20',
    name: '财务报表',
    
    children:[{
      id:'21',
      name: '利润分配表',
      href: '/cwbb/lrfpb',
    },{ id: '22',
      name: '执业会员会费管理',
      href: '/hyhfgl/grhyhfgl'
    }, { id: '23',
      name: '非执业会员会费管理',
      href: '/hyhfgl/fzyhyhfgl'}]
  },
  {
    id: '3',
    name: '业务管理',
    children: [{
      id: '4',
      name: '协议管理',
      href:'/ywgl/xygl'
    }]
  }]
};

module.exports = asideMenuInit;