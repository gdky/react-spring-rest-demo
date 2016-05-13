module.exports = {
    /**
     * 通过树节点定义数组生成JSON对象，该数组要先按PID进行AESC排序
     * @param arr {Array}
     * @returns {Array}
     */
    getTreeData(arr) {
        let key = 0;
        var root = {text: null};
        if (arr.length > 0) {
            var objMap = {};
            root.items = [];
            arr.forEach(function (item) {
                var node = {
                    id: item.id,
                    pid: item.pid,
                    name: item.name,
                    href: item.href,
                    orderNo: item.orderNo ? item.orderNo : '',
                    path: item.path,
                    visble: item.visble,
                    icon: item.icon,
                    lx: item.lx,
                    key: key++
                };
                objMap[item.id] = node;
                if (item.pid == 0) {
                    root.items.push(node);
                } else {
                    var parent = objMap[item.pid];
                    if (parent["children"]) {
                        parent["children"].push(node);
                    } else {
                        parent["children"] = [];
                        parent["children"].push(node);
                    }
                }
            });
        } else {
            root.items = [];
        }
        return root.items;
    },

    /**
     * 补零操作函数
     * @param str 输入的字符串
     * @param length 需要布满的长度
     * @returns {string}
     */
    addZero(str, length){
        return new Array(length - str.length + 1).join("0") + str;
    },

    getObjBindModel(obj, model){
        model.forEach(prop=> {
            if (prop.type == 'date') {
                let date = new Date(obj[prop.id]);
                obj[prop.id] = date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDate() + '日'
            }
        });
        return obj;
    },

    entityFormat(entity, model){
        let obj = entity;
        if (model){
            for (let i = 0; i < model.length; i++) {
                const prop = model[i];
                let render = prop.render;
                let value = entity[prop.id];
                if (render && value != null) {
                    value = render(value);
                }
                obj[prop.id] = value;
            }
        }
        return obj;
    }

};
