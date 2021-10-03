data class Player(val id: Int, val name: String, val hp: Int) {
    companion object Player {
        private var lastId = 0
        private const val HP = 100
        fun create(name: String) =
            Player(lastId++, name, hp = HP)
    }
}