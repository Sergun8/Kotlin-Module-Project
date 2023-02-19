class Start {

    var archive: Archive? = null
    var note: Note? = null

    fun start() {
        println("Введите цифру чтобы выбрать необходимое действие:")
        StartMenu.execute()
    }
}

val start: Start = Start()
