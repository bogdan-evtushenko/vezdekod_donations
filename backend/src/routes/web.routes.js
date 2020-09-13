import express from 'express'
import DonationWebRepository from "../dao/entity/Donation.web.mongo";

const donationRepository = new DonationWebRepository()

function getWebRoutes() {
    const router = express.Router()
    router.get('/', getDonates)
    router.post('/new', createNewDonate)
    return router
}

async function getDonates(req, res) {
    const userId = req.query.userId

    const donations = await donationRepository.getAll(userId)

    res.send(JSON.stringify(donations))
}

async function createNewDonate(req, res) {
    const body = req.body

    if (!body.userId || !body.data) {
        return res.send({
            success: false
        })
    }

    const id = await donationRepository.createNew(body.userId, body.data)

    res.send(JSON.stringify({
        success: true,
        id: id
    }))
}

export {
    getWebRoutes
}