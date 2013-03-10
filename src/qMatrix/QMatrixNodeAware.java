package qMatrix;

public interface QMatrixNodeAware<T extends QMatrixNodeAware<T>>{
	public void setOMatrixNode(QMatrixNode<T> qMatrixNode);
	public QMatrixNode<T> getOMatrixNode();
}
