//
//  AppReducer.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import ReSwift

func AppReducer(action: Action, state: AppState?) -> AppState {
    guard let state = state else { fatalError() }

    return AppState(
        news: NewsReducer(action, state)
    )
}

func NewsReducer(_ action: Action, _ state: AppState) -> NewsState {
    var newState = state.news
    
    switch(action) {
    case let action as ApiRequests.FetchNews.Success:
        newState.news = action.news
    default:
        break
    }
        
    return newState
}
