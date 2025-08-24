import android.content.Context
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.patcher.after
import com.lytefast.flexinput.model.Attachment

@AliucordPlugin
class AnonymousFiles : Plugin() {
    private val specificWord = "anonymous" // specific word here

    override fun start(context: Context) {
        patcher.after<Attachment<*>>("getDisplayName") {
            val originalName = it.result as String
            val ext = originalName.substringAfterLast('.', "")
            
            // use the specific word + counter if needed to avoid duplicates
            it.result = if (ext.isNotEmpty()) "$specificWord.$ext" else specificWord
        }
    }

    override fun stop(context: Context) = patcher.unpatchAll()
}
