import Foundation

class HomeViewModel : ObservableObject {
    
    @Published var shouldNavigateToFeed = false
    
    func navigateToFeed() -> Void {
        self.shouldNavigateToFeed.toggle()
    }
}
