package bibliotroca.BiblioTroca.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Livros")
public class Book {
	@Id
	private String id;
	private Long registry;
	private String title;
	private String author;
	private String field;
	private String language;
	private String edition;
	private String description;
	private String publishingCompany;
	private State state;
}
