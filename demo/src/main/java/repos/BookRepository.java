package repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pojo.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
