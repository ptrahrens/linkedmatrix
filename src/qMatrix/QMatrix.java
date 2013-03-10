/**
 * 
 */
package qMatrix;
import java.util.ArrayList;
import java.util.Iterator;

/**qMatrix is the equivalent of a circularly, doubly linked list, except for matricies.
 * This concept could be confusing, so here is an ascii diagram of what this looks like:
 * Diagram of a sample OMatrix structure:
 * Key:
 *   s is the sentinel
 *   r is a row sentinel
 *   c is a column sentinel
 *   q is a node
 *   n is the number of columns (not counting sentinel column)
 *   m is the number of rows (not counting sentinel row)
 *   note that connections wrap to other side
 *     |  |  |  |  |  
 *   --s--r--r--r--r--
 *     |  |  |  |  |  
 *   --c--q--q--q--q--
 *     |  |  |  |  |  
 *   --c--q--q--q--q--
 *     |  |  |  |  |  
 * @author peter
 *
 */

public class QMatrix<E> implements Iterable<E>{
	protected QMatrixSentinel<E> sentinel;
	protected int m;
	protected int n;
	protected QMatrixNode<E> newNode(E item, QMatrixNode<E> up, QMatrixNode<E> down, QMatrixNode<E> left, QMatrixNode<E> right){
		return new QMatrixNode<E>(item, up, down, left, right);
	}
	public QMatrix(){
		this.sentinel = new QMatrixSentinel<E>(null, null, null, null);
		this.sentinel.up = this.sentinel;
		this.sentinel.down = this.sentinel;
		this.sentinel.left = this.sentinel;
		this.sentinel.right = this.sentinel;
		this.m = 0;
		this.n = 0;
	}
	@Override
	public String toString(){
		String repr = "";
		QMatrixNode<E> i = this.sentinel;
		QMatrixNode<E> j;
		while(i.down != this.sentinel){
			i = i.down;
			j = i;
			while(j.right != i){
				j = j.right;
				repr += j.item.toString() + " ";
			}
			repr += "\n";
		}
		return repr;
	}

	@Override
	public Iterator<E> iterator(){
		return this.rowIterator();
	}
	
	public Iterator<E> rowIterator(){
		return new OMatrixRowIterator<E>(this.sentinel);
	}
	//TODO make this faster, cleaner
	public class OMatrixRowIterator<F> implements Iterator<F> {
		private QMatrixSentinel<F> sentinel;
		private QMatrixSentinel<F> rowSentinel;
		private Iterator<F> rowIterator;
		public OMatrixRowIterator(QMatrixSentinel<F> sentinel){
			this.sentinel = sentinel;
			this.rowSentinel = sentinel;
			this.rowIterator = null;
		}
		@Override
		public boolean hasNext() {
			if(this.rowIterator != null && this.rowIterator.hasNext()){
				return true;
			}else if(this.rowSentinel.down != this.sentinel){
				return true;
			}else{
				return false;
			}
		}

		@Override
		public F next() {
			if(this.rowIterator != null && this.rowIterator.hasNext()){
				return this.rowIterator.next();
			}else if(this.rowSentinel.down != this.sentinel){
				this.rowSentinel = (QMatrixSentinel<F>) this.rowSentinel.down;
				this.rowIterator = new QRowIterator<F>(this.rowSentinel);
				return this.rowIterator.next();
			}else{
				return null;
			}
		}


		@Override
		public void remove() {}
		
	}
	
	public Iterator<E> columnIterator(){
		return new OMatrixColumnIterator<E>(this.sentinel);
	}
	//TODO make this faster, cleaner
	public class OMatrixColumnIterator<F> implements Iterator<F> {
		private QMatrixSentinel<F> sentinel;
		private QMatrixSentinel<F> columnSentinel;
		private Iterator<F> columnIterator;
		public OMatrixColumnIterator(QMatrixSentinel<F> sentinel){
			this.sentinel = sentinel;
			this.columnSentinel = sentinel;
			this.columnIterator = null;
		}

