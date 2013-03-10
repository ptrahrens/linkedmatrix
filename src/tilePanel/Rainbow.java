package tilePanel;

public class Rainbow {
	protected NumericColor[] rainbow;
	protected NumericColor currentColor;
	protected int currentColorIndex;
	public Rainbow(NumericColor[] rainbow){
		this.rainbow = rainbow;
		this.currentColorIndex = 0;
		this.newColor();
	}
	public void newColor(){
		this.currentColorIndex += 1;
		this.currentColorIndex %= this.rainbow.length;
		this.currentColor = this.rainbow[this.currentColorIndex];
	}
	public NumericColor getColor(){
		return this.currentColor;
	}
	public int getLength(){
		return rainbow.length;
	}
}
