package bibliotroca.BiblioTroca.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import bibliotroca.BiblioTroca.entity.Book;
import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.strategy.TransactionStatus;

public class TransactionDTO {
	
	@JsonInclude(Include.NON_NULL)
	private UserDTO seller;
	@JsonInclude(Include.NON_NULL)
	private UserDTO buyer;
	@JsonInclude(Include.NON_NULL)
	private BookDTO book;
	private int paymentPoints;
	private boolean bookReceived;
	private LocalDateTime startDate;
	private LocalDateTime completionDate;
	private TransactionStatus transactionStatus;
	
	public TransactionDTO() { }
	
	public TransactionDTO(int newPaymentPoints, boolean newBookReceived, LocalDateTime newStartDate, LocalDateTime newCompletionDate, 
			TransactionStatus newTransactionStatus) {
		this.paymentPoints = newPaymentPoints;
		this.bookReceived = newBookReceived;
		this.startDate = newStartDate;
		this.completionDate = newCompletionDate;
		this.transactionStatus = newTransactionStatus;
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
	
	public int getPaymentPoints() {
		return paymentPoints;
	}
	public void setPaymentPoints(int paymentPoints) {
		this.paymentPoints = paymentPoints;
	}
	
	public boolean getBookReceived() {
		return bookReceived;
	}
	public void setBookReceived(boolean bookReceived) {
		this.bookReceived = bookReceived;
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
	
	public static TransactionDTO returnTransactionDTO(Transaction transaction) {
		return new TransactionDTO(transaction.getPaymentPoints(), transaction.getBookReceived(), 
				transaction.getStartDate(), transaction.getCompletionDate(), transaction.getTransactionStatus());
	}
	
	public static Transaction returnTransaction(TransactionDTO transactionDTO) {
		return new Transaction(transactionDTO.getPaymentPoints(), transactionDTO.getBookReceived(), 
				transactionDTO.getStartDate(), transactionDTO.getCompletionDate(), transactionDTO.getTransactionStatus());
	}

}
