package tilePanel;

import java.awt.event.MouseEvent;

import qMatrix.InvalidNodeException;

@SuppressWarnings("serial")
public class PropagationTile extends Tile {

	public NumericColor additiveColor;
	protected NumericColor color;
	protected Rainbow colorGenerator;
	protected double propagation;
	protected double evaporation;
	protected double drop;
	

	
	public PropagationTile(double propagation, double evaporation, double drop, Rainbow colorGenerator){
		this.propagation = propagation;
		this.evaporation = evaporation;
		this.drop = drop;
		this.colorGenerator = colorGenerator;
		this.color = new NumericColor();
		this.additiveColor = new NumericColor();
		this.setColor(this.color);
	}

	@Override
	public void update() {
		this.color = this.color.add(this.additiveColor);
		this.setColor(this.color);
		this.additiveColor = new NumericColor();
	}

	@Override
	public void calculate() {
	  double x;
	  try{
	    for(int i = 0; i < 8; i++){
	      if(i % 2 == 0){
	        x = this.propagation;
	      }else{
	        x = this.propagation / PropagationPanel.diagonalLength;
	      }
	      ((PropagationTile) this.node.neighbor(i).item()).additiveColor = 
	          ((PropagationTile) this.node.neighbor(i).item()).additiveColor.add(this.color.multiply(x));
	    }
	  }catch(InvalidNodeException e){
	    System.err.print(e);
	  }
		this.color = this.color.multiply(this.evaporation);
	}

	@Override
	public void reset() {
		this.color = new NumericColor();
		this.setColor(this.color);
	}
	
	@Override
	public void mousePressed(MouseEvent me){
		this.colorGenerator.newColor();
		this.color = this.colorGenerator.getColor().multiply(this.drop);
		this.setColor(this.color);
	}
	@Override
	public void mouseEntered(MouseEvent me){
		this.color = this.colorGenerator.getColor().multiply(this.drop);
		this.setColor(this.color);

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
