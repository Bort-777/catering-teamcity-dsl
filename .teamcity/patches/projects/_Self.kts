package patches.projects

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project
import jetbrains.buildServer.configs.kotlin.v2018_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the root project
accordingly, and delete the patch script.
*/
changeProject(DslContext.projectId) {
    params {
        expect {
            password("sandbox_oauth_token", "credentialsJSON:8625f88766da")
        }
        update {
            password("sandbox_oauth_token", "credentialsJSON:5cc94f88-d4ea-4b92-b1f8-934027067e1f")
        }
    }
}
