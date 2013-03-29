package qMatrix;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QRow<T> implements Iterable<T>{
  protected QMatrixNode<T> head;
  protected QMatrix<T> matrix;
  QRow(QMatrix<T> matrix, QMatrixNode<T> rowSentinel){
    this.matrix = matrix;
    this.head = rowSentinel;
  }
  public boolean isValidRow(){
    return this.matrix != null;
  }
  
  protected QRow<T> insertUp(Iterable<T> newRowItems) throws InvalidRowException{
    if(!this.isValidRow()){
      throw new InvalidRowException();
    }
    QMatrixNode<T> current;
    this.head.up.down = this.matrix.newNode(null, null, this.head.column, null, this.head.up, this.head, null, null);
    this.head.up = this.head.up.down;
    this.head.up.row = new QRow<T>(this.matrix, this.head.up);
    current = this.head.up;
    Iterator<T> newRowIterator = newRowItems.iterator();
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
  protected QRow<T> insertDown(Iterable<T> newRowItems) throws InvalidRowException{
    if(!this.isValidRow()){
      throw new InvalidRowException();
    }
    QMatrixNode<T> current;
    this.head.down.up = this.matrix.newNode(null, null, this.head.column, null, this.head, this.head.down, null, null);
    this.head.down = this.head.down.up;
    this.head.down.row = new QRow<T>(this.matrix, this.head.down);
    current = this.head.down;
    Iterator<T> newRowIterator = newRowItems.iterator();
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
  public class QRowIterator<U> implements Iterator<U>{
    protected QMatrixNode<U> head;
    protected QMatrixNode<U> current;
    public QRowIterator(QRow<U> row){
      this.head = row.head;
      this.current = row.head;
    }
    @Override
    public boolean hasNext() {
      return this.current.right != this.head;
    }

    @Override
    public U next() {
      if(!this.hasNext()){
        throw new NoSuchElementException();
      }
      this.current = this.current.right;
      return this.current.item;
    }

    @Override
    public void remove() {
      return; //TODO make this method remove the column the last element was in.
    }
    
  }
  @Override
  public Iterator<T> iterator(){
    return new QRowIterator<T>(this);
  }
}

