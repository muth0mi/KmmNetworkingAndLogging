import Foundation
import forum

class FeedViewModel : ObservableObject {
    
    var feedRepository : FeedRepository
    
    init(feedRepository: FeedRepository = FeedRepositoryImpl.init(feedApi: FeedApi()) ){
        self.feedRepository = feedRepository
        self.refreshFeedItems()
    }
    
    
    @Published var error : String? = nil
    
    @Published var feedItems : [FeedItem] = []
    
    func refreshFeedItems()   -> Void  {
        self.error = nil
        self.feedRepository.getFeedItems () { data, exception  in
            if let feedItems = data {
                self.feedItems = feedItems
            }
            if let error = exception?.localizedDescription {
                self.error =  error
            }
        }
    }
    
    
    @Published var showingNewSheet = false
    
    func setShowingNewSheet( showing: Bool = true) -> Void  {
        showingNewSheet = showing
    }
}
