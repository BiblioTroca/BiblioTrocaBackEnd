package bibliotroca.BiblioTroca.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.strategy.TransactionStatus;

public class TransactionDTO {
	
	private Long registry;
	@JsonInclude(Include.NON_NULL)
	private UserDTO seller;
	@JsonInclude(Include.NON_NULL)
	private UserDTO buyer;
	@JsonInclude(Include.NON_NULL)
	private BookDTO book;
	private String startDate;
	private String completionDate;
	private TransactionStatus transactionStatus;
	
	public TransactionDTO() { }
	
	public TransactionDTO(Long registry, String newStartDate, String newCompletionDate, TransactionStatus newTransactionStatus) {
		this.registry = registry;
		this.startDate = newStartDate;
		this.completionDate = newCompletionDate;
		this.transactionStatus = newTransactionStatus;
	}
	
	public Long getRegistry() {
		return registry;
	}
	public void setRegistry(Long registry) {
		this.registry = registry;
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
	
	public BookDTO getBook() {
		return book;
	}
	public void setBook(BookDTO book) {
		this.book = book;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}
	
	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	public static TransactionDTO returnTransactionDTO(Transaction transaction) {
		return new TransactionDTO(transaction.getRegistry(), transaction.getStartDate(), transaction.getCompletionDate(), transaction.getTransactionStatus());
	}
	
	public static Transaction returnTransaction(TransactionDTO transactionDTO) {
		return new Transaction(transactionDTO.getRegistry(), transactionDTO.getStartDate(), transactionDTO.getCompletionDate(), transactionDTO.getTransactionStatus());
	}

}
