package tilePanel;

import qMatrix.*;

@SuppressWarnings("serial")
public class PropagationPanel extends TilePanel{

	protected double propagation;
	protected double evaporation;
	protected double drop;
	public Rainbow colorGenerator;
	public Boolean mouseDown;
	protected static final double diagonalLength = 1.41421356237;
	
	public PropagationPanel(int tileWidth, int tileHeight, double propagationFactor, double evaporationFactor, double dropFactor, NumericColor[] dropColors){
		super(tileWidth, tileHeight);
		this.propagation = propagationFactor/(4.0 + 4.0 / PropagationPanel.diagonalLength);
		this.evaporation = (1.0 - propagationFactor) * evaporationFactor;
		this.drop = dropFactor;
		this.colorGenerator = new Rainbow(dropColors);
	}
	
	@Override
	public Tile newTile(){
		return new PropagationTile(propagation, evaporation, drop, colorGenerator);
	}
	
	@Override
	protected QMatrix<Tile> newMatrix() {
		return new QBoundlessMatrix<Tile>();
	}
}
