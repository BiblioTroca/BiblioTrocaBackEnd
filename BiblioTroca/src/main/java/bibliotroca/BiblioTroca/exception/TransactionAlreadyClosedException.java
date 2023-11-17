package bibliotroca.BiblioTroca.exception;

public class TransactionAlreadyClosedException extends Exception {
	private static final long serialVersionUID = 1L;

    public TransactionAlreadyClosedException() {
        super(String.format("A transação já foi encerrada"), null, false, false);
    }
}
