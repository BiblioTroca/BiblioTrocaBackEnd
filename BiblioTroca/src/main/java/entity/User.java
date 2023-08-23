package entity;

import jakarta.persistence.Entity;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection="Usuários")
public class User {

}
