package bibliotroca.BiblioTroca.repository;

import bibliotroca.BiblioTroca.entity.Review;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    Optional<Review> findByTransactionId(String transactionId);

   // void deleteAllBy();
 /*
   @DeleteMapping("/excluir-todos")
    public ResponseEntity<String> excluirTodosDocumentos() {
        reviewService.excluirTodosDocumentos();
        return new ResponseEntity<>("Todos os documentos foram excluídos com sucesso.", HttpStatus.OK);
    }
    public void excluirTodosDocumentos() {
        reviewRepository.deleteAll();
    }
  */
}
