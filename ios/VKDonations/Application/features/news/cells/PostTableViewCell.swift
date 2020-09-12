//
//  PostTableViewCell.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import UIKit
import SDWebImage

class PostTableViewCell: UITableViewCell {
    
    private let postCardView = CardView()
    private let userIcon = UIImageView(image: UIImage(named: "avatar.png"))
    private let userName = UILabel().apply {
        $0.font = Font.bodyBold
    }
    private let agoLabel = HintLabel().apply {
        $0.text = "час назад"
    }
    
    private let cardView = CardView()
    private let whiteView = UIView().apply {
        $0.backgroundColor = Palette.white
    }
    private let photoView = UIImageView().apply {
        $0.contentMode = .scaleAspectFill
        $0.backgroundColor = .blue
    }
    private let nameLabel = UILabel().apply {
        $0.font = Font.bodyBold
    }
    
    private let blackView = UIView().apply {
        $0.backgroundColor = .black
    }
    private let hintLabel = HintLabel()
    private let helpFirst = HintLabel().apply {
        $0.text = "Собрано в сентябре 8 750 ₽"
    }
    private let progressBar = ProgressBar().apply {
        $0.transition(to: .determinate(percentage: 0))
    }
    
    private let button = CommonButton().apply {
        $0.setTitle("Помочь", for: .normal)
        $0.mode = .bordered
    }
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupView()
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setupView()
    }

    private func setupView() {
        selectionStyle = .none
        
        buildViewTree()
        setConstraints()
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 2.0) {
            self.progressBar.transition(to: .determinate(percentage: 0.75))
        }
    }
    
    private func buildViewTree() {
        addSubview(postCardView)
        
        [userIcon, userName, agoLabel, cardView].forEach(postCardView.addSubview)
        [photoView, whiteView].forEach(cardView.contentView.addSubview)
        [nameLabel, hintLabel, blackView, helpFirst, progressBar, button].forEach(whiteView.addSubview)
    }
    
    private func setConstraints() {
        postCardView.run {
            $0.horizontalToSuperview(insets: .horizontal(12))
            $0.verticalToSuperview(insets: .vertical(8))
            //$0.heightToWidth(of: postCardView, multiplier: 9 / 16.0)
        }
        
        userIcon.run {
            $0.topToSuperview(offset: 12)
            $0.leadingToSuperview(offset: 12)
            $0.width(36)
            $0.height(36)
        }
        
        userName.run {
            $0.leadingToTrailing(of: userIcon, offset: 8)
            $0.topToSuperview(offset: 12)
        }
        
        agoLabel.run {
            $0.leadingToTrailing(of: userIcon, offset: 8)
            $0.topToBottom(of: userName)
        }
        
        cardView.run {
            $0.topToBottom(of: userIcon, offset: 16)
            $0.horizontalToSuperview(insets: .horizontal(12))
            $0.bottomToSuperview(offset: -16)
            $0.heightToWidth(of: cardView, multiplier: 9 / 16.0)
        }
        
        photoView.edgesToSuperview()
        
        whiteView.run {
            $0.bottomToSuperview()
            $0.horizontalToSuperview()
        }
        
        nameLabel.run {
            $0.topToSuperview(offset: 8)
            $0.horizontalToSuperview(insets: .horizontal(12))
        }
        
        hintLabel.run {
            $0.topToBottom(of: nameLabel)
            $0.horizontalToSuperview(insets: .horizontal(12))
        }
        
        blackView.run {
            $0.topToBottom(of: hintLabel, offset: 8)
            $0.height(0.5)
            $0.horizontalToSuperview(insets: .horizontal(12))
        }
        
        helpFirst.run {
            $0.topToBottom(of: blackView, offset: 10)
            $0.leadingToSuperview(offset: 12)
        }
        
        progressBar.run {
            $0.height(4)
            $0.topToBottom(of: helpFirst, offset: 8)
            $0.leadingToSuperview(offset: 12)
            $0.trailingToLeading(of: button, offset: -12)
            $0.bottomToSuperview(offset: -14)
        }
        
        button.run {
            $0.topToBottom(of: blackView, offset: 10)
            $0.trailingToSuperview(offset: 12)
        }
    }
    
    func bind(_ news: NewsData) {
        userName.text = news.post?.author ?? ""
        nameLabel.text = news.post?.title ?? ""
        hintLabel.text = (news.post?.author ?? "") + " · " + (news.post?.description ?? "")
        
        if let photoUrl = news.post?.imageUrl {
            print(photoUrl)
            photoView.sd_setImage(with: URL(string: photoUrl), placeholderImage: UIImage(named: "testImage"))
        }
    }
}
