import SwiftUI
import shared

struct IdentifiableError: Identifiable {
    var id = UUID()
    let error: Error
}

struct HomeScreen: View {
    let networkManager = NetworkManager.shared
    @Binding var username: String
    @Binding var password: String
    @Binding var confirm: String
    
    @State private var errorDescription = ""
    @State private var showAlert = false
    
    @Binding var signedUp: Bool
    @Binding var registrationError: IdentifiableError?
    
    var body: some View {
        VStack(spacing: 0) {
            Text("Регистрация")
                .font(.system(size: 22))
                .padding(.bottom, 12)
            
            VStack(spacing: 8.5) {
                AppTextField(
                    title: "Логин",
                    text: $username
                )
                AppTextField(
                    title: "Пароль",
                    text: $password
                )
                AppTextField(
                    title: "Введите пароль еще раз",
                    text: $confirm
                )
            }
            .padding(.bottom, 28.75)
            
            Button {
                // Валидация полей
                guard !username.isEmpty, !password.isEmpty, !confirm.isEmpty else {
                    showAlert = true
                    errorDescription = "Все поля должны быть заполнены!"
                    return
                }
                
                guard password == confirm else {
                    showAlert = true
                    errorDescription = "Пароли должны совпадать!"
                    return
                }
                
                // Отправка запроса на регистрацию
                networkManager.registerUser(username: username, password: password) { success, error in
                    DispatchQueue.main.async {
                        if let error = error {
                            registrationError = IdentifiableError(error: error)
                            showAlert = true
                        } else if success {
                            signedUp = true
                        }
                    }
                }
            } label: {
                Text("Зарегистрироваться")
                    .padding()
                    .foregroundColor(.white)
                    
                    
            }
            .cornerRadius(20)
            .background(.green)
            
        }
    
        
        .alert(isPresented: $showAlert) {
            Alert(
                title: Text("Ошибка"),
                message: Text(errorDescription)
            )
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
