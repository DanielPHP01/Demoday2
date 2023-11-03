
import SwiftUI
import shared

struct SuccessView: View {
    private let CHECKMARK_ICON = "ic_checkmark"
    
    var body: some View {
        HStack {
            Spacer()
            Image(CHECKMARK_ICON)
                .resizable()
                .frame(width: 200, height: 200)
            
            Spacer()
        }
        .padding(.horizontal, 25)
        .padding(.top, 58)
        .padding(.bottom, 116)
        .background(
            VisualEffect(style: .systemUltraThinMaterial)
                .opacity(0.8)
        )
    }
}
