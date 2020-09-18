import BaseRepository from "./base/BaseRepository";

export default class DonationRepository extends BaseRepository {
    constructor() {
        super()
    }

    getCollectionName() {
        return 'donation'
    }

    async getAll(id = null) {
        const collection = await this.getCollection()

        if (id === null) {
            return collection.find({}).toArray();
        }

        return collection.find({user_id: Number(id)}).toArray()
    }

    async createNew(userId, data) {
        const collection = await this.getCollection()

        const result = await collection.insert({
            user_id: userId,
            data: data
        })

        return result.insertedIds[0]
    }

    async deleteAll() {
        const collection = await this.getCollection()

        await collection.deleteMany({})
    }
}