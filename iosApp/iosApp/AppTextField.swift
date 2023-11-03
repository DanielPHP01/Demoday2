//
//  AppTextField.swift
//  iosApp
//
//  Created by Daniyar Marklenovich on 24/6/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AppTextField: View {
    let title: String
    @Binding var text: String
    
    var body: some View {
        TextField(
            "",
            text: $text,
            prompt: Text(title).foregroundColor(.gray)
        )
        .padding()
        .font(.system(size: 14))
        .background(Color.white)
        .foregroundColor(.black)
    }
}
