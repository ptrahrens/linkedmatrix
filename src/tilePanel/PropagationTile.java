package tilePanel;

import java.awt.event.MouseEvent;

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
		((PropagationTile) this.node.up().item).additiveColor = 
				((PropagationTile) this.node.up().item).additiveColor.add(this.color.multiply(this.propagation));
		((PropagationTile) this.node.right().item).additiveColor = 
				((PropagationTile) this.node.right().item).additiveColor.add(this.color.multiply(this.propagation));
		((PropagationTile) this.node.down().item).additiveColor = 
				((PropagationTile) this.node.down().item).additiveColor.add(this.color.multiply(this.propagation));
		((PropagationTile) this.node.left().item).additiveColor = 
				((PropagationTile) this.node.left().item).additiveColor.add(this.color.multiply(this.propagation));
		((PropagationTile) this.node.up().right().item).additiveColor = 
				((PropagationTile) this.node.up().right().item).additiveColor.add(this.color.multiply(this.propagation / PropagationPanel.diagonalLength));
		((PropagationTile) this.node.right().down().item).additiveColor = 
				((PropagationTile) this.node.right().down().item).additiveColor.add(this.color.multiply(this.propagation / PropagationPanel.diagonalLength));
		((PropagationTile) this.node.down().left().item).additiveColor = 
				((PropagationTile) this.node.down().left().item).additiveColor.add(this.color.multiply(this.propagation / PropagationPanel.diagonalLength));
		((PropagationTile) this.node.left().up().item).additiveColor = 
				((PropagationTile) this.node.left().up().item).additiveColor.add(this.color.multiply(this.propagation / PropagationPanel.diagonalLength));

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
