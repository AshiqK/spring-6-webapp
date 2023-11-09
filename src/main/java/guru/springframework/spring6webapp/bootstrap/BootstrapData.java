package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt, Spring Framework Guru.
 */
@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        Publisher bubblesPublisher = new Publisher();
        bubblesPublisher.setName("Bubblisher");
        bubblesPublisher.setAddress("5 Bubble Street");
        bubblesPublisher.setCity("Soap City");
        bubblesPublisher.setState("Float State");
        bubblesPublisher.setZip("00000");

        Author ericSaved = authorRepository.save(eric);
        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);
        Book dddSaved = bookRepository.save(ddd);
        Publisher bubblesPublisherSaved = publisherRepository.save(bubblesPublisher);

        ericSaved.getBooks().add(ddd);
        rodSaved.getBooks().add(noEJB);
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);
        dddSaved.setPublisher(bubblesPublisher);
        noEJBSaved.setPublisher(bubblesPublisher);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());

        System.out.println("Id for bubbles: "+bubblesPublisherSaved.getId());
        Publisher retrieved = publisherRepository.findById(bubblesPublisherSaved.getId()).get();
        System.out.println("Find by id done: "+retrieved.getName());


    }
}










