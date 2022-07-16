import Foundation
import logging

class HomeViewModel : ObservableObject {
    
    var  logger : Logger
    
    init(){
        self.logger = Logger.init()
        self.logger.i(message: "HomeScreen Launched", tag: "HomeViewModel" )
    }
    
    @Published var shouldNavigateToFeed = false
    
    func navigateToFeed() -> Void {
        self.shouldNavigateToFeed.toggle()
        self.logger.i(message: "Navigate to feed screen", tag: "HomeViewModel" )
    }
    
    
    @Published var shouldNavigateToChat = false
    
    func navigateToChat() -> Void {
        self.shouldNavigateToChat.toggle()
        self.logger.i(message: "Navigate to chat screen", tag: "HomeViewModel" )
    }
    
}
