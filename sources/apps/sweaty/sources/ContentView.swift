import SwiftUI
import Logging

struct ContentView: View {
    var body: some View {
        VStack {
            Text("Hello, Tuist project!")
                .padding()
            Button {
                getLogger().debug("print me")
                getLogger().trace("is trace level on ? yes!!!")
            } label: {
                Text("i10n has to be implemented")
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
