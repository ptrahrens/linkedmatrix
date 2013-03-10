package tilePanel;
/**
 * The NumericColor class is a representation of rgb color in a way that can be manipulated like a number.
 * @author peter
 *
 */
public class NumericColor {
	protected double red;
	protected double green;
	protected double blue;
	/**
	 * Create a new NumericColor object. All parameters should be in [0.0,1.0].
	 * Greater or lesser values may be used, but be aware that the getter methods
	 * will constrict values to the range [0.0,1.0].
	 * @param red Amount of red ([0.0,1.0])
	 * @param green Amount of green ([0.0,1.0])
	 * @param blue Amount of blue ([0.0,1.0])
	 */
	public NumericColor(double red, double green, double blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	/**
	 * Create a new NumericColor object, representing black.
	 */
	public NumericColor(){
		this.red = 0;
		this.green = 0;
		this.blue = 0;
	}
	protected double constrict(double x){
		if(x < 0.0){
			return 0;
		}else if(x > 1.0){
			return 1;
		}else{
			return x;
		}
	}

	/**
	 * 
	 * @return the amount of red in this NumericColor object, constricted to [0,1]
	 */
	public double getRed(){
		return this.constrict(this.red);
	}
	/**
	 * 
	 * @return the amount of green in this NumericColor object, constricted to [0,1]
	 */
	public double getGreen(){
		return this.constrict(this.green);
	}
	/**
	 * 
	 * @return the amount of blue in this NumericColor object, constricted to [0,1]
	 */
	public double getBlue(){
		return this.constrict(this.blue);
	}

	public NumericColor add(NumericColor other){
		return new NumericColor(this.red + other.red, this.green + other.green, this.blue + other.blue);
	}
	public NumericColor multiply(double x){
		return new NumericColor(this.red * x, this.green * x, this.blue * x);
	}
}
