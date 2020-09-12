//
//  BodyMediumLabel.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import UIKit

class BodyMediumLabel: UILabel {
    public override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    public required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setupView()
    }
    
    private func setupView() {
        font = Font.bodyMedium
        textColor = Palette.doveGray
        numberOfLines = 0
    }
}
