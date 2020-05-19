import org.tinylog.Logger

object Log {

    enum class Modulo(val modulo: String) {
        CONFIGURACION("CONFIGURACION"),
        POSTS("POSTS"),
        USUARIOS("USUARIOS")
    }

    fun info(tag: Modulo, msg: String) {
        Logger.info("{}::{}", tag.modulo, msg)
    }

    fun debug(tag: Modulo, msg: String) {
        Logger.debug("{}::{}", tag.modulo, msg)
    }

    fun error(tag: Modulo, msg: String) {
        Logger.error("{}::{}", tag.modulo, msg)
    }
}