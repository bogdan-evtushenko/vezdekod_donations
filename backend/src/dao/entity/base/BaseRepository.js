import {getDbClient} from "../../mongo.dao";

export default class BaseRepository {
    collection

    constructor() {
        this.collection = null
    }

    async getCollection() {
        if (this.collection == null) {
            const db = await getDbClient()

            const name = this.getCollectionName()
            this.collection = db.collection(name)
        }

        return this.collection
    }

    getCollectionName() {
        return ''
    }
}