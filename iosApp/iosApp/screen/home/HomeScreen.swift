import SwiftUI
import shared

struct HomeScreen: View {
    
    @StateObject var viewModel : HomeViewModel
    
    init(viewModel: HomeViewModel = .init()) {
        _viewModel = StateObject(wrappedValue: viewModel)
    }
    
        
    var body: some View {
        ZStack{
            NavigationLink( destination: FeedScreen(), isActive: $viewModel.shouldNavigateToFeed ){ EmptyView() }
            
            VStack{
                Spacer()
                
                heading()
                
                Spacer()
                
                actions()
            }
        }
        .navigationBarHidden(true)
    }
    
    func heading( ) -> some View {
        Text("Network Calls and Logging in KMM")
            .padding()
            .font(.largeTitle)
            .multilineTextAlignment(.center)
    }
    
    
    func actions(  ) -> some View {
        VStack{
            
            Button("REST Requests", action: viewModel.navigateToFeed)
                .padding()
                .frame(maxWidth: .infinity)
                .background(Color.yellow.cornerRadius(8))
            
            
            Button("Socket Requests", action: viewModel.navigateToFeed )
                .padding()
                .frame(maxWidth: .infinity)
                .background(Color.yellow.cornerRadius(8))
            
            
            Button("Server Sent Events", action: viewModel.navigateToFeed )
                .padding()
                .frame(maxWidth: .infinity)
                .background(Color.yellow.cornerRadius(8))
            
            
            Button("GraphQl Requests", action: viewModel.navigateToFeed)
                .padding()
                .frame(maxWidth: .infinity)
                .background(Color.yellow.cornerRadius(8))
            
        }
        .padding()
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
