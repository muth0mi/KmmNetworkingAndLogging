import Foundation
import shared

class ChatViewModel : ObservableObject {
    
    var chatRepository : ChatRepository
    
    init(chatRepository: ChatRepository = ChatRepositoryImpl.init(chatApi: ChatApi()) ){
        self.chatRepository = chatRepository
        self.initiateConnection()
    }
    
    @Published var messages : [ChatMessage] = []
    
    func initiateConnection(){
        let usernames = ["iPad", "Mac", "iPod", "Mac-Mini", "Watch"]
        self.chatRepository.openConnection(username: usernames.randomElement() ?? "") { exception  in
            if let error = exception?.localizedDescription {
                return
            }
            DispatchQueue.main.async {
            self.observeMessages()
            }
        }
    }
    
    @Published var error : String? = nil
    
    func observeMessages(){
        self.chatRepository.listenMessagesIos { chatMessage in
            self.messages.append( chatMessage)
        } completionHandler: {  exception  in
            if let error = exception?.localizedDescription {
                self.error = "Failed to listen to messages: " + error
            }
        }
        
    }
    
    @Published var newMessage : String = ""
    
    @Published var sendingMessage : Bool = false
    
    func sendMessage()   -> Void  {
        self.error = nil
        self.sendingMessage = true
        self.chatRepository.sendMessage(message: newMessage) { exception  in
            self.sendingMessage = false
            if let error = exception?.localizedDescription {
                self.error =  error
            }
        }
    }
    
    func disconnect()  -> Void  {
        chatRepository.closeConnection {  exception  in
            if let error = exception?.localizedDescription {
                return
            }
        }
    }
}
