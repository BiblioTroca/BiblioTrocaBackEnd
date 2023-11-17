package bibliotroca.BiblioTroca.dto;

import bibliotroca.BiblioTroca.entity.Point;

public class PointDTO {
	
	private int walletPoints;
	
	public PointDTO() { }
	
	public PointDTO(int newWalletPoints) {
		this.walletPoints = newWalletPoints;
	}
	
	public int getWalletPoints() {
		return walletPoints;
	}
	public void setWalletPoints(int walletPoints) {
		this.walletPoints = walletPoints;
	}
	
	public static PointDTO returnPointDTO(Point point) {
		return new PointDTO(point.getWalletPoints());
	}
	
	public static Point returnPoint(PointDTO pointDTO) {
		return new Point(pointDTO.getWalletPoints());
	}
	
}
