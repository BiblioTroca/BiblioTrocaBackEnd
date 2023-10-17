package bibliotroca.BiblioTroca.dto;

import bibliotroca.BiblioTroca.entity.Point;
import bibliotroca.BiblioTroca.entity.User;

public class PointDTO {
	
	private int walletPoints;
	private User user;
	
	public PointDTO() { }
	
	public PointDTO(int newWalletPoints, User user) {
		this.walletPoints = newWalletPoints;
		this.user = user;
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
	
	public static PointDTO returnPointDTO(Point point, User user) {
		return new PointDTO(point.getWalletPoints(), point.getUser());
	}
	
	public static Point returnPoint(PointDTO pointDTO, User user) {
		return new Point(pointDTO.getWalletPoints(), pointDTO.getUser());
	}
	
}