		@Override
		public boolean hasNext() {
			if(this.columnIterator != null && this.columnIterator.hasNext()){
				return true;
			}else if(this.columnSentinel.right != this.sentinel){
				return true;
			}else{
				return false;
			}
		}

		@Override
		public F next() {
			if(this.columnIterator != null && this.columnIterator.hasNext()){
				return this.columnIterator.next();
			}else if(this.columnSentinel.right != this.sentinel){
				this.columnSentinel = (QMatrixSentinel<F>) this.columnSentinel.right;
				this.columnIterator = new QColumnIterator<F>(this.columnSentinel);
				return this.columnIterator.next();
			}else{
				return null;
			}
		}

		@Override
		public void remove() {}
		
	}
	
	public static void main(String[] args){
		QMatrix<Integer> foo = new QMatrix<Integer>();
		ArrayList<Integer> bar1 = new ArrayList<Integer>();
		bar1.add(1);
		bar1.add(2);
		bar1.add(3);
		bar1.add(4);		
		ArrayList<Integer> bar2 = new ArrayList<Integer>();
		bar2.add(5);
		bar2.add(6);
		bar2.add(7);
		bar2.add(8);
		ArrayList<Integer> qux = new ArrayList<Integer>();
		qux.add(9);
		qux.add(0);
		foo.addRowToBottom(bar1);
		foo.addRowToTop(bar2);
		foo.addColumnToBack(qux);
		ArrayList<Integer> qux2 = new ArrayList<Integer>();
		qux2.add(7);
		qux2.add(7);
		foo.insertColumnAt(3, qux2);
		System.out.println(foo.numRows());
		System.out.println(foo.numColumns());
		System.out.println(foo);
		for(Integer x: foo){
			System.out.println(x);
		}
		Iterator<Integer> col = foo.columnIterator();
		System.out.println(col.hasNext());
		while(col.hasNext()){
			System.out.println(col.next());
		}
		System.out.println(foo.getNode(1,4).up().left().left().down().left().up().item);
	}
	
	public int numRows(){
		return this.m;
	}
	public int numColumns(){
		return this.n;
	}
	public QMatrixNode<E> getNode(int i, int j){
		return this.sentinel.down(i + 1).right(j + 1);
	}
	public E getItem(int i, int j){
		return this.getNode(i, j).item;
	}
	public Iterable<E> getColumn(int j){
		return new QColumn<E>(((QMatrixSentinel<E>) this.sentinel.right(j + 1)));
	}
	public Iterable<E> getRow(int i){
		return new QRow<E>(((QMatrixSentinel<E>) this.sentinel.down(i + 1)));
	}
	
	public void addColumnToFront(Iterable<E> column){
		this.insertColumnAfter(this.sentinel, column);
	}
	public void addColumnToBack(Iterable<E> column){
		this.insertColumnAfter(((QMatrixSentinel<E>) this.sentinel.left), column);
	}
	public void insertColumnAt(int j, Iterable<E> column){
		this.insertColumnAfter(((QMatrixSentinel<E>) this.sentinel.right(j)), column);
	}
	protected void insertColumnAfter(QMatrixSentinel<E> columnSentinel, Iterable<E> column){
		QMatrixNode<E> current;
		if(this.n == 0){
			assert columnSentinel == this.sentinel;
		}
		columnSentinel.right.left = new QMatrixSentinel<E>(null, null, columnSentinel, columnSentinel.right);
		columnSentinel.right = columnSentinel.right.left;
		current = columnSentinel.right;
		Iterator<E> columnIter = column.iterator();
		while(columnIter.hasNext()){
			if(this.n == 0){
				current.left.down = new QMatrixSentinel<E>(current.left, this.sentinel, null, null);
				this.m++;
			}
			current.down = this.newNode(columnIter.next(), current, columnSentinel.right, current.left.down, current.right.down);
			current.left.down.right = current.down;
			current.right.down.left = current.down;
			if(!columnIter.hasNext()){
				columnSentinel.right.up = current.down;
				if(this.n == 0){
					this.sentinel.up = current.left.down;
				}
			}
			current = current.down;
		}
		assert current.down == this.sentinel:"Inconsistent column length.";
		this.n++;
	}
	
