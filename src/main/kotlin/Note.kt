import java.util.*

class Note(val name: String, val content: String) : Menu() {

    override val menuItems = listOf(
        "Показать содержимое заметки",
        "Выход"
    )

    override val header = "Меню заметки с именем '$name':"

    override fun run(response: String): Boolean {
        return when (response.toInt()) {
            menuItems.firstIndex -> {
                showContent(); true
            }
            else -> {
                exit(); false
            }
        }
    }

    private fun showContent() {
        println("\n $content \n")
    }

    override fun exit() {
        start.archive?.execute()
    }
}
object CreateNoteMenu : Menu() {
    override val menuItems = listOf(
        "Ввести имя заметки, содержимое и сохранить ее",
        "Выход"
    )
    override val header = "Меню создания заметки:"

    private fun enterNameAndContent() {
        println("Введите имя заметки:")
        val inputName = Scanner(System.`in`).nextLine()
        println("Введите содержимое заметки:")
        val responseContent = Scanner(System.`in`).nextLine()
        val note = Note(inputName, responseContent)
        val checkList: List<String>? = start.archive?.notes?.map { it.name }
        if (checkList != null) {
            if (inputName !in checkList) {
                start.archive?.addNote(note)
                println("Заметка с именем '$inputName' в архиве '${start.archive?.name}' успешно создана.")
                start.archive?.execute()
            } else {
                println(
                    "Заметка с именем '$inputName' в архиве '${start.archive?.name}' уже существует." +
                            " Попробуйте еще раз с другим именем."
                )
            }
        }
    }

    override fun run(response: String): Boolean {
        return when (response.toInt()) {
            menuItems.firstIndex -> {
                enterNameAndContent(); true
            }
            else -> {
                exit(); false
            }
        }
    }

    override fun exit() {
        start.archive?.execute()
    }
}
    