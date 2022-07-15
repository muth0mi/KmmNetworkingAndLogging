import SwiftUI
import shared

struct FeedScreen: View {
    let greet = Greeting().greeting()
    
    var body: some View {
        
        
        ZStack{
            VStack{
                appBar()
                feedList()
            }
            floatingButton( )
        }
        
    }
    
    func appBar( ) -> some View {
        VStack{
            
            Text("Feed")
                .font(.largeTitle)
                .multilineTextAlignment(.center)
            
            Text("Demonstrate REST Requests")
                .font(.subheadline)
                .multilineTextAlignment(.center)
            
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
