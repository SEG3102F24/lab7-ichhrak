package seg3102.lab7_books_api.entities

import jakarta.persistence.*

@Entity
class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var firstName: String = ""
    var lastName: String = ""

    @ManyToMany
    var books: MutableList<Book> = ArrayList()
}