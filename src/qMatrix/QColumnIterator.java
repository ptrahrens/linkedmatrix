package qMatrix;

import java.util.Iterator;

public class QColumnIterator<T> implements Iterator<T> {
	private QMatrixSentinel<T> columnSentinel;
	private QMatrixNode<T> node;
	public QColumnIterator(QMatrixSentinel<T> columnSentinel){
		this.columnSentinel = columnSentinel;
		this.node = columnSentinel;
	}
	@Override
	public boolean hasNext(){
		return this.node.down != this.columnSentinel;
	}
	@Override
	public T next(){
		if(this.node.down == this.columnSentinel){
			return null;
		}
		this.node = this.node.down;
		return this.node.item;
	}
	@Override
	public void remove(){}
}
