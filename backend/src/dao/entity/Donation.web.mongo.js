import DonationRepository from "./Donation.mongo";

export default class DonationWebRepository extends DonationRepository {
    getCollectionName() {
        return 'donation-web'
    }
}