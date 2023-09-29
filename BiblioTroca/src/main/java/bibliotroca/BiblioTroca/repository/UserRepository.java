package bibliotroca.BiblioTroca.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import bibliotroca.BiblioTroca.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findById(String id);

	boolean existsByCpf(String cpf);

	User findByCpf(String cpf);

	void deleteByCpf(String cpf);

}
