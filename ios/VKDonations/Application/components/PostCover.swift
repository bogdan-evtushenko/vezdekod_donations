//
//  PostCover.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import UIKit

class PostCover: UIView {

    private let contentView = UIView()
    
    private let imageIcon = UIImageView(image: UIImage(named: "ic_image"))
    private let label = BodyMediumLabel().apply {
        $0.textColor = Palette.primary
        $0.text = "Загрузить обложку"
    }
    
    private let photoView = UIImageView().apply {
        $0.contentMode = .scaleAspectFill
    }
    
    private let closeView = CloseView().apply {
        $0.isHidden = true
    }
    
    var onClick: (() -> ()) = {}
    
    public override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    public required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setupView()
    }
    
    private func setupView() {
        buildViewTree()
        setConstraints()
        
        layer.cornerRadius = 10
        clipsToBounds = true
        
        onTap(target: self, action: #selector(coverTapped))
        closeView.onTap(target: self, action: #selector(deletePhoto))
    }
    
    private func buildViewTree() {
        addSubview(contentView)
        addSubview(photoView)
        addSubview(closeView)
        [imageIcon, label].forEach(contentView.addSubview)
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        if (photoView.image == nil) {
            addDashedBorder(Palette.primary, 10)
        } else {
            layer.sublayers?.forEach {
                if ($0.name == "borderLayer") {
                    $0.removeFromSuperlayer()
                }
            }
        }
    }
    
    private func setConstraints() {
        contentView.centerInSuperview()
        
        imageIcon.run {
            $0.centerYToSuperview()
            $0.leadingToSuperview()
        }
        
        label.run {
            $0.leadingToTrailing(of: imageIcon, offset: 10)
            $0.centerYToSuperview()
            $0.trailingToSuperview()
        }
        
        photoView.edgesToSuperview()
        
        closeView.run {
            $0.width(24)
            $0.height(24)
            $0.trailingToSuperview(offset: 8)
            $0.topToSuperview(offset: 8)
        }
    }
    
    @objc func coverTapped() {
        if photoView.image == nil {
            onClick()
        }
    }
    
    func setPhoto(_ image: UIImage) {
        photoView.image = image
        layoutSubviews()
        closeView.isHidden = false
    }
    
    @objc func deletePhoto() {
        photoView.image = nil
        layoutSubviews()
        closeView.isHidden = true
    }
}
