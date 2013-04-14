package tilePanel;

import java.awt.event.MouseEvent;

import qMatrix.InvalidNodeException;

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
			try{
			  for(int i = 0; i < 8; i++){
			    if(((RainbowLife) this.node.neighbor(i).item()).isAlive){
			      total = total.add(((RainbowLife) this.node.neighbor(i).item()).liveColor);
			    }
			  }
			}catch(InvalidNodeException e){
			  System.err.print(e);
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
