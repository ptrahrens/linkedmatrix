package tilePanel;

import qMatrix.*;

@SuppressWarnings("serial")
public class LifePanel extends TilePanel {

	public LifePanel(int tileWidth, int tileHeight){
		super(tileWidth, tileHeight);
	}
	
	@Override
	protected Tile newTile(){
		return new Life();
	}
	
	@Override
	protected QMatrix<Tile> newMatrix() {
		return new QBoundlessMatrix<Tile>();
	}
}
