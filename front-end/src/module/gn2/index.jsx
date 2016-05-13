module.exports = {
    path: 'gn2(/)',
    breadcrumbName:'功能2',
    getComponent(location, cb) {
        require.ensure([], (require) => {
            cb(null, require('./component'))
        })
    }
}