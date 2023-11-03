import SwiftUI
import shared

struct MainScreen: View {
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
                        title: "Имя",
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
                                if let apiError = error as? ApiError {
                                    errorDescription = apiError.description
                                } else {
                                    errorDescription = "Ошибка сервера"
                                }
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
                        .background(.green)
                        .foregroundColor(.white)
                }
                .cornerRadius(20)
            }
            .alert(isPresented: $showAlert) {
                Alert(
                    title: Text("Ошибка ⚠️"),
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
    
    @State private var username = ""
    @State private var password = ""
    @State private var confirm = ""
    @State private var signedUp = false
    @State private var registrationError: IdentifiableError?
    
    var body: some View {
        VStack {
            Spacer()
            
            if signedUp {
                SuccessView()
            } else {
                HomeScreen(
                    username: $username,
                    password: $password,
                    confirm: $confirm,
                    signedUp: $signedUp,
                    registrationError: $registrationError
                )
            }
            
            Spacer()
        }
        .padding(.horizontal, 59)
        .background(BackgroundView())
    }
}

struct MainScreen_Previews: PreviewProvider {
    static var previews: some View {
        MainScreen()
    }
}
