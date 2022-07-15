import SwiftUI
import shared

struct FeedScreen: View {
    
    
    @StateObject var viewModel : FeedViewModel
    
    init(viewModel: FeedViewModel = .init()) {
        _viewModel = StateObject(wrappedValue: viewModel)
    }
    
    
    var body: some View {
        ZStack{
            VStack{
                feedList()
            }
            floatingButton( )
        }
        .navigationBarTitleDisplayMode(.inline)
        .toolbar{ ToolbarItem(placement: .principal) { appBar().padding([.trailing], 30)} }
        .sheet(
            isPresented: $viewModel.showingNewSheet,
            onDismiss: {viewModel.setShowingNewSheet(showing: false)}
        ){
            NewPostScreen( onDismiss: {viewModel.setShowingNewSheet(showing: false)} )
        }
    }
    
    func appBar( ) -> some View  {
        VStack{
            Text("Feed")
                .font(.title2)
                .bold()
                .frame(maxWidth: .infinity)
            
            Text("Demonstrate REST Requests")
                .font(.subheadline)
                .frame(maxWidth: .infinity)
        }
    }
    
    func floatingButton( ) -> some View {
        VStack{
            Spacer()
            HStack {
                Spacer()
                Button("New Post"){ viewModel.setShowingNewSheet() }
                .padding()
                .background(Color.yellow.cornerRadius(8))
                .padding([.bottom, .trailing], 20)
                .shadow(color: .black.opacity(0.3),      radius: 3, x: 3, y: 3)
            }
        }
        
    }
    
    func feedList(  ) -> some View {
        List(viewModel.feedItems,id: \.self) { item in
            feedRow(feedItem: item)
        }
        .refreshable { viewModel.refreshFeedItems()}
    }
    
    func feedRow(feedItem: FeedItem) -> some View{
        VStack{
            Text(feedItem.post)
                .font(.title2)
                .bold()
                .frame(maxWidth: .infinity)
            
            Text(feedItem.author)
                .font(.subheadline)
                .frame(maxWidth: .infinity)
        }
    }
}

struct FeedScreen_Previews: PreviewProvider {
    static var previews: some View {
        FeedScreen()
    }
}
