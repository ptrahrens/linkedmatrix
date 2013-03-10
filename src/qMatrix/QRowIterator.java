package qMatrix;

import java.util.Iterator;

public class QRowIterator<T> implements Iterator<T> {
	private QMatrixSentinel<T> rowSentinel;
	private QMatrixNode<T> node;
	public QRowIterator(QMatrixSentinel<T> rowSentinel){
		this.rowSentinel = rowSentinel;
		this.node = rowSentinel;
	}
	@Override
	public boolean hasNext(){
		return this.node.right != this.rowSentinel;
	}
	@Override
	public T next(){
		if(this.node.right == this.rowSentinel){
			return null;
		}
		this.node = this.node.right;
		return this.node.item;
	}
	@Override
	public void remove() {}
}
