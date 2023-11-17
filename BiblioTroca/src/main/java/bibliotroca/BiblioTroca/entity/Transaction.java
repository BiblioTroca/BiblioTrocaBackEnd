package bibliotroca.BiblioTroca.entity;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import bibliotroca.BiblioTroca.strategy.TransactionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection="Transacoes")
public class Transaction {
	
	@Id
	private String id;
	private Long registry;
	@NotBlank(message = "Campo não pode ser vazio.")
	private String sellerCpf;	
	@NotBlank(message = "Campo não pode ser vazio.")
	private String buyerCpf;
	@NotNull(message = "O número de registro do livro é obrigatório.")
	private Long bookRegistry;
	private String startDate;	
	private String completionDate;
	@Enumerated(EnumType.STRING)
	private TransactionStatus transactionStatus;
	
	public Transaction() { }
	
	public Transaction(String id, Long registry, String sellerCpf, String buyerCpf, Long bookRegistry, String startDate,
			String completionDate, TransactionStatus transactionStatus) {
		this.id = id;
		this.registry = registry;
		this.sellerCpf = sellerCpf;
		this.buyerCpf = buyerCpf;
		this.bookRegistry = bookRegistry;
		this.startDate = startDate;
		this.completionDate = completionDate;
		this.transactionStatus = transactionStatus;
	}
	
	public Transaction(Long registry, String sellerCpf, String buyerCpf, Long bookRegistry, String startDate,
			String completionDate, TransactionStatus transactionStatus) {
		this.registry = registry;
		this.sellerCpf = sellerCpf;
		this.buyerCpf = buyerCpf;
		this.bookRegistry = bookRegistry;
		this.startDate = startDate;
		this.completionDate = completionDate;
		this.transactionStatus = transactionStatus;
	}
	
	public Transaction(Long registry, String startDate, String completionDate, TransactionStatus transactionStatus) {
		this.registry = registry;
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
	
	public Long getRegistry() {
		return registry;
	}
	public void setRegistry(Long registry) {
		this.registry = registry;
	}
	
	public String getSellerCpf() {
		return sellerCpf;
	}
	public void setSellerCpf(String sellerCpf) {
		this.sellerCpf = sellerCpf;
	}
	
	public String getBuyerCpf() {
		return buyerCpf;
	}
	public void setBuyerCpf(String buyerCpf) {
		this.buyerCpf = buyerCpf;
	}
	
	public Long getBookRegistry() {
		return bookRegistry;
	}
	public void setBookRegistry(Long bookRegistry) {
		this.bookRegistry = bookRegistry;
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

	
	public String getCurrentDate() {
		return Instant.now().atZone(ZoneId.of("GMT-3")).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}
}