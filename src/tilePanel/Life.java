package tilePanel;

import java.awt.event.MouseEvent;

import qMatrix.InvalidNodeException;

@SuppressWarnings("serial")
public class Life extends Tile{
	
	public boolean isAlive;
	protected boolean willBeAlive;
	protected int numAliveNeighbors;
	
	protected NumericColor liveColor;
	protected NumericColor deadColor;
	
	public Life(){
		this.isAlive = false;
		this.liveColor = new NumericColor(1.0, 1.0, 1.0);
		this.deadColor = new NumericColor();
		this.updateColor();
	}

	@Override
	public void mousePressed(MouseEvent me){
		this.isAlive = !this.isAlive;
		this.updateColor();
	}

	@Override
	public void calculate() {
		this.numAliveNeighbors = 0;
		try{
		  for(int i = 0; i < 8; i++){
		    this.numAliveNeighbors += ((Life) this.node.neighbor(i).item()).isAlive ? 1 : 0;
		  }
		}catch(InvalidNodeException e){
		  System.err.print(e);
		}
		if(this.isAlive){
			if(this.numAliveNeighbors < 2){
				this.willBeAlive = false;
			}else if(this.numAliveNeighbors <= 3){
				this.willBeAlive = true;
			}else{
				this.willBeAlive = false;
			}
		}else if(this.numAliveNeighbors == 3){
			this.willBeAlive = true;
		}else{
			this.willBeAlive = false;
		}
	}
	
	@Override
	public void update() {
		this.isAlive = this.willBeAlive;
		this.updateColor();
	}
	
	@Override
	public void reset() {
		this.isAlive = false;
		this.updateColor();
	}
	
	protected void updateColor(){
		if(this.isAlive){
			this.setColor(this.liveColor);
		}else{
			this.setColor(this.deadColor);
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
