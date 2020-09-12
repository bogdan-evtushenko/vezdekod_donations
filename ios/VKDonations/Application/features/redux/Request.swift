//
//  Request.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import ReSwift

protocol Request : Action {
    
    func execute()
}
