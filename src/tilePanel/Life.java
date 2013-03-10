package tilePanel;

import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Life extends Tile{
	
	public boolean isAlive;
	protected boolean willBeAlive;
	protected int numAliveNeighbors;
	
	protected NumericColor liveColor;
	protected NumericColor deadColor;
	
	public Life(){
		this.isAlive = false;
		this.liveColor = new NumericColor(1.0, 1.0, 1.0);
		this.deadColor = new NumericColor();
		this.updateColor();
	}

	@Override
	public void mousePressed(MouseEvent me){
		this.isAlive = !this.isAlive;
		this.updateColor();
	}

	@Override
	public void calculate() {
		this.numAliveNeighbors = 0;
		this.numAliveNeighbors += ((Life) this.node.up().item).isAlive ? 1 : 0;
		this.numAliveNeighbors += ((Life) this.node.right().item).isAlive ? 1 : 0;
		this.numAliveNeighbors += ((Life) this.node.down().item).isAlive ? 1 : 0;
		this.numAliveNeighbors += ((Life) this.node.left().item).isAlive ? 1 : 0;
		this.numAliveNeighbors += ((Life) this.node.up().right().item).isAlive ? 1 : 0;
		this.numAliveNeighbors += ((Life) this.node.right().down().item).isAlive ? 1 : 0;
		this.numAliveNeighbors += ((Life) this.node.down().left().item).isAlive ? 1 : 0;
		this.numAliveNeighbors += ((Life) this.node.left().up().item).isAlive ? 1 : 0;
		if(this.isAlive){
			if(this.numAliveNeighbors < 2){
				this.willBeAlive = false;
			}else if(this.numAliveNeighbors <= 3){
				this.willBeAlive = true;
			}else{
				this.willBeAlive = false;
			}
		}else if(this.numAliveNeighbors == 3){
			this.willBeAlive = true;
		}else{
			this.willBeAlive = false;
		}
	}
	
	@Override
	public void update() {
		this.isAlive = this.willBeAlive;
		this.updateColor();
	}
	
	@Override
	public void reset() {
		this.isAlive = false;
		this.updateColor();
	}
	
	protected void updateColor(){
		if(this.isAlive){
			this.setColor(this.liveColor);
		}else{
			this.setColor(this.deadColor);
		}
	}
}
