import Foundation

enum ApiError: Error {
    case errorOccurred
    
    var description: String {
        switch self {
        case .errorOccurred:
            return "Неожиданный ответ от сервера.\nПожалуйста, повторите попытку еще раз через некоторое время."
        }
    }
}

final class NetworkManager {
    // Singleton
    private init() { }
    static let shared = NetworkManager()
    
    private let baseURL = URL(string: "https://1070-176-123-255-178.ngrok-free.app/auth/register/")!
    
    private let session = URLSession(configuration: .default)
    
    private func encode(_ params: [String: Any]) throws -> Data {
        return try JSONSerialization.data(withJSONObject: params, options: .prettyPrinted)
    }
    
    public func registerUser(
        username: String,
        password: String,
        completion: @escaping (Bool, Error?) -> Void
    ) {
        // Создание запроса по URL
        var request = URLRequest(url: baseURL)
        request.httpMethod = "POST"
        
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.addValue("application/json", forHTTPHeaderField: "Accept")
        
        // Параметры запроса в виде словаря
        let parameters: [String: String] = [
            "username": username,
            "password": password
        ]
        
        do {
            request.httpBody = try encode(parameters)
        } catch {
            completion(false, error)
            return
        }
        
        session.dataTask(with: request) { data, response, error in
            // Проверка на отсутствие ошибок в ответе сервера
            if let error = error {
                print("Произошла ошибка при выполнении запроса: \(error.localizedDescription)")
                completion(false, error)
                return
            }
            
            // Проверка на статус-код ответа от сервера
            guard let httpResponse = response as? HTTPURLResponse,
                  (200...299).contains(httpResponse.statusCode)
            else {
                print("Получен недопустимый ответ от сервера (не в диапазоне 200...299)")
                completion(false, ApiError.errorOccurred)
                return
            }
            
            // Проверка, что данные вернулись
            guard let data = data else {
                print("Получены пустые данные от сервера")
                completion(false, ApiError.errorOccurred)
                return
            }
            
            // Добавьте здесь код для обработки данных, если необходимо
            
            completion(true, nil)
            
        }.resume()
    }
}
