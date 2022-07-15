import SwiftUI
import shared

struct NewPostScreen: View {
    
    var onDismiss: () -> Void
    
    
    @StateObject var viewModel : NewPostViewModel
    
    init(viewModel: NewPostViewModel = .init() , onDismiss: @escaping () -> Void) {
        _viewModel = StateObject(wrappedValue: viewModel)
        self.onDismiss =   {onDismiss()}
    }
    
    
    var body: some View {
        
        VStack{
            Text("New Post")
                .font(.largeTitle)
                .frame(maxWidth: .infinity)
            
            Spacer()
            
            TextField(
                "Post your status ...",
                text: $viewModel.newPostText
            )
            .textFieldStyle(PlainTextFieldStyle())
            .padding()
            .frame(maxHeight: .infinity)
            .overlay(RoundedRectangle(cornerRadius: 5).stroke(Color("lightGrey")))
            
            if viewModel.error != nil {
                Text(viewModel.error ?? "" )
                    .font(.title)
                    .foregroundColor(Color.red)
                    .frame(maxWidth: .infinity)
            }
            
            Button("Post Update", action: { viewModel.postNewFeed(onSuccess: onDismiss)})
                .padding()
                .frame(maxWidth: .infinity)
                .background(Color.yellow.cornerRadius(8))
                .disabled(viewModel.postingFeed)
        }
        .padding()
        .frame(alignment: .topLeading)
    }
}

struct NewPostScreen_Previews: PreviewProvider {
    static var previews: some View {
        NewPostScreen(onDismiss: {})
    }
}
