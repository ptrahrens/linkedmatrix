package tilePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.applet.*;

//TODO comment the code everywhere
//TODO fix iterators on matricies
//TODO finish

@SuppressWarnings("serial")
public class TileApplet extends Applet implements ItemListener, Runnable, ActionListener{
	protected Panel mainPanel;
	protected CardLayout mainPanelLayout;
	
	protected Speed speed;
	
	protected Thread updateThread;
	protected boolean running;
	protected boolean updating;
	
	protected TilePanel currentTilePanel;
	protected RandomTilePanel randomTilePanel;
	protected LifePanel lifePanel;
	protected RainbowLifePanel rainbowLifePanel;
	protected PropagationPanel propagationPanel;

	//defaults
	protected final int speedInputSize = 7;
	protected final int maxSpeed = 1000;
	protected final int initSpeed = 900;
	protected final int numRows = 24;
	protected final int numColumns = 24;
	protected String currentTilePanelString = "propagation";

	protected final double dropFactor = 2;
	protected final double propagationFactor = 0.9;
	protected final double evaporationFactor = 0.7;
	protected final NumericColor[] propagationColors = {new NumericColor(1,0,0),
														new NumericColor(0,1,0), 
														new NumericColor(0,0,1), 
														new NumericColor(0,1,1), 
														new NumericColor(1,0,1), 
														new NumericColor(1,1,0)};

	protected final NumericColor[] rainbowLifeColors = {new NumericColor(1,0,0),
														new NumericColor(0,1,0), 
														new NumericColor(0,0,1)};
	
	@Override
	public void init(){
		//Create input panel
		Panel inputPanel = new Panel();
		inputPanel.setBackground(Color.BLACK);
		Button startButton = new Button("start");
		startButton.addActionListener(this);
		Button stopButton = new Button("stop");
		stopButton.addActionListener(this);
		Button resetButton = new Button("reset");
		resetButton.addActionListener(this);
		Choice tileChooser = new Choice();
		tileChooser.addItemListener(this);
		Label speedLabel = new Label("speed:");
		speedLabel.setForeground(Color.WHITE);
		TextField speedInput = new TextField(new Integer(this.initSpeed).toString(), this.speedInputSize);
		this.speed = new Speed(speedInput, this.initSpeed, this.maxSpeed);
		speedInput.addActionListener(this.speed);
		inputPanel.add(startButton);
		inputPanel.add(stopButton);
		inputPanel.add(resetButton);
		inputPanel.add(tileChooser);
		inputPanel.add(speedLabel);
		inputPanel.add(speedInput);
		
		//Create main panel
		this.mainPanel = new Panel();
		this.mainPanelLayout = new CardLayout();
		this.mainPanel.setLayout(this.mainPanelLayout);

		//Add various types of TilePanels to the main panel
		//RandomTilePanel
		tileChooser.add("random");
		this.randomTilePanel = new RandomTilePanel(this.numRows, this.numColumns);
		this.mainPanel.add(this.randomTilePanel, "random");
		//LifePanel
		tileChooser.add("life");
		this.lifePanel = new LifePanel(this.numRows, this.numColumns);
		this.mainPanel.add(this.lifePanel, "life");
		//RainbowLifePanel
		tileChooser.add("rainbow life");
		this.rainbowLifePanel = new RainbowLifePanel(this.numRows, this.numColumns, this.rainbowLifeColors);
		this.mainPanel.add(this.rainbowLifePanel, "rainbow life");
		//PropagationPanel
		tileChooser.add("propagation");
		this.propagationPanel = new PropagationPanel(this.numRows, this.numColumns, this.propagationFactor, this.evaporationFactor, this.dropFactor, this.propagationColors);
		this.mainPanel.add(this.propagationPanel, "propagation");
		
		//this.mainPanelLayout.show(this.mainPanel, this.currentPanel);
		tileChooser.select(this.currentTilePanelString);
		this.choose(this.currentTilePanelString);
		
		//Add input panel and main panel to the applet
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		this.add(inputPanel, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
		
		//Make the update thread
		this.updateThread = new Thread(this);
		this.updating = false;
	}
	
	public void choose(String newItem){
		assert newItem.equals("random") || newItem.equals("life") || newItem.equals("rainbow life") || newItem.equals("propagation");
		this.updating = false;
		if(newItem.equals("random")){
			this.currentTilePanel = this.randomTilePanel;
		}else if(newItem.equals("life")){
			this.currentTilePanel = this.lifePanel;
		}else if(newItem.equals("rainbow life")){
			this.currentTilePanel = this.rainbowLifePanel;
		}else if(newItem.equals("propagation")){
			this.currentTilePanel = this.propagationPanel;
		}
		if(!this.currentTilePanel.isInitialized()){
			this.currentTilePanel.init();
		}
		this.mainPanelLayout.show(this.mainPanel, newItem);
		this.currentTilePanelString = newItem;
		System.out.println(newItem);
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		Object selectedItem = ie.getItem();
		if(selectedItem.equals("random") || selectedItem.equals("life") || selectedItem.equals("rainbow life") || selectedItem.equals("propagation")){
			this.choose((String) selectedItem);
		}
	}
	
	@Override
	public void run() {
		while(this.running){
			if(this.updating){
				this.currentTilePanel.calculateTiles();
				this.currentTilePanel.updateTiles();
			}
            try{
                 Thread.sleep(this.speed.getWait());
            }
            catch (InterruptedException e){
                 System.out.println(e);
            }
		}
	}
	
	@Override
	public void start(){
		this.running = true;
		this.updateThread.start();
	}
	
	@Override
	public void stop(){
		this.running = false;
		try{
			this.updateThread.join();
		}
		catch (InterruptedException e){
			System.out.println(e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		if(action.equals("start")){
			this.updating = true;
		}else if(action.equals("stop")){
			this.updating = false;
		}else if(action.equals("reset")){
			this.updating = false;
			this.currentTilePanel.resetTiles();
		}
	}
	
}

class Speed implements ActionListener {

  private int speed;
  private int maxSpeed;
  private TextField in;

  public Speed(TextField in, int initialSpeed, int maxSpeed){
    this.speed = initialSpeed;
    this.maxSpeed = maxSpeed;
    this.in = in;
  }
  
  public int getWait(){
	  return this.maxSpeed - this.speed;
  }

  public void actionPerformed(ActionEvent ae) {
    String s = in.getText();
    int newSpeed = 0;
    try{
    	newSpeed = Integer.parseInt(s);
    }catch(NumberFormatException e){
    	in.setText("Invalid");
    }
    if(newSpeed <= 0){
    	in.setText("Invalid");
    }else if(newSpeed > this.maxSpeed){
    	in.setText("Too Big");
    }
    this.speed = newSpeed;
  }

}
