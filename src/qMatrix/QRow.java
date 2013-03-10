package qMatrix;

import java.util.Iterator;

public class QRow<T> implements Iterable<T> {
	protected QMatrixSentinel<T> rowSentinel;
	public QRow(QMatrixSentinel<T> rowSentinel){
		this.rowSentinel = rowSentinel;
	}
	@Override
	public Iterator<T> iterator(){
		return new QRowIterator<T>(this.rowSentinel);
	}
}
