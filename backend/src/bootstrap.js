import express from 'express'
import 'express-async-errors'
import {getRoutes} from './routes'

const bodyParser = require('body-parser')
const cors = require("cors");

const corsOptions = {
    origin: '*',
};



const config = require('../config.json')
const port = config.port


function startServer() {
    const app = express()

    app.use(cors(corsOptions));
    app.use(bodyParser.json());

    app.use('/api', getRoutes())

    return new Promise((resolve) => {
        const server = app.listen(port, () => {
            console.log(`Listening on port ${server.address().port}`)
            const originalClose = server.close.bind(server)
            server.close = () => {
                return new Promise((resolveClose) => {
                    originalClose(resolveClose)
                })
            }
            setupCloseOnExit(server)
            resolve(server)
        })
    })
}

function setupCloseOnExit(server) {
    // thank you stack overflow
    // https://stackoverflow.com/a/14032965/971592
    async function exitHandler(options = {}) {
        await server
            .close()
            .then(() => {
                console.log('Server successfully closed')
            })
            .catch((e) => {
                console.error('Something went wrong closing the server', e.stack)
            })
        if (options.exit) process.exit()
    }

    // do something when app is closing
    process.on('exit', exitHandler)

    // catches ctrl+c event
    process.on('SIGINT', exitHandler.bind(null, {exit: true}))

    // catches "kill pid" (for example: nodemon restart)
    process.on('SIGUSR1', exitHandler.bind(null, {exit: true}))
    process.on('SIGUSR2', exitHandler.bind(null, {exit: true}))

    // catches uncaught exceptions
    process.on('uncaughtException', exitHandler.bind(null, {exit: true}))
}

export {
    startServer
}