package qMatrix;

import java.util.Iterator;

public class QColumn<T> implements Iterable<T>{
	protected QMatrixSentinel<T> columnSentinel;
	public QColumn(QMatrixSentinel<T> columnSentinel){
		this.columnSentinel = columnSentinel;
	}
	@Override
	public Iterator<T> iterator(){
		return new QColumnIterator<T>(this.columnSentinel);
	}
}
