
import Foundation
import UIKit
import TinyConstraints

class CardView: UIView {
    let radius = 10.0
    
    private let shadowView = UIView().apply {
        $0.layer.masksToBounds = false
        $0.backgroundColor = .clear
        $0.layer.run {
            $0.shadowColor = UIColor.black.cgColor
            $0.shadowOffset = CGSize(width: 0.0, height: 2.0)
            $0.shadowOpacity = 0.5
            $0.shadowRadius = 2
        }
    }
    
    let contentView = UIView().apply {
        $0.backgroundColor = .white
        $0.layer.run {
            $0.masksToBounds = true
            $0.cornerRadius = 10
        }
    }
    
    public override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    public required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setupView()
    }
    
    private func setupView() {
        clipsToBounds = false
        buildViewTree()
        setConstraints()
    }
    
    private func buildViewTree() {
        addSubview(shadowView)
        shadowView.addSubview(contentView)
    }
    
    private func setConstraints() {
        shadowView.edgesToSuperview()
        contentView.edgesToSuperview()
    }
}
