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
	private String userCpf;
	private String lastLogin;

	public Point() { }
	
	public Point(int walletPoints, String userCpf) {
		this.walletPoints = walletPoints;
		this.userCpf = userCpf;
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
	
	public String getUserCpf() {
		return userCpf;
	}
	public void setUserCpf(String userCpf) {
		this.userCpf = userCpf;
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
	
	@SuppressWarnings("unused")
	private void increasePointsIfLoggedToday() {
		String today = this.getCurrentDate();
		if(this.lastLogin != today) {
			this.walletPoints++;
			this.lastLogin = today;
		}
	}
}