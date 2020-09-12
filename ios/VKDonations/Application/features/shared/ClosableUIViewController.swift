//
//  ClosableUIViewController.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation

import Foundation
import UIKit

class ClosableUIViewController: BaseViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        
        navigationItem.leftBarButtonItem = UIBarButtonItem(
            image: UIImage(named: "ic_back")?.withRenderingMode(.alwaysTemplate),
            style: .plain,
            target: self,
            action: #selector(closeTapped)
        )
    }

    @objc func closeTapped() {
        dismiss(animated: true)
    }
}
