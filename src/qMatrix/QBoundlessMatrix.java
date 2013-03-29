package qMatrix;

public class QBoundlessMatrix<E> extends QMatrix<E>{
	@Override
	protected QMatrixNode<E> newNode(QMatrix<E> matrix, QRow<E> row, QColumn<E> column, E item, QMatrixNode<E> up, QMatrixNode<E> down, QMatrixNode<E> left, QMatrixNode<E> right){
		return new QBoundlessMatrixNode<E>(matrix, row, column, item, up, down, left, right);
	}
}