	public void addRowToTop(Iterable<E> row){
		this.insertRowBelow(this.sentinel, row);
	}
	public void addRowToBottom(Iterable<E> row){
		this.insertRowBelow(((QMatrixSentinel<E>) this.sentinel.up), row);
	}
	public void insertRowAt(int i, Iterable<E> row){
		this.insertRowBelow(((QMatrixSentinel<E>) this.sentinel.down(i)), row);
	}
	protected void insertRowBelow(QMatrixSentinel<E> rowSentinel, Iterable<E> row){
		QMatrixNode<E> current;
		if(this.m == 0){
			assert rowSentinel == this.sentinel;
		}
		rowSentinel.down.up = new QMatrixSentinel<E>(rowSentinel, rowSentinel.down, null, null);
		rowSentinel.down = rowSentinel.down.up;
		current = rowSentinel.down;
		Iterator<E> rowIter = row.iterator();
		while(rowIter.hasNext()){
			if(this.m == 0){
				current.up.right = new QMatrixSentinel<E>(null, null, current.up, this.sentinel);
				this.n++;
			}
			current.right = this.newNode(rowIter.next(),current.up.right, current.down.right, current, rowSentinel.down);
			current.up.right.down = current.right;
			current.down.right.up = current.right;
			if(!rowIter.hasNext()){
				rowSentinel.down.left = current.right;
				if(this.m == 0){
					this.sentinel.left = current.up.right;
				}
			}
			current = current.right;
		}
		assert current.right == this.sentinel:"Inconsistent row length.";
		this.m++;
	}
	/*
	protected void insertRowBelow(OMatrixSentinel<T> rowSentinel, T[] row){
		OMatrixNode<T> current;
		if(this.n == 0){
			assert rowSentinel == this.sentinel;
			current = this.sentinel;
			for(int j = 0; j < row.length; j++){
				current.right =  new OMatrixSentinel<T>(null, null, current, this.sentinel);
				current = current.right;
			}
			this.sentinel.left = current;
			this.n = row.length;
		}
		assert row.length == this.n: "wrong row length";
		rowSentinel.down.up = new OMatrixSentinel<T>(rowSentinel, rowSentinel.down, null, null);
		rowSentinel.down = rowSentinel.down.up;
		current = rowSentinel.down;
		for(int i = 0; i < row.length; i++){
			current.right = this.newNode(row[i],current.up.right, current.down.right, current, rowSentinel.down);
			current.up.right.down = current.right;
			current.down.right.up = current.right;
			if(i == row.length - 1){
				rowSentinel.down.left = current;
			}else{
				current = current.right;
			}
		}
		this.m++;
	}
	*/
	/*
	protected void insertColumnAfter(OMatrixSentinel<T> columnSentinel, T[] column){
		OMatrixNode<T> current;
		if(this.m == 0){
			assert columnSentinel == this.sentinel;
			current = this.sentinel;
			for(int i = 0; i < column.length; i++){
				current.down = new OMatrixSentinel<T>(current, this.sentinel, null, null);
				current = current.down;
			}
			this.sentinel.up = current;
			this.m = column.length;
		}
		assert column.length == this.m;
		columnSentinel.right.left = new OMatrixSentinel<T>(columnSentinel, columnSentinel.right, null, null);
		columnSentinel.right = columnSentinel.right.left;
		current = columnSentinel.right;
		for(int j = 0; j < column.length; j++){
			current.down = this.newNode(column[j], current.left.down, current.right.down, current, columnSentinel.right);
			current.left.down.right = current.down;
			current.right.down.left = current.down;
			if(j == column.length - 1){
				columnSentinel.right.up = current;
			}else{
				current = current.down;
			}
		}
		this.n++;
	}
	*/

}
