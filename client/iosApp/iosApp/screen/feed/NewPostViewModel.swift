import Foundation
import forum

class NewPostViewModel : ObservableObject {
    
    var feedRepository : FeedRepository
    
    init(feedRepository: FeedRepository = FeedRepositoryImpl.init(feedApi: FeedApi()) ){
        self.feedRepository = feedRepository
    }
    
    
    @Published var newPostText = ""
    
    @Published var postingFeed = false
    
    @Published var error : String? = nil
    
    func postNewFeed(onSuccess: @escaping () -> Void) -> Void  {
        self.error = nil
        self.postingFeed = true
        self.feedRepository.postToFeed(post: self.newPostText) { exception in
            self.postingFeed = false
            if let error = exception?.localizedDescription  {
                self.error =  error
            } else {
                onSuccess()
            }
        }
    }
    
}
