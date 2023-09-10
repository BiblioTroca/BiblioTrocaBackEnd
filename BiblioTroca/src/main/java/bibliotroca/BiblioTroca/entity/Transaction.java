package bibliotroca.BiblioTroca.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import bibliotroca.BiblioTroca.strategy.TransactionStatus;

@Document(collection="Transacoes")
public class Transaction {
	
	@Id
	private String id;	
	//@NotBlank ???
	private String seller;	
	//@NotBlank ???
	private String buyer;
	//@NotNull ???
	private int paymentPoints;	
	//@Getter ???
	private boolean bookReceived;
	//@Getter ???
	private boolean pointsTransferred;
	@CreatedDate
	private LocalDateTime startDate;	
	@CreatedDate
	private LocalDateTime completionDate;
	
	//@NotBlank ???
	private TransactionStatus transactionStatus;
	private User user;
	private Book book;
	
	public Transaction() { }
	
	public Transaction(String seller, String buyer, int paymentPoints, boolean bookReceived, boolean pointsTransferred,
			LocalDateTime startDate, LocalDateTime completionDate, TransactionStatus transactionStatus, User user, Book book) {
		this.seller = seller;
		this.buyer = buyer;
		this.paymentPoints = paymentPoints;
		this.bookReceived = bookReceived;
		this.pointsTransferred = pointsTransferred;
		this.startDate = startDate;
		this.completionDate = completionDate;
		this.transactionStatus = transactionStatus;
		this.user = user;
		this.book = book;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
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
	
	public boolean getPointsTransferred() {
		return pointsTransferred;
	}
	public void setPointsTransferred(boolean pointsTransferred) {
		this.pointsTransferred = pointsTransferred;
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
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

}