//
//  BackgroundView.swift
//  iosApp
//
//  Created by Daniyar Marklenovich on 24/6/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct BackgroundView: View {
    private let BACKGROUND_IMAGE = "backgroundImage"
    
    var body: some View {
        Image(BACKGROUND_IMAGE)
            .resizable()
            .aspectRatio(contentMode: .fill)
            .edgesIgnoringSafeArea(.all)
    }
}

struct BackgroundView_Previews: PreviewProvider {
    static var previews: some View {
        BackgroundView()
    }
}
