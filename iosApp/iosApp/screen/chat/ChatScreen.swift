import SwiftUI
import shared

struct ChatScreen: View {
    
    
    @StateObject var viewModel : ChatViewModel
    
    init(viewModel: ChatViewModel = .init()) {
        _viewModel = StateObject(wrappedValue: viewModel)
    }
    
    
    var body: some View {
        ZStack{
            VStack{
                if viewModel.error != nil {
                    VStack{
                        Text(viewModel.error ?? "")
                            .font(.title2)
                            .bold()
                            .frame(maxWidth: .infinity)
                            .padding()
                        
                        Button("Retry", action: { viewModel.initiateConnection() })
                            .padding()
                    }
                }
                chatHistory()
                
                newMessageBox()
            }
        }
        .navigationBarTitleDisplayMode(.inline)
        .toolbar{ ToolbarItem(placement: .principal) { appBar().padding([.trailing], 30)} }
    }
    
    func appBar( ) -> some View  {
        VStack{
            Text("Chat")
                .font(.title2)
                .bold()
                .frame(maxWidth: .infinity)
            
            Text("Demonstrate Socket Connections")
                .font(.subheadline)
                .frame(maxWidth: .infinity)
        }
    }
    
    func newMessageBox( ) -> some View {
        VStack{
                
                TextField("Message ...", text: $viewModel.newMessage)
                .textFieldStyle(PlainTextFieldStyle())
                .padding()
                .frame(maxHeight: 50)
                .overlay(RoundedRectangle(cornerRadius: 5).stroke(Color("lightGrey")))
                
                Button("Send Message"){ viewModel.sendMessage() }
                    .padding()
                    .frame(maxWidth: .infinity)
                    .background(Color.yellow.cornerRadius(8))
        }
        .padding()
    }
    
    func chatHistory( ) -> some View {
        List(viewModel.messages,id: \.self) { item in
            chatBox(message: item)
        }
    }
    
    func chatBox(message: ChatMessage) -> some View{
        VStack{
            Text(message.sender)
                .font(.subheadline)
                .frame(maxWidth: .infinity, alignment: .leading)
            
            Text(message.message)
                .font(.title2)
                .frame(maxWidth: .infinity, alignment: .leading)
        }
        .padding()
    }
}

struct ChatScreen_Previews: PreviewProvider {
    static var previews: some View {
        ChatScreen()
    }
}
