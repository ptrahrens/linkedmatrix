package qMatrix;

public interface QMatrixNodeAware<T extends QMatrixNodeAware<T>>{
	public void setQMatrixNode(QMatrixNode<T> qMatrixNode);
	public QMatrixNode<T> getQMatrixNode();
}
