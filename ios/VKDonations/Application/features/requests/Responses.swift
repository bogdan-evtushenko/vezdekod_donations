//
//  Responses.swift
//  VKDonations
//
//  Created by Den Matyash on 12.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import ObjectMapper

struct NewsData: Mappable {
    
    var _id: String?
    var user_id: Int?
    var post: Post?
    
    init?(map: Map) {}
    
    mutating func mapping(map: Map) {
        post <- map["data"]
        _id <- map["_id"]
        user_id <- map["user_id"]
    }
}

struct Post: Mappable {
    var id: String?
    var imageUrl: String?
    var title: String?
    var amount: Int?
    var goal: String?
    var description: String?
    var author: String?
    var startDate: Int?
    var isRegular: Bool?
    var endDate: Int?
    
    init?(map: Map) {}
    
    mutating func mapping(map: Map) {
        id <- map["id"]
        imageUrl <- map["imageUrl"]
        title <- map["title"]
        amount <- map["amount"]
        goal <- map["goal"]
        description <- map["description"]
        author <- map["author"]
        startDate <- map["startDate"]
        isRegular <- map["isRegular"]
        endDate <- map["endDate"]
    }
}
