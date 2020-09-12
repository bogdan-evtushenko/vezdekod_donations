//
//  ProgressBar.swift
//  VKDonations
//
//  Created by Den Matyash on 12.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import NicoProgress

class ProgressBar: NicoProgressBar {
    public override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    public required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setupView()
    }
    
    private func setupView() {
        primaryColor = Palette.primary
        secondaryColor = Palette.primary.withAlphaComponent(0.2)
    }
}
