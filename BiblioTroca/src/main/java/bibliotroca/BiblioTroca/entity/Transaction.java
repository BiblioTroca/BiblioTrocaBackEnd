package bibliotroca.BiblioTroca.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import bibliotroca.BiblioTroca.strategy.TransactionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

@Document(collection="Transacoes")
public class Transaction {
	
	@Id
	private String id;
	private Long registry;
	private String sellerEmail;
	private String buyerEmail;
	@NotNull(message = "O número de registro do livro é obrigatório.")
	private Long bookRegistry;
	private LocalDateTime startDate;	
	private LocalDateTime completionDate;
	@Enumerated(EnumType.STRING)
	private TransactionStatus transactionStatus;
	
	public Transaction() { }
	
	public Transaction(String id, Long registry, String sellerEmail, String buyerEmail, Long bookRegistry, LocalDateTime startDate,
			LocalDateTime completionDate, TransactionStatus transactionStatus) {
		this.id = id;
		this.registry = registry;
		this.sellerEmail = sellerEmail;
		this.buyerEmail = buyerEmail;
		this.bookRegistry = bookRegistry;
		this.startDate = startDate;
		this.completionDate = completionDate;
		this.transactionStatus = transactionStatus;
	}
	
	public Transaction(Long registry, String sellerEmail, String buyerEmail, Long bookRegistry, LocalDateTime startDate,
			LocalDateTime completionDate, TransactionStatus transactionStatus) {
		this.registry = registry;
		this.sellerEmail = sellerEmail;
		this.buyerEmail = buyerEmail;
		this.bookRegistry = bookRegistry;
		this.startDate = startDate;
		this.completionDate = completionDate;
		this.transactionStatus = transactionStatus;
	}
	
	public Transaction(Long registry, String sellerEmail, String buyerEmail, LocalDateTime startDate, LocalDateTime completionDate, TransactionStatus transactionStatus) {
		this.registry = registry;
		this.startDate = startDate;
		this.sellerEmail = sellerEmail;
		this.buyerEmail = buyerEmail;
		this.completionDate = completionDate;
		this.transactionStatus = transactionStatus;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Long getRegistry() {
		return registry;
	}
	public void setRegistry(Long registry) {
		this.registry = registry;
	}
	
	public Long getBookRegistry() {
		return bookRegistry;
	}
	public void setBookRegistry(Long bookRegistry) {
		this.bookRegistry = bookRegistry;
	}
	
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	
	public LocalDateTime getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(LocalDateTime completionDate) {
		this.completionDate = completionDate;
	}
	
	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	public String getSellerEmail() {
		return sellerEmail;
	}
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}
	
	public String getBuyerEmail() {
		return buyerEmail;
	}
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
}