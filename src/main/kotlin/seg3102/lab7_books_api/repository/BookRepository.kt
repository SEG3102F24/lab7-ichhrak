package seg3102.lab7_books_api.repository

import org.springframework.data.repository.CrudRepository
import seg3102.lab7_books_api.entities.Book

interface BookRepository : CrudRepository<Book, Long>