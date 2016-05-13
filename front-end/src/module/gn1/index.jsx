module.exports = {
    path: 'gn1(/)',
    breadcrumbName:'功能1',
    getComponent(location, cb) {
        require.ensure([], (require) => {
            cb(null, require('./component'))
        })
    }
}