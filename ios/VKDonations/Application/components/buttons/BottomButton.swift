//
//  BottomButton.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import UIKit
import TinyConstraints

class BottomButton: UIButton {
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    private func setupView() {
        titleLabel?.font = Font.bottomButton
        contentEdgeInsets = UIEdgeInsets(top: 10.5, left: 16, bottom: 11.5, right: 16)
        layer.run {
            $0.cornerRadius = 10
            $0.masksToBounds = true
        }
        backgroundColor = Palette.primary
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
