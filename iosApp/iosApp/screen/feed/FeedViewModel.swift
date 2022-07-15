import Foundation
import shared

class FeedViewModel : ObservableObject {
    
    init(){
        self.refreshFeedItems()
    }
    
    let feedRepository = FeedRepository()
    
    
    @Published var feedItems : [FeedItem] = []
    
    func refreshFeedItems()   -> Void  {
        DispatchQueue.main.async {
            self.feedItems = self.feedRepository.getFeedItems()
        }
        
    }
}
