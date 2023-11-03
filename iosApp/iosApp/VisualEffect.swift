//
//  VisualEffect.swift
//  iosApp
//
//  Created by Daniyar Marklenovich on 24/6/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct VisualEffect: UIViewRepresentable {
    @State var style : UIBlurEffect.Style
    
    func makeUIView(context: Context) -> UIVisualEffectView {
        return UIVisualEffectView(effect: UIBlurEffect(style: style))
    }
    
    func updateUIView(_ uiView: UIVisualEffectView, context: Context) {
        
    }
}
