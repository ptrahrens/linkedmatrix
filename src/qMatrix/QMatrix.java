/**
 * 
 */
package qMatrix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A QMatrix is a mutable, quadruply linked matrix ADT. Each row is circularly
 * linked and each column is circularly linked. The row and column of sentinel
 * nodes that result are also circularly linked, and share the main sentinel
 * node.
 * 
 * This concept could be confusing, so here is an ascii diagram of what this
 * looks like:
 * 
 * Diagram of a sample 2x4 QMatrix structure: Key: s is the main sentinel r is a
 * row sentinel c is a column sentinel q is a node n is the number of columns
 * (not counting sentinel column) m is the number of rows (not counting sentinel
 * row) note that connections wrap to other side
 * 
 *   |  |  |  |  | 
 * --s--c--c--c--c--
 *   |  |  |  |  |
 * --r--q--q--q--q--
 *   |  |  |  |  |
 * --r--q--q--q--q-- 
 *   |  |  |  |  |
 *   
 * Performance:
 *                                Linked Matrix 2D Array   2D Linked List
 *           Lookup Node At(i,j): O(i + j)      O(1)       O(i + j)
 *           Insertion(row at j): O(n + j)      O(m-j)     O(j)
 *        Insertion(column at i): O(m + i)      O(m(n-i))  O(mi)
 *             Removal(row at j): O(n + j)      O(m-j)     O(j)
 *          Removal(column at i): O(m + i)      O(m(n-i))  O(mi)
 *    Get Adjacent Node(up/down): O(1)          O(1)       O(n)
 * Get Adjacent Node(left/right): O(1)          O(1)       O(1)
 * 
 * QMatrix Invariants: 
 * 1) head != null
 * 2) for every QMatrixNode q in the matrix, q.up != null 
 * 3) for every QMatrixNode q in the matrix, q.down != null 
 * 4) for every QMatrixNode q in the matrix, q.left != null 
 * 5) for every QMatrixNode q in the matrix, q.right != null 
 * 6) for every QMatrixNode q in the matrix, if q.up == t, then t.down == q 
 * 7) for every QMatrixNode q in the matrix, if q.down == t, then t.up == q 
 * 8) for every QMatrixNode q in the matrix, if q.left == t, then t.right == q 
 * 9) for every QMatrixNode q in the matrix, if q.right == t, then t.left == q 
 * 10) for every QMatrixNode q except the head, row, and column sentinels, q.matrix = u 
 * 11) m is the number of QMatrixNodes (not counting head or column sentinels) that can be accessed by a sequence of "down" references. 
 * 12) n is the number of QMatrixNodes (not counting head or row sentinels) that can be accessed by a sequence of "right" references.
 * 
 * @author Peter Ahrens
 * 
 */

public class QMatrix<E> implements Iterable<E> {
  protected QMatrixNode<E> head;
  protected int m;
  protected int n;

  /**
   * newNode simply returns a new QMatrixNode<E> that is appropriate for this
   * matrix (override this method to use a different type of node)
   * 
   * @param matrix
   *          the matrix this node is contained in
   * @param item
   *          the item this node stores
   * @param up
   *          the node above this one
   * @param down
   *          the node below this one
   * @param left
   *          the node to the left of this one
   * @param right
   *          the node to the right of this one
   **/
  protected QMatrixNode<E> newNode(QMatrix<E> matrix, QRow<E> row,
      QColumn<E> column, E item, QMatrixNode<E> up, QMatrixNode<E> down,
      QMatrixNode<E> left, QMatrixNode<E> right) {
    return new QMatrixNode<E>(matrix, row, column, item, up, down, left, right);
  }

  /**
   * QMatrix() constructs an empty QMatrix Keep in mind that when adding a new
   * row or column to an empty QMatrix, in addition to creating a new row or
   * column sentinel, one must also create for each new node a new column or row
   * sentinel, respectively.
   **/
  public QMatrix() {
    this.head = this.newNode(null, null, null, null, null, null, null, null);
    this.head.row = new QRow<E>(null, head);
    this.head.column = new QColumn<E>(null, head);
    this.head.up = this.head;
    this.head.down = this.head;
    this.head.left = this.head;
    this.head.right = this.head;
    this.m = 0;
    this.n = 0;
  }

