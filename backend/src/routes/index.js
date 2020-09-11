import express from 'express'
import {getAndroidRoutes} from './android.routes'

function getRoutes() {
    const router = express.Router()
    router.use('/android', getAndroidRoutes())
    return router
}

export {
    getRoutes
}