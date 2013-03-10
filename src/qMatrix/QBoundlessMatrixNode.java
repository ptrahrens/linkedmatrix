package qMatrix;

public class QBoundlessMatrixNode<T> extends QMatrixNode<T>{
	public QBoundlessMatrixNode(T item, QMatrixNode<T> up, QMatrixNode<T> down, QMatrixNode<T> left, QMatrixNode<T> right){
		super(item, up, down, left, right);
	}
	@Override
	public QMatrixNode<T> up(){
		if(this.up instanceof QMatrixSentinel){
			return this.up.up;
		}
		return this.up;
	}
	@Override
	public QMatrixNode<T> down(){
		if(this.down instanceof QMatrixSentinel){
			return this.down.down;
		}
		return this.down;
	}
	@Override
	public QMatrixNode<T> left(){
		if(this.left instanceof QMatrixSentinel){
			return this.left.left;
		}
		return this.left;
	}
	@Override
	public QMatrixNode<T> right(){
		if(this.right instanceof QMatrixSentinel){
			return this.right.right;
		}
		return this.right;
	}
}
