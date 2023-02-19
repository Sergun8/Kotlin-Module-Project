import java.util.*
import kotlin.system.exitProcess

abstract class Menu {

    val List<String>.firstIndex: Int
        get() = if (this.isNotEmpty()) 0 else -1

    abstract val menuItems: List<String>
    abstract val header: String

    abstract fun run(response: String): Boolean
    abstract fun exit()

    private fun printMenu(header: String) {
        println(header)
        for ((number, item) in menuItems.withIndex()) {
            println("${number}. $item")
        }
    }

    fun execute() {
        var flag = true
        while (flag) {
            printMenu(header)
            val input = Scanner(System.`in`).nextLine()
            if (!input.matches("0|([1-9]\\d*)".toRegex())) {
                println("Пожалуста, введите цифру, соответствующую пунктам меню...")
            } else if (input.toInt() !in menuItems.indices) {
                println("Пункта меню с подобной цифрой не существует. Введите корректное значение...")
            } else {
                flag = run(input)
            }
        }
    }
}
object StartMenu : Menu() {

    val archives: MutableList<Archive> = mutableListOf()
    override val menuItems: MutableList<String> = mutableListOf(
        "Создать архив",
        "Выход"
    )

    override val header = "Список архивов:"

    override fun run(response: String): Boolean {
        return when (response.toInt()) {
            menuItems.firstIndex -> {
                createArchive(); false
            }
            menuItems.lastIndex -> {
                exit(); false
            }
            else -> {
                start.archive = archives[response.toInt() - 1]
                start.archive!!.execute()
                false
            }
        }
    }

    private fun createArchive() {
        CreateArchiveMenu.execute()
    }

    override fun exit() {
        println("Приложение 'Заметки' завершило свою работу.")
        exitProcess(0)
    }

    fun addArchive(archive: Archive) {
        archives.add(archive)
        menuItems.add(archives.size, archive.name)
    }
}

