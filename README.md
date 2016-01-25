he purpose of the project was to implement a quadruply-linked matrix. I have taken the concept for a linked list and extended it to matricies in an atypical way. Each node is linked to other nodes with an up, down, left, and right pointer. There is a row of column sentinels, and a column of row sentinels. Each column and row can be iterated over like an individual linked list. The matrix is circularly linked in both the horizontal and vertical directions, making a boundless matrix very easy to implement. As an example of what can be done with a linked matrix, I made an applet where each node in the QMatrix is represented by a separate cell in a simulation.

Full description:

A QMatrix is a mutable, quadruply linked matrix ADT. Each row is circularly
linked and each column is circularly linked. The row and column of sentinel
nodes that result are also circularly linked, and share the main sentinel
node.

This concept could be confusing, so here is an ascii diagram of what this
looks like:

Diagram of a sample 2x4 QMatrix structure:
  Key:
    s is the main sentinel
    r is a row sentinel 
    c is a column sentinel 
    q is a node
    n is the number of columns (not counting sentinel column) 
    m is the number of rows (not counting sentinel row) 

note that connections wrap to other side

  |  |  |  |  | 
--s--c--c--c--c--
  |  |  |  |  |
--r--q--q--q--q--
  |  |  |  |  |
--r--q--q--q--q-- 
  |  |  |  |  |
  
Performance:
                               Linked Matrix 2D Array   2D Linked List
          Lookup Node At(i,j): O(i + j)      O(1)       O(i + j)
          Insertion(row at j): O(n + j)      O(m-j)     O(j)
       Insertion(column at i): O(m + i)      O(m(n-i))  O(mi)
            Removal(row at j): O(n + j)      O(m-j)     O(j)
         Removal(column at i): O(m + i)      O(m(n-i))  O(mi)
   Get Adjacent Node(up/down): O(1)          O(1)       O(n)
Get Adjacent Node(left/right): O(1)          O(1)       O(1)

QMatrix Invariants: 
 1) head != null
 2) for every QMatrixNode q in the matrix, q.up != null 
 3) for every QMatrixNode q in the matrix, q.down != null 
 4) for every QMatrixNode q in the matrix, q.left != null 
 5) for every QMatrixNode q in the matrix, q.right != null 
 6) for every QMatrixNode q in the matrix, if q.up == t, then t.down == q 
 7) for every QMatrixNode q in the matrix, if q.down == t, then t.up == q 
 8) for every QMatrixNode q in the matrix, if q.left == t, then t.right == q 
 9) for every QMatrixNode q in the matrix, if q.right == t, then t.left == q 
10) for every QMatrixNode q except the head, row, and column sentinels, q.matrix = u 
11) m is the number of QMatrixNodes (not counting head or column sentinels) that can
      be accessed by a sequence of "down" references. 
12) n is the number of QMatrixNodes (not counting head or row sentinels) that can
      be accessed by a sequence of "right" references.
