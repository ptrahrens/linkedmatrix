package tilePanel;

import java.awt.event.MouseEvent;

import qMatrix.InvalidNodeException;

@SuppressWarnings("serial")
public class PropagationTile extends Tile {

	public NumericColor additiveColor;
	protected NumericColor color;
	protected Rainbow colorGenerator;
	protected double propagation;
	protected double evaporation;
	protected double drop;
	

	
	public PropagationTile(double propagation, double evaporation, double drop, Rainbow colorGenerator){
		this.propagation = propagation;
		this.evaporation = evaporation;
		this.drop = drop;
		this.colorGenerator = colorGenerator;
		this.color = new NumericColor();
		this.additiveColor = new NumericColor();
		this.setColor(this.color);
	}

	@Override
	public void update() {
		this.color = this.color.add(this.additiveColor);
		this.setColor(this.color);
		this.additiveColor = new NumericColor();
	}

	@Override
	public void calculate() {
	  double x;
	  try{
	    for(int i = 0; i < 8; i++){
	      if(i % 2 == 0){
	        x = this.propagation;
	      }else{
	        x = this.propagation / PropagationPanel.diagonalLength;
	      }
	      ((PropagationTile) this.node.neighbor(i).item()).additiveColor = 
	          ((PropagationTile) this.node.neighbor(i).item()).additiveColor.add(this.color.multiply(x));
	    }
	  }catch(InvalidNodeException e){
	    System.err.print(e);
	  }
		this.color = this.color.multiply(this.evaporation);
	}

	@Override
	public void reset() {
		this.color = new NumericColor();
		this.setColor(this.color);
	}
	
	@Override
	public void mousePressed(MouseEvent me){
		this.colorGenerator.newColor();
		this.color = this.colorGenerator.getColor().multiply(this.drop);
		this.setColor(this.color);
	}
	@Override
	public void mouseEntered(MouseEvent me){
		this.color = this.colorGenerator.getColor().multiply(this.drop);
		this.setColor(this.color);

	}

}