  @Override
  public String toString() {
    String str = "";
    QMatrixNode<E> i = this.head;
    QMatrixNode<E> j;
    while (i.down != this.head) {
      i = i.down;
      j = i;
      while (j.right != i) {
        j = j.right;
        str += j.item.toString() + " ";
      }
      str += "\n";
    }
    return str;
  }

  @Override
  public Iterator<E> iterator() {
    return this.rowIterator();
  }

  public Iterator<E> rowIterator() {
    return new QMatrixRowIterator<E>(this);
  }

  public class QMatrixRowIterator<F> implements Iterator<F> {
    protected QMatrixNode<F> head;
    protected QMatrixNode<F> current;

    public QMatrixRowIterator(QMatrix<F> matrix) {
      this.head = matrix.head;
      this.current = matrix.head.left;
    }

    @Override
    public boolean hasNext() {
      if (this.current.right.column == this.head.column) {
        return this.current.right != this.head.up;
      }
      return true;
    }

    @Override
    public F next() {
      if (!this.hasNext()) {
        throw new NoSuchElementException();
      }
      if (this.current.right.column == this.head.column) {
        this.current = this.current.right.down.right;
      } else {
        this.current = this.current.right;
      }
      return this.current.item;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public class QMatrixColumnIterator<F> implements Iterator<F> {
    protected QMatrixNode<F> head;
    protected QMatrixNode<F> current;

    public QMatrixColumnIterator(QMatrix<F> matrix) {
      this.head = matrix.head;
      this.current = matrix.head.up;
    }

    @Override
    public boolean hasNext() {
      if (this.current.down.row == this.head.row) {
        return this.current.down != this.head.left;
      }
      return true;
    }

    @Override
    public F next() {
      if (!this.hasNext()) {
        throw new NoSuchElementException();
      }
      if (this.current.down.row == this.head.row) {
        this.current = this.current.down.right.down;
      } else {
        this.current = this.current.down;
      }
      return this.current.item;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public Iterator<E> columnIterator() {
    return new QMatrixColumnIterator<E>(this);
  }

  public static void main(String[] args) {
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
    foo.insertRowBottom(bar1);
    foo.insertRowTop(bar2);
    foo.insertColumnBack(qux);
    ArrayList<Integer> qux2 = new ArrayList<Integer>();
    qux2.add(7);
    qux2.add(7);
    try {
      foo.getColumn(3).insertLeft(qux2);
    } catch (InvalidColumnException e) {
      e.printStackTrace();
    } catch (InvalidNodeException e) {
      e.printStackTrace();
    }
    System.out.println("Rows: " + foo.numRows() + " Columns: "
        + foo.numColumns());
    System.out.println(foo);
    System.out.println("Iteration by Row: ");
    for (Integer x : foo) {
      System.out.println(x);
    }
    System.out.println("Iteration by Column: ");
    Iterator<Integer> foobar = foo.columnIterator();
    while (foobar.hasNext()) {
      System.out.println(foobar.next());
    }
  }

  public int numRows() {
    return this.m;
  }

  public int numColumns() {
    return this.n;
  }

  public QMatrixNode<E> getNode(int i, int j) throws InvalidNodeException {
    return this.head.down.right.down(i).right(j);
  }

  public QColumn<E> getColumn(int j) throws InvalidNodeException {
    return this.head.down.right.right(j).column;
  }

  public QRow<E> getRow(int i) throws InvalidNodeException {
    return this.head.down.right.down(i).row;
  }

  public QColumn<E> insertColumnFront(Iterable<E> newColumnItems) {
    QMatrixNode<E> current;
    this.head.right.left = this.newNode(null, this.head.row, null, null, null,
        null, this.head, this.head.right);
    this.head.right = this.head.right.left;
    this.head.right.column = new QColumn<E>(this, this.head.right);
    current = this.head.right;
    Iterator<E> newColumnIterator = newColumnItems.iterator();
    while (newColumnIterator.hasNext()) {
      if (this.n == 0) {
        current.left.down = this.newNode(null, null, current.left.column, null,
            current.left, this.head, null, null);
        current.left.down.row = new QRow<E>(this, current.left.down);
        this.m++;
      }
      current.down = this.newNode(this, current.left.down.row, current.column,
          newColumnIterator.next(), current, this.head.right,
          current.left.down, current.right.down);
      current.left.down.right = current.down;
      current.right.down.left = current.down;
      if (!newColumnIterator.hasNext()) {
        this.head.right.up = current.down;
        if (this.n == 0) {
          this.head.up = current.left.down;
        }
      }
      current = current.down;
    }
    assert current.down == this.head.right : "Inconsistent column length.";
    this.n++;
    return this.head.right.column;
  }

  public QColumn<E> insertColumnBack(Iterable<E> newColumnItems) {
    QMatrixNode<E> current;
    this.head.left.right = this.newNode(null, this.head.row, null, null, null,
        null, this.head.left, this.head);
    this.head.left = this.head.left.right;
    this.head.left.column = new QColumn<E>(this, this.head.left);
    current = this.head.left;
    Iterator<E> newColumnIterator = newColumnItems.iterator();
    while (newColumnIterator.hasNext()) {
      if (this.n == 0) {
        current.right.down = this.newNode(null, null, current.right.column,
            null, current.right, this.head, null, null);
        current.right.down.row = new QRow<E>(this, current.right.down);
        this.m++;
      }
      current.down = this.newNode(this, current.right.down.row, current.column,
          newColumnIterator.next(), current, this.head.left, current.left.down,
          current.right.down);
      current.right.down.left = current.down;
      current.left.down.right = current.down;
      if (!newColumnIterator.hasNext()) {
        this.head.left.up = current.down;
        if (this.n == 0) {
          this.head.up = current.right.down;
        }
      }
      current = current.down;
    }
    assert current.down == this.head.left : "Inconsistent column length.";
    this.n++;
    return this.head.left.column;
  }

  public QRow<E> insertRowTop(Iterable<E> newRowItems) {
    QMatrixNode<E> current;
    this.head.down.up = this.newNode(null, null, this.head.column, null,
        this.head, this.head.down, null, null);
    this.head.down = this.head.down.up;
    this.head.down.row = new QRow<E>(this, this.head.down);
    current = this.head.down;
    Iterator<E> newRowIterator = newRowItems.iterator();
    while (newRowIterator.hasNext()) {
      if (this.m == 0) {
        current.up.right = this.newNode(null, current.up.row, null, null, null,
            null, current.up, this.head);
        current.up.right.column = new QColumn<E>(this, current.up.right);
        this.n++;
      }
      current.right = this.newNode(this, current.row, current.up.right.column,
          newRowIterator.next(), current.up.right, current.down.right, current,
          this.head.down);
      current.up.right.down = current.right;
      current.down.right.up = current.right;
      if (!newRowIterator.hasNext()) {
        this.head.down.left = current.right;
        if (this.m == 0) {
          this.head.left = current.up.right;
        }
      }
      current = current.right;
    }
    assert current.right == this.head.down : "Inconsistent row length.";
    this.m++;
    return this.head.down.row;
  }

  public QRow<E> insertRowBottom(Iterable<E> newRowItems) {
    QMatrixNode<E> current;
    this.head.up.down = this.newNode(null, null, this.head.column, null,
        this.head.up, this.head, null, null);
    this.head.up = this.head.up.down;
    this.head.up.row = new QRow<E>(this, this.head.up);
    current = this.head.up;
    Iterator<E> newRowIterator = newRowItems.iterator();
    while (newRowIterator.hasNext()) {
      if (this.m == 0) {
        current.down.right = this.newNode(null, current.down.row, null, null,
            null, null, current.down, this.head);
        current.down.right.column = new QColumn<E>(this, current.down.right);
        this.n++;
      }
      current.right = this.newNode(this, current.row,
          current.down.right.column, newRowIterator.next(), current.up.right,
          current.down.right, current, this.head.up);
      current.down.right.up = current.right;
      current.up.right.down = current.right;
      if (!newRowIterator.hasNext()) {
        this.head.up.left = current.right;
        if (this.m == 0) {
          this.head.left = current.down.right;
        }
      }
      current = current.right;
    }
    assert current.right == this.head.up : "Inconsistent row length.";
    this.m++;
    return this.head.up.row;
  }
}

// Copyright (c) 2013, Peter Ahrens
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// Redistributions of source code must retain the above copyright notice, this
// list of conditions and the following disclaimer.
// Redistributions in binary form must reproduce the above copyright notice,
// this list of conditions and the following disclaimer in the documentation
// and/or other materials provided with the distribution.
// Neither the name of linkedMatrix nor the names of its contributors may be
// used to endorse or promote products derived from this software without
// specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

