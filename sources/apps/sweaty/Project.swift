import ProjectDescription
import ProjectDescriptionHelpers

let target = Target(
    name: "Sweaty",
    platform: .iOS,
    product: .app,
    bundleId: "domain.my.Sweaty",
    infoPlist: .default,
    sources: ["sources/**"],
    resources: ["resources/**"],
    dependencies: [
            .external(name: "Logging"),
    ]
)

let scheme_FR = Scheme(name: "Sweaty_Fr",
                    shared: true,
                    runAction: .runAction(
                        configuration: .debug,
                        executable: "Sweaty",
                        options: .options(language: "French")
                    )
)

let scheme_ES = Scheme(name: "Sweaty_Es",
                    shared: true,
                    runAction: .runAction(
                        configuration: .debug,
                        executable: "Sweaty",
                        options: .options(language: "Spanish")
                    )
)

let project = Project(
    name: "Sweaty",
    options: .options(
        textSettings: .textSettings(usesTabs: false, indentWidth: 4, tabWidth: 4)
    ),
    targets: [target],
    schemes: [scheme_FR, scheme_ES]
)
