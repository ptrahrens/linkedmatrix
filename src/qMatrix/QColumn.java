package qMatrix;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QColumn<E> implements Iterable<E>{
	protected QMatrixNode<E> head;
	protected QMatrix<E> matrix;
	QColumn(QMatrix<E> matrix, QMatrixNode<E> columnSentinel){
	  this.matrix = matrix;
		this.head = columnSentinel;
	}
	public boolean isValidColumn(){
	  return this.matrix != null;
	}
	
  protected QColumn<E> insertLeft(Iterable<E> newColumnItems) throws InvalidColumnException{
    if(!this.isValidColumn()){
      throw new InvalidColumnException();
    }
    QMatrixNode<E> current;
    this.head.left.right = this.matrix.newNode(null, this.head.row, null, null, null, null, this.head.left, this.head);
    this.head.left = this.head.left.right;
    this.head.left.column = new QColumn<E>(this.matrix, this.head.left);
    current = this.head.left;
    Iterator<E> newColumnIterator = newColumnItems.iterator();
    while(newColumnIterator.hasNext()){
      current.down = this.matrix.newNode(this.matrix, current.right.down.row, current.column, newColumnIterator.next(), current, this.head.left, current.left.down, current.right.down);
      current.right.down.left = current.down;
      current.left.down.right = current.down;
      if(!newColumnIterator.hasNext()){
        this.head.left.up = current.down;
      }
      current = current.down;
    }
    assert current.down == this.head.left : "Inconsistent column length.";
    this.matrix.n++;
    return this.head.left.column;
  }
  
	protected QColumn<E> insertRight(Iterable<E> newColumnItems) throws InvalidColumnException{
    if(!this.isValidColumn()){
      throw new InvalidColumnException();
    }
    QMatrixNode<E> current;
    this.head.right.left = this.matrix.newNode(null, this.head.row, null, null, null, null, this.head, this.head.right);
    this.head.right = this.head.right.left;
    this.head.right.column = new QColumn<E>(this.matrix, this.head.right);
    current = this.head.right;
    Iterator<E> newColumnIterator = newColumnItems.iterator();
    while(newColumnIterator.hasNext()){
      current.down = this.matrix.newNode(this.matrix, current.left.down.row, current.column, newColumnIterator.next(), current, this.head.right, current.left.down, current.right.down);
      current.left.down.right = current.down;
      current.right.down.left = current.down;
      if(!newColumnIterator.hasNext()){
        this.head.right.up = current.down;
      }
      current = current.down;
    }
    assert current.down == this.head.right : "Inconsistent column length.";
    this.matrix.n++;
    return this.head.right.column;
  }
	
	public void remove() throws InvalidColumnException{
	  if(!this.isValidColumn()){
	    throw new InvalidColumnException();
	  }
	  QMatrixNode<E> current;
	  QMatrixNode<E> next = this.head.down;
	  do{
      current = next;
	    current.left.right = current.right;
	    current.right.left = current.left;
	    next = current.down;
	    current.up = null;
	    current.down = null;
	    current.left = null;
	    current.right = null;
	    current.item = null;
	    current.matrix = null;
	    current.column = null;
	    current.row = null;
	  }while(current != this.head);
	  this.matrix.n--;
	  if(this.matrix.n == 0){
	    while(this.matrix.head.down != this.matrix.head){
	      try {
          this.matrix.head.down.row.remove();
        } catch (InvalidRowException e) {
          e.printStackTrace();
        }
	      this.matrix.m--;
	    }
	  }
	  this.matrix = null;
	  this.head = null;
	}

  public class QColumnIterator<F> implements Iterator<F>{
    protected QMatrixNode<F> head;
    protected QMatrixNode<F> current;
    protected boolean canRemove;
    public QColumnIterator(QColumn<F> column){
      this.head = column.head;
      this.current = column.head.down;
      this.canRemove = false;
    }
    @Override
    public boolean hasNext() {
      return this.current != this.head;
    }

    @Override
    public F next() {
      if(!this.hasNext()){
        throw new NoSuchElementException();
      }
      this.current = this.current.down;
      this.canRemove = true;
      return this.current.up.item;
    }

    @Override
    public void remove() {
      if(this.canRemove){
        try {
          this.current.up.row.remove();
        } catch (InvalidRowException e) {
          e.printStackTrace();
        }
        this.canRemove = false;
      }else{
        throw new IllegalStateException();
      }
    }
    
  }
  @Override
  public Iterator<E> iterator(){
    return new QColumnIterator<E>(this);
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
