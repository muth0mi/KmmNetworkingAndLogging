import Foundation
import shared

class FeedViewModel : ObservableObject {
    
    var feedRepository : FeedRepository

    init(feedRepository: FeedRepository = .init() ){
        self.feedRepository = feedRepository
        self.refreshFeedItems()
    }
        
    
    @Published var feedItems : [FeedItem] = []
    
    func refreshFeedItems()   -> Void  {
        DispatchQueue.main.async {
            self.feedItems = self.feedRepository.getFeedItems()
        }
        
    }
    
    
    @Published var showingNewSheet = false
    
    func setShowingNewSheet( showing: Bool = true) -> Void  {
         showingNewSheet = showing
    }
}
