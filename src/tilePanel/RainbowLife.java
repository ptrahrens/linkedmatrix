package tilePanel;

import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class RainbowLife extends Life{
	protected Rainbow colorGenerator;
	protected int numClicks;
	
	public RainbowLife(Rainbow colorGenerator){
		super();
		this.colorGenerator = colorGenerator;
		this.liveColor = colorGenerator.getColor();
		this.numClicks = 0;
		this.updateColor();
	}
	
	public void calculateColor() {
		if(!this.isAlive && this.willBeAlive){
			NumericColor total = new NumericColor();
			if(((RainbowLife) this.node.up().item).isAlive){
				total = total.add(((RainbowLife) this.node.up().item).liveColor);
			}
			if(((RainbowLife) this.node.right().item).isAlive){
				total = total.add(((RainbowLife) this.node.right().item).liveColor);
			}
			if(((RainbowLife) this.node.down().item).isAlive){
				total = total.add(((RainbowLife) this.node.down().item).liveColor);
			}
			if(((RainbowLife) this.node.left().item).isAlive){
				total = total.add(((RainbowLife) this.node.left().item).liveColor);
			}
			if(((RainbowLife) this.node.up().right().item).isAlive){
				total = total.add(((RainbowLife) this.node.up().right().item).liveColor);
			}
			if(((RainbowLife) this.node.right().down().item).isAlive){
				total = total.add(((RainbowLife) this.node.right().down().item).liveColor);
			}
			if(((RainbowLife) this.node.down().left().item).isAlive){
				total = total.add(((RainbowLife) this.node.down().left().item).liveColor);
			}
			if(((RainbowLife) this.node.left().up().item).isAlive){
				total = total.add(((RainbowLife) this.node.left().up().item).liveColor);
			}
			this.liveColor = total.multiply(1.0/this.numAliveNeighbors);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent me){
		if(this.isAlive){
			if(this.numClicks >= this.colorGenerator.getLength() - 1){
				this.numClicks = 0;
				this.isAlive = false;
			}else{
				this.numClicks++;
				this.colorGenerator.newColor();
				this.liveColor = this.colorGenerator.getColor();
			}
		}else{
			this.liveColor = this.colorGenerator.getColor();
			this.numClicks = 0;
			this.isAlive = true;
		}
		this.updateColor();
	}

	@Override
	public void mouseEntered(MouseEvent me){
		this.numClicks = this.colorGenerator.getLength() - 1;
	}
	
}
