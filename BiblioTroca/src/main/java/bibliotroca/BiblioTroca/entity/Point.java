package bibliotroca.BiblioTroca.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Entity;

@Document(collection="Points")
@Entity
public class Point {
	
	@Id
	private String id;
	private int walletPoints;	
	private User user;

	public Point() { }
	
	public Point(int walletPoints, User user) {
		this.walletPoints = walletPoints;
		this.user = user;
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
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}