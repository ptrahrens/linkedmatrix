package qMatrix;

public class QBoundlessMatrix<E> extends QMatrix<E>{
	@Override
	protected QMatrixNode<E> newNode(E item, QMatrixNode<E> up, QMatrixNode<E> down, QMatrixNode<E> left, QMatrixNode<E> right){
		return new QBoundlessMatrixNode<E>(item, up, down, left, right);
	}
}
