//
//  UIViewController+Extension.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import UIKit
import TinyConstraints

extension UIViewController {
    func presentModal(_ viewController: UIViewController, withNavigation: Bool = true, animated: Bool = true) {
        var viewController = viewController
        
        if withNavigation {
            viewController = UINavigationController(rootViewController: viewController)
        }
        
        present(viewController.apply() {
            $0.modalPresentationStyle = .fullScreen
            $0.modalTransitionStyle = .crossDissolve
        }, animated: animated)
    }
    
    func replaceChildViewController(by child: UIViewController) {
        removeAllChildren()
        
        addChild(child)
        view.addSubview(child.view)
        
        child.run {
            $0.view.edgesToSuperview()
            $0.didMove(toParent: self)
        }
    }
    
    func removeAllChildren() {
        children.forEach {
            $0.willMove(toParent: nil)
            $0.view.removeFromSuperview()
            $0.removeFromParent()
        }
    }
}
