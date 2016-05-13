module.exports = {
    path: 'gn3(/)',
    breadcrumbName:'功能3',
    getComponent(location, cb) {
        require.ensure([], (require) => {
            cb(null, require('./component'))
        })
    }
}