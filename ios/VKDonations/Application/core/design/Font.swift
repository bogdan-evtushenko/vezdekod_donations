//
//  Font.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import UIKit

struct Font {
    public static let headerBold =
        UIFont(name: "TTCommons-DemiBold", size: 21) ?? UIFont.boldSystemFont(ofSize: 21)
    public static let header =
        UIFont(name: "Roboto-Regular", size: 20) ?? UIFont.systemFont(ofSize: 20)
    public static let huge =
        UIFont(name: "Roboto-Bold", size: 32) ?? UIFont.boldSystemFont(ofSize: 32)
    public static let title =
        UIFont(name: "Roboto-Medium", size: 18) ?? UIFont.systemFont(ofSize: 18)
    public static let titleRegular =
        UIFont(name: "Roboto-Regular", size: 18) ?? UIFont.systemFont(ofSize: 18)
    public static let titleBold =
        UIFont(name: "Roboto-Bold", size: 18) ?? UIFont.systemFont(ofSize: 18)
    public static let titleItalic =
        UIFont(name: "Roboto-Italic", size: 18) ?? UIFont.systemFont(ofSize: 18)
    public static let body =
        UIFont(name: "SFProText-Regular", size: 16) ?? UIFont.systemFont(ofSize: 16)
    public static let bodyMedium =
        UIFont(name: "SFProText-Medium", size: 16) ?? UIFont.systemFont(ofSize: 16)
    public static let bodyBold =
        UIFont(name: "TTCommons-DemiBold", size: 15) ?? UIFont.boldSystemFont(ofSize: 15)
    /*public static let link =
        UIFont(name: "Roboto-Regular", size: 12) ?? UIFont.systemFont(ofSize: 12)
    public static let note =
        UIFont(name: "Roboto-Regular", size: 11) ?? UIFont.systemFont(ofSize: 11)
 */
    public static let hint =
        UIFont(name: "SFProText-Regular", size: 14) ?? UIFont.systemFont(ofSize: 14)
    public static let button =
        UIFont(name: "SFProText-Medium", size: 15) ?? UIFont.systemFont(ofSize: 15)
    
    public static let bottomButton =
        UIFont(name: "SFProText-Medium", size: 17) ?? UIFont.systemFont(ofSize: 17)
}
