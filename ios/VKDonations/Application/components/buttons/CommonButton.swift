//
//  CommonButton.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import UIKit
import TinyConstraints

class CommonButton: UIButton {
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    enum Mode {
        case bordered, filled
    }

    var mode: Mode = .filled {
        didSet {
            if oldValue != mode {
                setupMode()
            }
        }
    }
    
    private let color = Palette.primary
    
    private func setupView() {
        titleLabel?.font = Font.button
        contentEdgeInsets = UIEdgeInsets(top: 7.5, left: 16, bottom: 8.5, right: 16)
        layer.run {
            $0.cornerRadius = 10
            $0.masksToBounds = true
        }
        backgroundColor = color
        
        setupMode()
    }
    
    private func setupMode() {
        switch mode {
        case .bordered:
            layer.run {
                $0.backgroundColor = Palette.white.cgColor
                $0.borderWidth = 1
                $0.borderColor = color.cgColor
            }
            setTitleColor(color, for: .normal)
        default:
            layer.run {
                $0.backgroundColor = color.cgColor
            }
            setTitleColor(Palette.white, for: .normal)
        }
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
