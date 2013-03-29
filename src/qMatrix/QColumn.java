package qMatrix;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QColumn<T> implements Iterable<T>{
	protected QMatrixNode<T> head;
	protected QMatrix<T> matrix;
	QColumn(QMatrix<T> matrix, QMatrixNode<T> columnSentinel){
	  this.matrix = matrix;
		this.head = columnSentinel;
	}
	public boolean isValidColumn(){
	  return this.matrix != null;
	}
	
  protected QColumn<T> insertLeft(Iterable<T> newColumnItems) throws InvalidColumnException{
    if(!this.isValidColumn()){
      throw new InvalidColumnException();
    }
    QMatrixNode<T> current;
    this.head.left.right = this.matrix.newNode(null, this.head.row, null, null, null, null, this.head.left, this.head);
    this.head.left = this.head.left.right;
    this.head.left.column = new QColumn<T>(this.matrix, this.head.left);
    current = this.head.left;
    Iterator<T> newColumnIterator = newColumnItems.iterator();
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
  
	protected QColumn<T> insertRight(Iterable<T> newColumnItems) throws InvalidColumnException{
    if(!this.isValidColumn()){
      throw new InvalidColumnException();
    }
    QMatrixNode<T> current;
    this.head.right.left = this.matrix.newNode(null, this.head.row, null, null, null, null, this.head, this.head.right);
    this.head.right = this.head.right.left;
    this.head.right.column = new QColumn<T>(this.matrix, this.head.right);
    current = this.head.right;
    Iterator<T> newColumnIterator = newColumnItems.iterator();
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

  public class QColumnIterator<U> implements Iterator<U>{
    protected QMatrixNode<U> head;
    protected QMatrixNode<U> current;
    public QColumnIterator(QColumn<U> column){
      this.head = column.head;
      this.current = column.head;
    }
    @Override
    public boolean hasNext() {
      return this.current.down != this.head;
    }

    @Override
    public U next() {
      if(!this.hasNext()){
        throw new NoSuchElementException();
      }
      this.current = this.current.down;
      return this.current.item;
    }

    @Override
    public void remove() {
      return; //TODO make this method remove the row the last element was in.
    }
    
  }
  @Override
  public Iterator<T> iterator(){
    return new QColumnIterator<T>(this);
  }
}
