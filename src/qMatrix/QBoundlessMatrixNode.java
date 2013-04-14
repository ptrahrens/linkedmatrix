package qMatrix;

public class QBoundlessMatrixNode<T> extends QMatrixNode<T>{
	QBoundlessMatrixNode(QMatrix<T> matrix, QRow<T> row, QColumn<T> column, T item, QMatrixNode<T> up, QMatrixNode<T> down, QMatrixNode<T> left, QMatrixNode<T> right){
		super(matrix, row, column, item, up, down, left, right);
	}
	@Override
	public QMatrixNode<T> up() throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
		if(!this.up.isValidNode()){
			return this.up.up;
		}
		return this.up;
	}
	@Override
	public QMatrixNode<T> down() throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
		if(!this.down.isValidNode()){
			return this.down.down;
		}
		return this.down;
	}
	@Override
	public QMatrixNode<T> left() throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
		if(!this.left.isValidNode()){
			return this.left.left;
		}
		return this.left;
	}
	@Override
	public QMatrixNode<T> right() throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
		if(!this.right.isValidNode()){
			return this.right.right;
		}
		return this.right;
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
