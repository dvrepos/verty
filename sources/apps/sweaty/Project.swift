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

let project = Project(
    name: "Sweaty",
    targets: [target]
)
