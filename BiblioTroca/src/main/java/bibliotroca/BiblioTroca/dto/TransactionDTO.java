package bibliotroca.BiblioTroca.dto;

import java.time.LocalDateTime;

import bibliotroca.BiblioTroca.entity.Book;
import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.strategy.TransactionStatus;

public class TransactionDTO {
	
	private String seller;
	private String buyer;
	private int paymentPoints;
	private boolean bookReceived;
	private boolean pointsTransferred;
	private LocalDateTime startDate;
	private LocalDateTime completionDate;
	
	private TransactionStatus transactionStatus;
	private User user;
	private Book book;
	
	public TransactionDTO() { }
	
	public TransactionDTO(String newSeller, String newBuyer, int newPaymentPoints, boolean newBookReceived, boolean newPointsTransferred,
			LocalDateTime newStartDate, LocalDateTime newCompletionDate, TransactionStatus newTransactionStatus, 
			User newUser, Book newBook) {
		this.seller = newSeller;
		this.buyer = newBuyer;
		this.paymentPoints = newPaymentPoints;
		this.bookReceived = newBookReceived;
		this.pointsTransferred = newPointsTransferred;
		this.startDate = newStartDate;
		this.completionDate = newCompletionDate;
		this.transactionStatus = newTransactionStatus;
		this.user = newUser;
		this.book = newBook;
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
	
	public static TransactionDTO returnTransactionDTO(Transaction transaction) {
		return new TransactionDTO(transaction.getSeller(), transaction.getBuyer(), transaction.getPaymentPoints(),
				transaction.getBookReceived(), transaction.getPointsTransferred(), transaction.getStartDate(),
				transaction.getCompletionDate(), transaction.getTransactionStatus(), transaction.getUser(), transaction.getBook());
	}
	
	public static Transaction returnTransaction(TransactionDTO transactionDTO) {
		return new Transaction(transactionDTO.getSeller(), transactionDTO.getBuyer(), transactionDTO.getPaymentPoints(),
				transactionDTO.getBookReceived(), transactionDTO.getPointsTransferred(), transactionDTO.getStartDate(),
				transactionDTO.getCompletionDate(), transactionDTO.getTransactionStatus(), 
				transactionDTO.getUser(), transactionDTO.getBook());
	}

}
