import Foundation
import chat
import logging

class ChatViewModel : ObservableObject {
    
    var  logger : Logger
    
    var chatRepository : ChatRepository
    
    init(chatRepository: ChatRepository = ChatRepositoryImpl.init(chatApi: ChatApi()) ){
        self.chatRepository = chatRepository
        
        self.logger = Logger.init()
        self.logger.i(message: "ChatViewModel Launched", tag: "ChatViewModel" )
        
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
        self.logger.i(message: "Listening for new messages", tag: "ChatViewModel" )
        
        
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
        self.logger.i(message: "Sending message: message -> " + newMessage, tag: "ChatViewModel" )
        
        
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
