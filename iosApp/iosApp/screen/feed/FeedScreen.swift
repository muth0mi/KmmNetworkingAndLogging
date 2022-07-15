import SwiftUI
import shared

struct FeedScreen: View {
    
    var body: some View {
        ZStack{
            VStack{
                feedList()
            }
            floatingButton( )
        }
        .navigationBarTitleDisplayMode(.inline)
        .toolbar{ ToolbarItem(placement: .principal) { appBar().padding([.trailing], 30)} }
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
                Button("New Post"                ){
                    
                }
                .padding()
                .background(Color.yellow.cornerRadius(8))
                .padding([.bottom, .trailing], 20)
                .shadow(color: .black.opacity(0.3),      radius: 3, x: 3, y: 3)
            }
        }
        
    }
    
    func feedList(  ) -> some View {
        ScrollView{
            VStack{
                
            }
        }
        .padding()
    }
}

struct FeedScreen_Previews: PreviewProvider {
    static var previews: some View {
        FeedScreen()
    }
}
