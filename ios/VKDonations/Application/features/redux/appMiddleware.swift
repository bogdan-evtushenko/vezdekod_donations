//
//  appMiddleware.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import ReSwift
import Toast_Swift

let appMiddleware: Middleware<AppState> = { dispatch, getState in
    return { next in
        return { action in
            switch action {
            case let request as Request:
                request.execute()
            case let request as MessageAction:
                UIApplication.shared.windows.last?.makeToast(request.message)
            default:
                break
            }
            
            next(action)
        }
    }
}
