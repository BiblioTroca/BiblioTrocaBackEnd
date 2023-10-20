package bibliotroca.BiblioTroca.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import bibliotroca.BiblioTroca.strategy.TransactionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Document(collection="Transacoes")
@Entity
public class Transaction {
	
	@Id
	private String id;	
	@NotBlank(message = "Campo não pode ser vazio.")
	private String seller;	
	@NotBlank(message = "Campo não pode ser vazio.")
	private String buyer;
	private Long book;
	@NotNull(message = "Campo não pode ser vazio.")
	private int paymentPoints;	
	private boolean bookReceived;
	@CreatedDate
	private LocalDateTime startDate;	
	@CreatedDate
	private LocalDateTime completionDate;
	@Enumerated(EnumType.STRING)
	private TransactionStatus transactionStatus;
	
	public Transaction() { }
	
	public Transaction(String id, String seller, String buyer, Long book, int paymentPoints, boolean bookReceived,
			LocalDateTime startDate, LocalDateTime completionDate, TransactionStatus transactionStatus) {
		this.id = id;
		this.seller = seller;
		this.buyer = buyer;
		this.book = book;
		this.paymentPoints = paymentPoints;
		this.bookReceived = bookReceived;
		this.startDate = startDate;
		this.completionDate = completionDate;
		this.transactionStatus = transactionStatus;
	}
	
	public Transaction(int paymentPoints, boolean bookReceived, LocalDateTime startDate, LocalDateTime completionDate, 
			TransactionStatus transactionStatus) {
		this.paymentPoints = paymentPoints;
		this.bookReceived = bookReceived;
		this.startDate = startDate;
		this.completionDate = completionDate;
		this.transactionStatus = transactionStatus;
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
	
	public Long getBook() {
		return book;
	}
	public void setBook(Long book) {
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

}