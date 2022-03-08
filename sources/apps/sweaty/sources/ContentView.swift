import SwiftUI
import Logging

struct ContentView: View {
    
    @State var isChecked:Bool = true
    @State var isNotChecked:Bool = true
    func toggle(){isChecked = !isChecked}
    
    var body: some View {
        VStack {
            Text(SweatyStrings.hiMessage("Tuist + SwiftGen"))

            Button(action: toggle){
                HStack{
                    Image(systemName: isChecked ? "checkmark.square": "square")
                    Text("schemas to be implmenented")
                }
            }

            Button(action: toggle){
                HStack{
                    Image(systemName: isChecked ? "checkmark.square": "square")
                    Text("l10n")
                }
            }

            Button(action: toggle){
                HStack{
                    Image(systemName: isChecked ? "checkmark.square": "square")
                    Text("logging")
                }
            }
            Button {
                getLogger().debug("print me")
                getLogger().trace("is trace level on ? yes!!!")
            } label: {
                Text("check clicks")
            }
        }
    }
    
    func getLogger() -> Logger {
        var logger = Logger(label: "bla-bla-bal")
        logger.logLevel = .trace
        return logger
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
