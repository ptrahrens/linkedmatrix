/**
 * 
 */
package qMatrix;

/**
 * @author peter
 *
 */
public class QMatrixNode<T> {
	QMatrixNode<T> up;
	QMatrixNode<T> down;
	QMatrixNode<T> left;
	QMatrixNode<T> right;
	public T item;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public QMatrixNode(T item, QMatrixNode<T> up, QMatrixNode<T> down, QMatrixNode<T> left, QMatrixNode<T> right){
		this.item = item;
		if(item instanceof QMatrixNodeAware){
			((QMatrixNodeAware) item).setOMatrixNode(this);
		}
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	public QMatrixNode<T> up(){
		if(this.up instanceof QMatrixSentinel){
			return null;
		}
		return this.up;
	}
	public QMatrixNode<T> up(int i){
		if(i == 0){
			return this;
		}else if(i < 0){
			assert this.down() != null:"Matrix index out of bounds.";
			return this.down().up(i + 1);
		}else{
			assert this.up() != null:"Matrix index out of bounds.";
			return this.up().up(i - 1);
		}
	}
	public QMatrixNode<T> down(){
		if(this.down instanceof QMatrixSentinel){
			return null;
		}
		return this.down;
	}
	public QMatrixNode<T> down(int i){
		if(i == 0){
			return this;
		}else if(i < 0){
			assert this.up() != null:"Matrix index out of bounds.";
			return this.up().down(i + 1);
		}else{
			assert this.down() != null:"Matrix index out of bounds.";
			return this.down().down(i - 1);
		}
	}
	public QMatrixNode<T> left(){
		if(this.left instanceof QMatrixSentinel){
			return null;
		}
		return this.left;
	}
	public QMatrixNode<T> left(int j){
		if(j == 0){
			return this;
		}else if(j < 0){
			assert this.right() != null:"Matrix index out of bounds.";
			return this.right().left(j + 1);
		}else{
			assert this.left() != null:"Matrix index out of bounds.";
			return this.left().left(j - 1);
		}
	}
	public QMatrixNode<T> right(){
		if(this.right instanceof QMatrixSentinel){
			return null;
		}
		return this.right;
	}
	public QMatrixNode<T> right(int j){
		if(j == 0){
			return this;
		}else if(j < 0){
			assert this.left() != null:"Matrix index out of bounds.";
			return this.left().right(j + 1);
		}else{
			assert this.right() != null:"Matrix index out of bounds.";
			return this.right().right(j - 1);
		}
	}
}
