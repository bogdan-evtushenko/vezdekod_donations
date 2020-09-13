import express from 'express'
import {getAndroidRoutes} from './android.routes'
import {getWebRoutes} from './web.routes'

function getRoutes() {
    const router = express.Router()
    router.use('/android', getAndroidRoutes())
    router.use('/web', getWebRoutes())
    return router
}

export {
    getRoutes
}