package bibliotroca.BiblioTroca.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.strategy.TransactionStatus;

public class TransactionDTO {
	
	private String id;
	private String sellerEmail;
	private String buyerEmail;
	@JsonInclude(Include.NON_NULL)
	private UserDTO seller;
	@JsonInclude(Include.NON_NULL)
	private UserDTO buyer;
	@JsonInclude(Include.NON_NULL)
	private BookDTO bookDetails;
	private String createdAt;
	@JsonInclude(Include.NON_NULL)
	private String endedAt;
	private String status;
	@JsonInclude(Include.NON_NULL)
	private String type;
	
	public TransactionDTO() { }
	
	public TransactionDTO(Long registry, String sellerEmail, String buyerEmail, LocalDateTime newStartDate, LocalDateTime newCompletionDate, TransactionStatus newTransactionStatus) {
		this.id = registry.toString();
		this.sellerEmail = sellerEmail;
		this.buyerEmail = buyerEmail;
		this.createdAt = newStartDate != null ? newStartDate.toString() : null;
		this.endedAt = newCompletionDate != null ? newCompletionDate.toString() : null;
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
	
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getEndedAt() {
		return endedAt;
	}
	public void setEndedAt(String endedAt) {
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
	
	public static TransactionDTO returnTransactionDTO(Transaction transaction) {
		return new TransactionDTO(transaction.getRegistry(), transaction.getSellerEmail(), transaction.getBuyerEmail(), transaction.getStartDate(), transaction.getCompletionDate(), transaction.getTransactionStatus());
	}
	
	public static Transaction returnTransaction(TransactionDTO transactionDTO) {
		return new Transaction(Long.getLong(transactionDTO.getId()), transactionDTO.getSellerEmail(), transactionDTO.getBuyerEmail(), (transactionDTO.getCreatedAt() != null) ? LocalDateTime.parse(transactionDTO.getCreatedAt()) : null, (transactionDTO.getEndedAt() != null) ? LocalDateTime.parse(transactionDTO.getEndedAt()) : null, TransactionStatus.getByTransactionStatus(transactionDTO.getStatus()));
	}

}
