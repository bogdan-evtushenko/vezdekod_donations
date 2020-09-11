import { MongoClient} from "mongodb";

const config = require('../../config.json')

let client
let db

const poolSize = config.mongo.poolSize || 5;
const mongoOptions = {
    poolSize,
    useNewUrlParser: true,
    // reconnectTries: Infinity,
    useUnifiedTopology: true
}

async function getDbClient(){
    if ((client != null) && client.isConnected() && (db != null)) return db;

    client = new MongoClient(config.mongo.url, mongoOptions);
    await client.connect();

    client.on('topologyClosed', () => {
        console.log('topology was closed');
        client = null;
        db = null;
    });

    console.log('Connected successfully to MongoDb server');

    db = client.db(config.mongo.dbName);
    return db;
}

export {
    getDbClient
}