package repository;

import org.springframework.stereotype.Repository;

import entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserRepository extends MongoRepository<User, Long>{

	User getReferenceByCpf(String cpf);

	boolean existsByCpf(String cpf);

	User findByCpf(String cpf);

	void deleteByCpf(String cpf);

}
