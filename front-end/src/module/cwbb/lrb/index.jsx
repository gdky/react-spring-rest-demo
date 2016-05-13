/*利润表*/
module.exports = {
    path: 'cwbb/lrb(/)',
    breadcrumbName:'利润表',
    getComponent(location, cb) {
        require.ensure([], (require) => {
            cb(null, require('./component'))
        })
    }
}