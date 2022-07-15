import Foundation
import shared

class NewPostViewModel : ObservableObject {
    
    var feedRepository : FeedRepository
    
    init(feedRepository: FeedRepository = .init() ){
        self.feedRepository = feedRepository
    }

    
    @Published var newPostText = ""
    
    @Published var postingFeed = false
    
    @Published var error : String? = nil
    
    func postNewFeed(onSuccess: @escaping () -> Void) -> Void  {
        DispatchQueue.main.async {
            self.error = nil
            self.postingFeed = true
            self.feedRepository.postToFeed(post: self.newPostText)
            if (Bool.random()) {
                onSuccess()
            } else {
                self.error =  "Some Error"
            }
            self.postingFeed = false
        }
    }
    
}
