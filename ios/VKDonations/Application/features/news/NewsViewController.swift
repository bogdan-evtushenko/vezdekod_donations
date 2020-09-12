//
//  NewsViewController.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import UIKit
import ReSwift

class NewsViewController: BaseViewController, StoreSubscriber {

    private let tableView = UITableView()
    private let tableAdapter = NewsTableViewAdapter()
    private let button = CommonButton().apply {
        $0.setTitle("create_post".localized, for: .normal)
        $0.addTarget(self, action: #selector(buttonTapped), for: .touchUpInside)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupView()
        //showLoading()
        
        store.dispatch(ApiRequests.FetchNews())
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        store.subscribe(self) { subcription in
            subcription.select { state in state.news }
        }
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        store.unsubscribe(self)
    }
    
    private func setupView() {
        view.backgroundColor = .white
        
        buildViewTree()
        setConstraints()
        
        setupTableView()
    }
    
    private func buildViewTree() {
        view.addSubview(button)
        view.addSubview(tableView)
    }
    
    private func setConstraints() {
        button.run {
            $0.topToSuperview(offset: 8)
            $0.centerXToSuperview()
        }
        tableView.run {
            $0.edgesToSuperview(excluding: .top)
            $0.topToBottom(of: button, offset: 8)
        }
    }
    
    private func setupTableView() {
        tableView.run {
            $0.delegate = tableAdapter
            $0.dataSource = tableAdapter
            $0.separatorStyle = .none
            $0.registerForReuse(cellType: PostTableViewCell.self)
        }
    }
    
    func newState(state: NewsState) {
        print(state.news)
        tableAdapter.news = state.news
        tableView.reloadData()
    }
    
    /*private func showLoading() {
        replaceChildViewController(by: LoadingViewController())
    }
    
    private func hideLoading() {
        removeAllChildren()
    }*/
    
    @objc func buttonTapped() {
        presentModal(AddDonationViewController())
    }
}
