package tilePanel;


@SuppressWarnings("serial")
public class RainbowLifePanel extends LifePanel{
	protected Rainbow colorGenerator;
	public RainbowLifePanel(int tileWidth, int tileHeight, NumericColor[] liveColors){
		super(tileWidth, tileHeight);
		this.colorGenerator = new Rainbow(liveColors);
	}
	@Override
	protected Tile newTile(){
		return new RainbowLife(this.colorGenerator);
	}
	@Override
	public void calculateTiles(){
		super.calculateTiles();
		for(Tile tile:this.tiles){
			((RainbowLife) tile).calculateColor();
		}
	}
}
