package seg3102.lab7_books_api.controller

import org.springframework.hateoas.CollectionModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import seg3102.lab7_books_api.assemblers.BookModelAssembler
import seg3102.lab7_books_api.entities.Book
import seg3102.lab7_books_api.repository.AuthorRepository
import seg3102.lab7_books_api.repository.BookRepository
import seg3102.lab7_books_api.representation.AuthorRepresentation
import seg3102.lab7_books_api.representation.BookRepresentation

@RestController
@RequestMapping("/books-api")
class ApiController(
    val bookRepository: BookRepository,
    val authorRepository: AuthorRepository,
    val bookAssembler: BookModelAssembler
) {
    @GetMapping("/books")
    fun getAllBooks(): ResponseEntity<CollectionModel<BookRepresentation>> {
        val books = bookRepository.findAll()
        return ResponseEntity.ok(bookAssembler.toCollectionModel(books))
    }

    @GetMapping("/books/{id}")
    fun getBookById(@PathVariable id: Long): ResponseEntity<BookRepresentation> {
        val book = bookRepository.findById(id)
        return book.map { ResponseEntity.ok(bookAssembler.toModel(it)) }
            .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("/books")
    fun addBook(@RequestBody book: Book): ResponseEntity<BookRepresentation> {
        val savedBook = bookRepository.save(book)
        return ResponseEntity.ok(bookAssembler.toModel(savedBook))
    }

    @GetMapping("/authors/{id}")
    fun getAuthorById(@PathVariable id: Long): ResponseEntity<AuthorRepresentation> {
        val author = authorRepository.findById(id)
        return author.map { ResponseEntity.ok(AuthorRepresentation(it)) }
            .orElse(ResponseEntity.notFound().build())
    }
}