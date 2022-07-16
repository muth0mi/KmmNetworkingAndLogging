import Foundation

class HomeViewModel : ObservableObject {
    
    @Published var shouldNavigateToFeed = false
    
    func navigateToFeed() -> Void {
        self.shouldNavigateToFeed.toggle()
    }
    
    
    @Published var shouldNavigateToChat = false
    
    func navigateToChat() -> Void {
        self.shouldNavigateToChat.toggle()
    }
    
}
