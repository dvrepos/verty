//
//  Dependencies.swift
//  Config
//

import ProjectDescription

let dependencies = Dependencies(
    swiftPackageManager: [
        .remote(url: "https://github.com/apple/swift-log.git", requirement: .upToNextMajor(from: "1.4.2")),
    ],
    platforms: [.iOS]
)
