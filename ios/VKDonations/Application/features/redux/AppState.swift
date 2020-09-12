//
//  AppState.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import ReSwift

struct AppState: StateType {
    var news = NewsState()
}

struct NewsState: StateType {
    var news: [NewsData] = []
}
