/**
 * 
 */
package qMatrix;

/**
 * @author peter
 *
 */
public class QMatrixNode<T> {
  QMatrix<T> matrix;
  QColumn<T> column;
  QRow<T> row;
  T item;
  QMatrixNode<T> up;
  QMatrixNode<T> down;
  QMatrixNode<T> left;
  QMatrixNode<T> right;
  @SuppressWarnings({ "rawtypes", "unchecked" })
  QMatrixNode(QMatrix<T> matrix, QRow<T> row, QColumn<T> column, T item, QMatrixNode<T> up, QMatrixNode<T> down, QMatrixNode<T> left, QMatrixNode<T> right){
    this.matrix = matrix;
    this.row = row;
    this.column = column;
    this.item = item;
    this.up = up;
    this.down = down;
    this.left = left;
    this.right = right;
    if(item instanceof QMatrixNodeAware<?>){
      ((QMatrixNodeAware) item).setQMatrixNode(this);
    }
  }

  public boolean isValidNode(){
    return this.matrix != null;
  }

  public T item() throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
    return this.item;
  }

  public void setItem(T item) throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
    this.item = item;
  }

  public QRow<T> row() throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
    return this.row;
  }

  public QColumn<T> column() throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
    return this.column;
  }

  public QMatrixNode<T> up() throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
    return this.up;
  }
  public QMatrixNode<T> up(int i) throws InvalidNodeException{
    if(i == 0){
      return this;
    }else if(i < 0){
      return this.down(i * -1);
    }else{
      return this.up().up(i - 1);
    }
  }
  public QMatrixNode<T> down() throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
    return this.down;
  }
  public QMatrixNode<T> down(int i) throws InvalidNodeException{
    if(i == 0){
      return this;
    }else if(i < 0){
      return this.up(i * -1);
    }else{
      return this.down().down(i - 1);
    }
  }
  public QMatrixNode<T> left() throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
    return this.left;
  }
  public QMatrixNode<T> left(int j) throws InvalidNodeException{
    if(j == 0){
      return this;
    }else if(j < 0){
      return this.right(j * -1);
    }else{
      return this.left().left(j - 1);
    }
  }
  public QMatrixNode<T> right() throws InvalidNodeException{
    if(!this.isValidNode()){
      throw new InvalidNodeException();
    }
    return this.right;
  }
  public QMatrixNode<T> right(int j) throws InvalidNodeException{
    if(j == 0){
      return this;
    }else if(j < 0){
      return this.left(j * -1);
    }else{
      return this.right().right(j - 1);
    }
  }

  protected QMatrixNode<T> utilUp(int j){
    if(j == 0){
      return this;
    }else if(j < 0){
      return this.utilDown(j * -1);
    }else{
      return this.up.utilUp(j - 1);
    }
  } 
  protected QMatrixNode<T> utilDown(int j){
    if(j == 0){
      return this;
    }else if(j < 0){
      return this.utilUp(j * -1);
    }else{
      return this.down.utilDown(j - 1);
    }
  }
  protected QMatrixNode<T> utilLeft(int j){
    if(j == 0){
      return this;
    }else if(j < 0){
      return this.utilRight(j * -1);
    }else{
      return this.left.utilLeft(j - 1);
    }
  }
  protected QMatrixNode<T> utilRight(int j){
    if(j == 0){
      return this;
    }else if(j < 0){
      return this.utilLeft(j * -1);
    }else{
      return this.right.utilRight(j - 1);
    }
  } 
  protected void delete(){
    this.up = null;
    this.down = null;
    this.left = null;
    this.right = null;
    this.item = null;
    this.matrix = null;
    this.column = null;
    this.row = null;
  }
}
