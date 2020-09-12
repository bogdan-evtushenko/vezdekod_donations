//
//  ApiRequests.swift
//  VKDonations
//
//  Created by Den Matyash on 12.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import Alamofire
import ReSwift
import ObjectMapper

protocol MessageAction: Action {
    var message: String { get set }
}

fileprivate let apiLink = "http://161.35.21.226:3000/api/android/"

struct ApiRequests {
    struct FetchNews: Request {
        func execute() {
            let request = Alamofire.request("\(apiLink)")
            request.validate().responseString { response in
                switch response.result {
                case .success:
                    self.handleSuccess(response)
                case .failure:
                    self.handleFailure()
                }
            }
        }
        
        func handleSuccess(_ response: DataResponse <String>) {
            if let json = response.result.value, let news = Mapper<NewsData> ().mapArray(JSONString: json) {

                store.dispatch(Success(news: news))
            } else {
                self.handleFailure()
            }
        }
        
        func handleFailure() {
            store.dispatch(Failure(message: "No connection".localized))
        }
        
        struct Success: Action {
            var news: [NewsData]
        }
        
        struct Failure: MessageAction {
            var message: String
        }
    }
    struct UploadPost: Request {
        func execute() {
            //Alamofire.request("\(apiLink)new", method: .post, parameters: [:]).responseJSON(completionHandler: )
        }
        
        
    }
}
