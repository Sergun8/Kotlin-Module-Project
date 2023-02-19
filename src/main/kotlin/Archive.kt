import java.util.*

class Archive(val name: String) : Menu() {
    val notes: MutableList<Note> = mutableListOf()
    override val menuItems: MutableList<String> = mutableListOf(
        "Создать заметку",
        "Выход"
    )
    override val header = "Список заметок архива '$name':"

    override fun exit() {
        StartMenu.execute()
    }

    override fun run(response: String): Boolean {
        return when (response.toInt()) {
            menuItems.firstIndex -> {
                createNote(); false
            }
            menuItems.lastIndex -> {
                exit(); false
            }
            else -> {
                start.note = notes[response.toInt() - 1]
                start.note!!.execute()
                false
            }
        }
    }

    private fun createNote() {
        CreateNoteMenu.execute()
    }

    fun addNote(note: Note) {
        notes.add(note)
        menuItems.add(notes.size, note.name)
    }
}
object CreateArchiveMenu : Menu() {
    override val menuItems = listOf(
        "Ввести имя архива и сохранить его",
        "Выход"
    )
    override val header = "Меню создания архива:"

    override fun run(response: String): Boolean {
        return when (response.toInt()) {
            menuItems.firstIndex -> {
                enterName(); true
            }
            else -> {
                exit(); false
            }
        }
    }

    override fun exit() {
        StartMenu.execute()
    }

    private fun enterName() {
        println("Введите имя архива:")
        val input = Scanner(System.`in`).nextLine()
        val archive = Archive(input)
        if (archive.name !in StartMenu.archives.map { it.name }) {
            StartMenu.addArchive(Archive(input))
            println("Архив с именем '$input' успешно создан.")
            StartMenu.execute()
        } else {
            println("Архив с именем '$input' уже существует. Попробуйте еще раз с другим именем.")
        }
    }
}
