//
//  CloseView.swift
//  VKDonations
//
//  Created by Den Matyash on 12.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import UIKit

class CloseView: UIView {
    
    private let crossIcon = UIImageView(image: UIImage(named: "ic_cross"))
    
    public override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    public required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setupView()
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        
        layer.cornerRadius = bounds.size.width / 2.0
    }
    
    private func setupView() {
        backgroundColor = UIColor(red: 0.6, green: 0.635, blue: 0.678, alpha: 1)
        layer.run {
            $0.borderColor = Palette.white.cgColor
            $0.borderWidth = 2
        }
        
        addSubview(crossIcon)
        crossIcon.centerInSuperview()
    }
}
