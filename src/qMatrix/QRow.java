package qMatrix;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QRow<E> implements Iterable<E>{
  protected QMatrixNode<E> head;
  protected QMatrix<E> matrix;
  QRow(QMatrix<E> matrix, QMatrixNode<E> rowSentinel){
    this.matrix = matrix;
    this.head = rowSentinel;
  }
  public boolean isValidRow(){
    return this.matrix != null;
  }
  
  protected QRow<E> insertUp(Iterable<E> newRowItems) throws InvalidRowException{
    if(!this.isValidRow()){
      throw new InvalidRowException();
    }
    QMatrixNode<E> current;
    this.head.up.down = this.matrix.newNode(null, null, this.head.column, null, this.head.up, this.head, null, null);
    this.head.up = this.head.up.down;
    this.head.up.row = new QRow<E>(this.matrix, this.head.up);
    current = this.head.up;
    Iterator<E> newRowIterator = newRowItems.iterator();
    while(newRowIterator.hasNext()){
      current.right = this.matrix.newNode(this.matrix, current.row, current.down.right.column, newRowIterator.next(), current.up.right, current.down.right, current, this.head.up);
      current.down.right.up = current.right;
      current.up.right.down = current.right;
      if(!newRowIterator.hasNext()){
        this.head.up.left = current.right;
      }
      current = current.right;
    }
    assert current.right == this.head.up : "Inconsistent row length.";
    this.matrix.m++;
    return this.head.up.row;
  }
  protected QRow<E> insertDown(Iterable<E> newRowItems) throws InvalidRowException{
    if(!this.isValidRow()){
      throw new InvalidRowException();
    }
    QMatrixNode<E> current;
    this.head.down.up = this.matrix.newNode(null, null, this.head.column, null, this.head, this.head.down, null, null);
    this.head.down = this.head.down.up;
    this.head.down.row = new QRow<E>(this.matrix, this.head.down);
    current = this.head.down;
    Iterator<E> newRowIterator = newRowItems.iterator();
    while(newRowIterator.hasNext()){
      current.right = this.matrix.newNode(this.matrix, current.row, current.up.right.column, newRowIterator.next(), current.up.right, current.down.right, current, this.head.down);
      current.up.right.down = current.right;
      current.down.right.up = current.right;
      if(!newRowIterator.hasNext()){
        this.head.down.left = current.right;
      }
      current = current.right;
    }
    assert current.right == this.head.down : "Inconsistent row length.";
    this.matrix.m++;
    return this.head.down.row;
  }
  
  public void remove() throws InvalidRowException{
    if(!this.isValidRow()){
      throw new InvalidRowException();
    }
    QMatrixNode<E> current;
    QMatrixNode<E> next = this.head.right;
    do{
      current = next;
      current.down.up = current.up;
      current.up.down = current.down;
      next = current.right;
      current.up = null;
      current.down = null;
      current.left = null;
      current.right = null;
      current.item = null;
      current.matrix = null;
      current.column = null;
      current.row = null;
    }while(current != this.head);
    this.matrix.m--;
    if(this.matrix.m == 0){
      while(this.matrix.head.right != this.matrix.head){
        try {
          this.matrix.head.right.column.remove();
        } catch (InvalidColumnException e) {
          e.printStackTrace();
        }
        this.matrix.n--;
      }
    }
    this.matrix = null;
    this.head = null;
  }
  
  public class QRowIterator<F> implements Iterator<F>{
    protected QMatrixNode<F> head;
    protected QMatrixNode<F> current;
    protected boolean canRemove;
    public QRowIterator(QRow<F> row){
      this.head = row.head;
      this.current = row.head.right;
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
      this.current = this.current.right;
      this.canRemove = true;
      return this.current.left.item;
    }

    @Override
    public void remove() {
      if(this.canRemove){
        try {
          this.current.left.column.remove();
        } catch (InvalidColumnException e) {
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
    return new QRowIterator<E>(this);
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

