package qMatrix;

public class QBoundlessMatrixNode<T> extends QMatrixNode<T>{
	QBoundlessMatrixNode(QMatrix<T> matrix, QRow<T> row, QColumn<T> column, T item, QMatrixNode<T> up, QMatrixNode<T> down, QMatrixNode<T> left, QMatrixNode<T> right){
		super(matrix, row, column, item, up, down, left, right);
	}
	@Override
	public QMatrixNode<T> up() throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
		if(!this.up.isValidNode()){
			return this.up.up;
		}
		return this.up;
	}
	@Override
	public QMatrixNode<T> down() throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
		if(!this.down.isValidNode()){
			return this.down.down;
		}
		return this.down;
	}
	@Override
	public QMatrixNode<T> left() throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
		if(!this.left.isValidNode()){
			return this.left.left;
		}
		return this.left;
	}
	@Override
	public QMatrixNode<T> right() throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
		if(!this.right.isValidNode()){
			return this.right.right;
		}
		return this.right;
	}
}
