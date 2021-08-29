import org.gradle.api.Project
import java.io.ByteArrayOutputStream

object BranchResolver {
    fun resolve(project: Project): String {
        val byteOut = ByteArrayOutputStream()
        project.exec {
            commandLine = "git rev-parse --abbrev-ref HEAD".split(" ")
            standardOutput = byteOut
        }

        return byteOut.toString().replace("\n", "")

    }
}