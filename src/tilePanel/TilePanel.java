package tilePanel;

import java.awt.*;

import java.util.*;
import qMatrix.*;

@SuppressWarnings("serial")
public abstract class TilePanel extends Panel{

	protected int tileWidth;
	protected int tileHeight;
	protected QMatrix<Tile> tiles;
	private boolean initialized = false;
	
	protected abstract Tile newTile();
	
	protected abstract QMatrix<Tile> newMatrix();
	
	public TilePanel(int tileWidth, int tileHeight){
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.setLayout(new GridLayout(tileWidth, tileHeight));
	}
	
	public boolean isInitialized(){
		return this.initialized;
	}
		
	public void init(){
		this.tiles = this.newMatrix();
		ArrayList<Tile> newRow;
		Tile newTile;
		for(int i = 0; i < tileHeight; i++){
			newRow = new ArrayList<Tile>();
			for(int j = 0; j < tileWidth; j++){
				newTile = this.newTile();
				newRow.add(newTile);
				this.add(newTile);
			}
			this.tiles.insertRowBottom(newRow);
		}
		this.initialized = true;
	}
	
	public void calculateTiles(){
		for(Tile tile: this.tiles){
			tile.calculate();
		}
	}
	
	public void updateTiles(){
		for(Tile tile: this.tiles){
			tile.update();
		}
	}
	
	public void resetTiles(){
		for(Tile tile: this.tiles){
			tile.reset();
		}
	}
}
