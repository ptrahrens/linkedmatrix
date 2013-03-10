package tilePanel;

import java.awt.*;
import java.awt.event.*;

import qMatrix.*;

@SuppressWarnings("serial")
public abstract class Tile extends Panel implements QMatrixNodeAware<Tile>, MouseListener{
	
	protected QMatrixNode<Tile> node;
	
	public Tile(){
		this.addMouseListener(this);
	}
	
	public abstract void update();
	
	public abstract void calculate();
	
	public abstract void reset();
	
	public void setColor(NumericColor color){
		this.setBackground(new Color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue()));
	}
	
	@Override
	public void setOMatrixNode(QMatrixNode<Tile> qMatrixNode) {
		this.node = qMatrixNode;
	}
	
	@Override
	public QMatrixNode<Tile> getOMatrixNode() {
		return this.node;
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
        MouseListener[] ml = this.getParent().getMouseListeners();
        for (MouseListener l : ml) {
            l.mouseClicked(me);
        }
	}

	@Override
	public void mouseEntered(MouseEvent me) {
        MouseListener[] ml = this.getParent().getMouseListeners();
        for (MouseListener l : ml) {
            l.mouseEntered(me);
        }
	}

	@Override
	public void mouseExited(MouseEvent me) {
        MouseListener[] ml = this.getParent().getMouseListeners();
        for (MouseListener l : ml) {
            l.mouseExited(me);
        }
	}

	@Override
	public void mousePressed(MouseEvent me) {
        MouseListener[] ml = this.getParent().getMouseListeners();
        for (MouseListener l : ml) {
            l.mousePressed(me);
        }
	}

	@Override
	public void mouseReleased(MouseEvent me) {
        MouseListener[] ml = this.getParent().getMouseListeners();
        for (MouseListener l : ml) {
            l.mouseReleased(me);
        }
	}
}
