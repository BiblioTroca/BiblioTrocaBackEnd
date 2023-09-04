package bibliotroca.BiblioTroca.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import bibliotroca.BiblioTroca.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	boolean existsByCpf(String cpf);

	User findByCpf(String cpf);

	void deleteByCpf(String cpf);

	User getByCpf(String cpf);

}
