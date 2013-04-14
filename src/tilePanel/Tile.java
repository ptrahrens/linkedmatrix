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
	public void setQMatrixNode(QMatrixNode<Tile> qMatrixNode) {
		this.node = qMatrixNode;
	}
	
	@Override
	public QMatrixNode<Tile> getQMatrixNode() {
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
