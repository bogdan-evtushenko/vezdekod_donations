//
//  ScopeFunc.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation

public protocol ScopeFunc {
}

extension ScopeFunc {
    @inline(__always) public func apply(block: (Self) -> ()) -> Self {
        block(self)
        return self
    }

    @inline(__always) public func run<R>(block: (Self) -> R) -> R {
        return block(self)
    }
}

extension NSObject: ScopeFunc {
}
