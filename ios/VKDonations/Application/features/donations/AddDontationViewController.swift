//
//  AddDontationViewController.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import UIKit

class AddDonationViewController: ClosableUIViewController, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    
    private let scrollView = UIScrollView()
    private let contentView = UIView()
    private let stackView = UIStackView().apply {
        $0.axis = .vertical
        $0.spacing = 26
        $0.alignment = .center
        $0.distribution = .equalSpacing
    }
    
    private let postCover = PostCover().apply {
        $0.isUserInteractionEnabled = true
    }
    
    private let donationNameInput = TextInputLayout(explanation: "Название сбора", placeholder: "Название сбора")
    
    private let sumInput = TextInputLayout(explanation: "Сумма в месяц, ₽", placeholder: "Сколько нужно в месяц?")
    
    private let goalInput = TextInputLayout(explanation: "Цель", placeholder: "Например, поддержка приюта")
    
    private let descriptionInput = TextInputLayout(explanation: "Описание", placeholder: "На что пойдут деньги и как они кому-то помогут?")
    
    private let bankAccountInput = TextInputLayout(explanation: "Куда получать деньги", placeholder: "").apply {
        $0.textField.text = "Счёт VK Pay · 1234"
    }
    
    private let authorInput = TextInputLayout(explanation: "Автор", placeholder: "").apply {
        $0.textField.text = "Денис Матяш"
    }
    
    private let actionButton = BottomButton().apply {
        $0.setTitle("next".localized, for: .normal)
    }
    
    var imagePicker = UIImagePickerController()

    override func viewDidLoad() {
        super.viewDidLoad()

        setupView()
    }
    
    struct UIModel {
        
    }
    
    private func setupView() {
        view.backgroundColor = .white
        title = "Целевой сбор"
        
        buildViewTree()
        setConstraints()
        
        postCover.onClick = {
            self.pickImage()
        }
        
        actionButton.addTarget(self, action: #selector(buttonTapped), for: .touchUpInside)
    }
    
    private func buildViewTree() {
        [scrollView].forEach(view.addSubview)
        
        scrollView.addSubview(contentView)
        contentView.addSubview(stackView)
        
        [postCover, donationNameInput, sumInput, goalInput, descriptionInput, bankAccountInput, authorInput, actionButton].forEach(stackView.addArrangedSubview)
    }
    
    private func setConstraints() {
        scrollView.edgesToSuperview()
        
        contentView.run {
            $0.edgesToSuperview()
            $0.width(to: scrollView)
            $0.height(to: scrollView, priority: .defaultLow)
        }
        
        stackView.run {
            $0.topToSuperview(offset: 16)
            $0.horizontalToSuperview(insets: .horizontal(16))
            $0.bottomToSuperview(offset: -16, relation: .equalOrLess)
        }
        
        postCover.run {
            $0.widthToSuperview()
            $0.height(140)
        }
        
        donationNameInput.widthToSuperview()
        sumInput.widthToSuperview()
        goalInput.widthToSuperview()
        descriptionInput.widthToSuperview()
        bankAccountInput.widthToSuperview()
        authorInput.widthToSuperview()
        actionButton.widthToSuperview()
    }
    
    private func pickImage() {
        if UIImagePickerController.isSourceTypeAvailable(.savedPhotosAlbum){
            imagePicker.run {
                $0.delegate = self
                $0.sourceType = .photoLibrary
                $0.allowsEditing = true
            }

            present(imagePicker, animated: true)
        }
    }
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        picker.dismiss(animated: true)
        guard let image = info[.originalImage] as? UIImage else {
            fatalError("Expected a dictionary containing an image, but was provided the following: \(info)")
        }
        postCover.setPhoto(image)
    }
    
    @objc func buttonTapped() {
        addDontation()
    }
    
    private func addDontation() {
        
    }
}
