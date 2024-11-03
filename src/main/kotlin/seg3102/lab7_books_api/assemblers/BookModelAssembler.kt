package seg3102.lab7_books_api.assemblers

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component
import seg3102.lab7_books_api.controller.ApiController
import seg3102.lab7_books_api.entities.Book
import seg3102.lab7_books_api.representation.BookRepresentation

@Component
class BookModelAssembler : RepresentationModelAssemblerSupport<Book, BookRepresentation>(
    ApiController::class.java, BookRepresentation::class.java
) {
    override fun toModel(entity: Book): BookRepresentation {
        val representation = instantiateModel(entity)
        representation.add(WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(ApiController::class.java).getBookById(entity.id)
        ).withSelfRel())
        return representation
    }
}