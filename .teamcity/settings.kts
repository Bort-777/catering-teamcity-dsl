import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2019.1"

project {

    vcsRoot(CateringMasterIosVcs)
    vcsRoot(HttpsGithubComBort777cateringTeamcityDslRefsHeadsMaster)

    buildType(Develop)

    params {
        password("private-key-teamcity-passphrase", "credentialsJSON:d27fa17f-3074-4e5c-a7d1-5967427b9572")
    }
}

object Develop : BuildType({
    name = "Develop Branch v2"

    vcs {
        root(CateringMasterIosVcs)
    }

    steps {
        script {
            name = "Install Dependencies"
            enabled = false
            scriptContent = "pod install"
        }
        script {
            name = "Analysing (clean analyze)"
            enabled = false
            scriptContent = "xcodebuild -workspace CateringApp.xcworkspace -scheme CateringApp -sdk iphonesimulator clean analyze"
        }
    }

    triggers {
        vcs {
            groupCheckinsByCommitter = true
        }
    }
})

object CateringMasterIosVcs : GitVcsRoot({
    name = "CateringIosVcs"
    url = "ssh://git@git.itransition.com:7999/cateringapp/cateringapp.ios.git"
    branch = "refs/heads/develop"
    authMethod = uploadedKey {
        uploadedKey = "private-key-teamcity"
        passphrase = "credentialsJSON:d27fa17f-3074-4e5c-a7d1-5967427b9572"
    }
})

object HttpsGithubComBort777cateringTeamcityDslRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/Bort-777/catering-teamcity-dsl#refs/heads/master"
    url = "https://github.com/Bort-777/catering-teamcity-dsl"
})
