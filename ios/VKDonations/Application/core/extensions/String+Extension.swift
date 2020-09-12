//
//  String+Extension.swift
//  VKDonations
//
//  Created by Den Matyash on 11.09.2020.
//  Copyright © 2020 Den. All rights reserved.
//

import Foundation
import UIKit

extension String {
    var localized: String {
        let string = NSLocalizedString(self, comment: "")

        if
            string == self, // the translation was not found
            let baseLanguagePath = Bundle.main.path(forResource: "Base", ofType: "lproj"),
            let baseLangBundle = Bundle(path: baseLanguagePath) {

            return NSLocalizedString(self, bundle: baseLangBundle, comment: "")
        } else {
            return string
        }
    }
    
    var attributed: NSMutableAttributedString { return NSMutableAttributedString(string: self) }
    
    func underlined(range: NSRange) -> NSAttributedString {
        let string = self.attributed
        string.addAttribute(.underlineStyle, value: NSUnderlineStyle.single.rawValue, range: range)
        return string
    }
    
    var htmlToAttributedString: NSAttributedString? {
        guard let data = data(using: .utf8, allowLossyConversion: true) else { return NSAttributedString() }
        do {
            return try NSAttributedString(
                data: data,
                options: [
                    .documentType: NSAttributedString.DocumentType.html,
                    .characterEncoding: String.Encoding.utf8.rawValue
                ],
                documentAttributes: nil
            )
        } catch {
            return NSAttributedString()
        }
    }
    
    func firstIndex(of string: String, from startPos: Index? = nil, options: CompareOptions = .literal) -> Index? {
        let startPos = startPos ?? startIndex
        
        return range(of: string, options: options, range: startPos ..< endIndex)?.lowerBound
    }
    
    var normalized: String {
        return self.folding(options: .diacriticInsensitive, locale: .current)
            .replacingOccurrences(of: "\u{27}", with: "\'")
            .replacingOccurrences(of: "\u{60}", with: "\'")
            .replacingOccurrences(of: "\u{B4}", with: "\'")
            .replacingOccurrences(of: "\u{2BC}", with: "\'")
            .replacingOccurrences(of: "\u{2018}", with: "\'")
            .replacingOccurrences(of: "\u{2019}", with: "\'")
            .lowercased()
    }
    
    func localizedFormat(args: CVarArg...) -> String {
        let format = self.localized
        return String(format: format, arguments: args)
    }
}
