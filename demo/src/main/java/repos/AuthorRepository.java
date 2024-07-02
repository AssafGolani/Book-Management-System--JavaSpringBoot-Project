package repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pojo.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
