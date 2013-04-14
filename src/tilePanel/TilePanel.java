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

//Copyright (c) 2013, Peter Ahrens
//All rights reserved.
//
//Redistribution and use in source and binary forms, with or without
//modification, are permitted provided that the following conditions are met:
//
//Redistributions of source code must retain the above copyright notice, this
//list of conditions and the following disclaimer.
//Redistributions in binary form must reproduce the above copyright notice,
//this list of conditions and the following disclaimer in the documentation
//and/or other materials provided with the distribution.
//Neither the name of linkedMatrix nor the names of its contributors may be
//used to endorse or promote products derived from this software without
//specific prior written permission.
//
//THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
//AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
//IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
//ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
//LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
//CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
//SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
//INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
//CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
//ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
//POSSIBILITY OF SUCH DAMAGE.
