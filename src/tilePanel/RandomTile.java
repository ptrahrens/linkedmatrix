package tilePanel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Random;

@SuppressWarnings("serial")
public class RandomTile extends Tile{
	protected Random rn;
	
	public RandomTile(){
		this.rn = new Random();
		this.setBackground(new Color(this.rn.nextInt(255), this.rn.nextInt(255), this.rn.nextInt(255)));
	}
	
	@Override
	public void mouseClicked(MouseEvent me){
		this.setBackground(new Color(this.rn.nextInt(255), this.rn.nextInt(255), this.rn.nextInt(255)));
	}
	
	@Override
	public void mouseEntered(MouseEvent me){
		this.setBackground(new Color(this.rn.nextInt(255), this.rn.nextInt(255), this.rn.nextInt(255)));
	}
	
	@Override
	public void calculate() {}

	@Override
	public void update() {
		this.setBackground(new Color(this.rn.nextInt(255), this.rn.nextInt(255), this.rn.nextInt(255)));
	}
	
	@Override
	public void reset() {}
}
