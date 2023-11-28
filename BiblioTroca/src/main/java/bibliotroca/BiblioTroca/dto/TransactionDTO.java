package bibliotroca.BiblioTroca.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.strategy.TransactionStatus;

public class TransactionDTO {
	
	private String id;
	@JsonInclude(Include.NON_NULL)
	private UserDTO seller;
	@JsonInclude(Include.NON_NULL)
	private UserDTO buyer;
	@JsonInclude(Include.NON_NULL)
	private BookDTO bookDetails;
	private LocalDateTime createdAt;
	private LocalDateTime endedAt;
	private String status;
	private String type;
	
	public TransactionDTO() { }
	
	public TransactionDTO(Long registry, LocalDateTime newStartDate, LocalDateTime newCompletionDate, TransactionStatus newTransactionStatus) {
		this.id = registry.toString();
		this.createdAt = newStartDate;
		this.endedAt = newCompletionDate;
		this.status = newTransactionStatus.getTransactionStatus();
	}
	
	public void setRegistry(Long registry) {
		this.id = registry.toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public UserDTO getSeller() {
		return seller;
	}
	public void setSeller(UserDTO seller) {
		this.seller = seller;
	}
	
	public UserDTO getBuyer() {
		return buyer;
	}
	public void setBuyer(UserDTO buyer) {
		this.buyer = buyer;
	}
	
	public BookDTO getBookDetails() {
		return bookDetails;
	}
	public void setBookDetails(BookDTO bookDetails) {
		this.bookDetails = bookDetails;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public LocalDateTime getEndedAt() {
		return endedAt;
	}
	public void setEndedAt(LocalDateTime endedAt) {
		this.endedAt = endedAt;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public static TransactionDTO returnTransactionDTO(Transaction transaction) {
		return new TransactionDTO(transaction.getRegistry(), transaction.getStartDate(), transaction.getCompletionDate(), transaction.getTransactionStatus());
	}
	
	public static Transaction returnTransaction(TransactionDTO transactionDTO) {
		return new Transaction(Long.getLong(transactionDTO.getId()), transactionDTO.getCreatedAt(), transactionDTO.getEndedAt(), TransactionStatus.getByTransactionStatus(transactionDTO.getStatus()));
	}

}
