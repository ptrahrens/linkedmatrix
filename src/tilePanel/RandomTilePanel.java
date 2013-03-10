package tilePanel;

import qMatrix.QMatrix;

@SuppressWarnings("serial")
public class RandomTilePanel extends TilePanel {
	
	public RandomTilePanel(int tileWidth, int tileHeight){
		super(tileWidth, tileHeight);
	}
	
	@Override
	public Tile newTile(){
		return new RandomTile();
	}
	
	@Override
	protected QMatrix<Tile> newMatrix() {
		return new QMatrix<Tile>();
	}
}
