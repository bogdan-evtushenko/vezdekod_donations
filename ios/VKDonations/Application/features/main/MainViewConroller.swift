import UIKit
import Foundation

class MainViewController: UITabBarController {
    private let controllers = [
        NewsViewController().apply(title: "News", iconNamed: "ic_news")
    ]

    required init() {
        super.init(nibName: nil, bundle: nil)
        setupView()
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func setupView() {
        viewControllers = controllers.map { UINavigationController(rootViewController: $0) }
    }
}

fileprivate extension UIViewController {

    func apply(title: String, iconNamed: String) -> UIViewController {
        let tabBarItem = UITabBarItem(title: title.localized, image: UIImage(named: iconNamed), selectedImage: nil)

        self.title = title.localized.capitalized
        self.tabBarItem = tabBarItem

        return self
    }
}
