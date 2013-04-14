package tilePanel;

import java.awt.event.MouseEvent;

import qMatrix.InvalidNodeException;

@SuppressWarnings("serial")
public class RainbowLife extends Life{
	protected Rainbow colorGenerator;
	protected int numClicks;
	
	public RainbowLife(Rainbow colorGenerator){
		super();
		this.colorGenerator = colorGenerator;
		this.liveColor = colorGenerator.getColor();
		this.numClicks = 0;
		this.updateColor();
	}
	
	public void calculateColor() {
		if(!this.isAlive && this.willBeAlive){
			NumericColor total = new NumericColor();
			try{
			  for(int i = 0; i < 8; i++){
			    if(((RainbowLife) this.node.neighbor(i).item()).isAlive){
			      total = total.add(((RainbowLife) this.node.neighbor(i).item()).liveColor);
			    }
			  }
			}catch(InvalidNodeException e){
			  System.err.print(e);
			}
			this.liveColor = total.multiply(1.0/this.numAliveNeighbors);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent me){
		if(this.isAlive){
			if(this.numClicks >= this.colorGenerator.getLength() - 1){
				this.numClicks = 0;
				this.isAlive = false;
			}else{
				this.numClicks++;
				this.colorGenerator.newColor();
				this.liveColor = this.colorGenerator.getColor();
			}
		}else{
			this.liveColor = this.colorGenerator.getColor();
			this.numClicks = 0;
			this.isAlive = true;
		}
		this.updateColor();
	}

	@Override
	public void mouseEntered(MouseEvent me){
		this.numClicks = this.colorGenerator.getLength() - 1;
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
