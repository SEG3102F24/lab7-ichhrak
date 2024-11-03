package seg3102.lab7_books_api.representation

import org.springframework.hateoas.RepresentationModel
import seg3102.lab7_books_api.entities.Author

class AuthorRepresentation(author: Author) : RepresentationModel<AuthorRepresentation>() {
    val id: Long = author.id
    val firstName: String = author.firstName
    val lastName: String = author.lastName
}