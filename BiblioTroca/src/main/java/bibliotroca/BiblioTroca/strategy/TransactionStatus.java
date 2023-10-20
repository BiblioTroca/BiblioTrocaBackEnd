package bibliotroca.BiblioTroca.strategy;

public enum TransactionStatus {
	PENDING("PENDENTE"), 
	CONCLUDED("CONCLUÍDO"),
	CANCELLED("CANCELADO");
	
	private final String transactionStatus;
	
	private TransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	public String getTransactionStatus() {
		return this.transactionStatus;
	}
	
	public static TransactionStatus getByTransactionStatus(String transactionStatus) {
		return TransactionStatus.valueOf(transactionStatus);
	}
	
	/*
	public String getStatusText() {
        switch (transactionStatus) {
            case Pending:
                return "pendente";
            case Concluded:
                return "CONCLUÍDA";
            case Cancelled:
                return "CANCELADA";
            default:
                return null;
        }
    }
    */
}
