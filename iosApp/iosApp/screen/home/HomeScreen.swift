import SwiftUI
import shared

struct HomeScreen: View {
    let greet = Greeting().greeting()
    
    var body: some View {
        VStack{
            Spacer()
            
            heading()
            
            Spacer()
            
            actions()
            
        }
    }
    
    func heading( ) -> some View {
        Text("Network Calls and Logging in KMM")
            .padding()
            .font(.largeTitle)
            .multilineTextAlignment(.center)
    }
    
    
    func actions(  ) -> some View {
        VStack{
            
            Button("REST Requests" ) {
                print("Button pressed")
            }
            .padding()
            .frame(maxWidth: .infinity)
            .background(Color.yellow.cornerRadius(8))
            
            
            Button("Socket Requests" ) {
                print("Button pressed")
            }
            .padding()
            .frame(maxWidth: .infinity)
            .background(Color.yellow.cornerRadius(8))
            
            
            Button("Server Sent Events" ) {
                print("Button pressed")
            }
            .padding()
            .frame(maxWidth: .infinity)
            .background(Color.yellow.cornerRadius(8))
            
            
            Button("GraphQl Requests" ) {
                print("Button pressed")
            }
            .padding()
            .frame(maxWidth: .infinity)
            .background(Color.yellow.cornerRadius(8))
            
        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
