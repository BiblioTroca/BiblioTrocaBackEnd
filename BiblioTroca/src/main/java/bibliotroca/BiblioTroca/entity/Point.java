package bibliotroca.BiblioTroca.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import jakarta.validation.constraints.NotBlank;

@Document(collection="Pontos")
public class Point {
	@Id
	private String id;
	private int walletPoints;
	@NotBlank(message="O CPF do usuário é obrigatório")
	private String userEmail;
	private String lastLogin;

	public Point() { }
	
	public Point(int walletPoints, String userEmail) {
		this.walletPoints = walletPoints;
		this.userEmail = userEmail;
		this.lastLogin = this.getCurrentDate();
	}
	
	public Point(int walletPoints) {
		this.walletPoints = walletPoints;
		this.lastLogin = this.getCurrentDate();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getWalletPoints() {
		return walletPoints;
	}
	public void setWalletPoints(int walletPoints) {
		this.walletPoints = walletPoints;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	
	private String getCurrentDate() {
	    return Instant.now().atZone(ZoneId.of("GMT-3")).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}
	
	public boolean increasePointsIfLoggedToday() {
		String today = this.getCurrentDate();
		if(this.lastLogin.equals(today)) {
			return false;
		}
		this.walletPoints++;
		this.lastLogin = today;
		return true;
	}
}