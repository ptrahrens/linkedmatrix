package qMatrix;

public class QMatrixSentinel<T> extends QMatrixNode<T> {
	public QMatrixSentinel(QMatrixNode<T> up, QMatrixNode<T> down, QMatrixNode<T> left, QMatrixNode<T> right){
		super(null, up, down, left, right);
	}
	@Override
	public QMatrixNode<T> up(){
		return this.up;
	}
	@Override
	public QMatrixNode<T> down(){
		return this.down;
	}
	@Override
	public QMatrixNode<T> left(){
		return this.left;
	}
	@Override
	public QMatrixNode<T> right(){
		return this.right;
	}
}
