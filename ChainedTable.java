//Jose Mora
//CIS 256
//05-12-2016
/******************************************************************************
* A <CODE>ChainedTable</CODE> is a chained hash table.
*
******************************************************************************/
public class ChainedTable<K, E>
{
   // Invariant of the ChainedTable class:
   //   For index i, the Object in table[i] is the head reference for a linked
   //   of all the elements for which hash(key) is i.
    private Object[ ] table;


   /**
   * Initialize an empty ChainedTable with a specified table size.
   * @param <CODE>tableSize</CODE>
   *   the table size for this new chained hash table
   * <dt><b>Precondition:</b><dd>
   *   <CODE>tableSize > 0</CODE>
   * <dt><b>Postcondition:</b><dd>
   *   This ChainedTable is empty and has the specified table size.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the specified table size.
   * @exception IllegalArgumentException
   *   Indicates that tableSize is not positive.
   **/
   public ChainedTable(int tableSize)
   {
      if (tableSize <= 0)
	   throw new IllegalArgumentException("Table size must be positive.");
      // Allocate the table which is initially all null head references.
      table = new Object[tableSize];
   }


   /**
   * Determines whether a specified key is in this ChainedTable.
   * @param <CODE>key</CODE>
   *   the non-null key to look for
   * <dt><b>Precondition:</b><dd>
   *   <CODE>key</CODE> cannot be null.
   * @return
   *   <CODE>true</CODE? (if this ChainedTable contains an object with the specified
   *   key); <CODE>false</CODE> otherwise. Note that <CODE>key.equals( )</CODE>
   *   is used to compare the <CODE>key</CODE> to the keys that are in the
   *   ChainedTable.
   * @exception NullPointerException
   *   Indicates that <CODE>key</CODE> is null.
   **/
   public boolean containsKey(K key)
   {
      // The following return statement should be replaced by the student's code:
	   boolean hasKey = false;
	   int index = hash(key);
	   ChainedHashNode<K,E> cursor;
	   ChainedHashNode<K,E> head = (ChainedHashNode<K,E>) table[index];
	   
	   if ( key == null) {
		   throw new NullPointerException("Customer not found");
	   }	
	   	   
	   for(cursor = head; cursor != null; cursor = cursor.link) {
		   if (cursor.key.equals(key)) {
			   hasKey = true;
		   }
	   }   	
	   return hasKey;
   }
	   


   /** Retrieves an object for a specified key.
   * @param <CODE>key</CODE>
   *   the non-null key to look for
   * <dt><b>Precondition:</b><dd>
   *   <CODE>key</CODE> cannot be null.
   * @return
   *   a reference to the object with the specified <CODE>key</CODE (if this
   *   ChainedTable contains an such an object);  null otherwise. Note that
   *   <CODE>key.equals( )</CODE> is used to compare the <CODE>key</CODE>
   *   to the keys that are in the ChainedTable.
   * @exception NullPointerException
   *   Indicates that <CODE>key</CODE> is null.
   **/
   public E get(K key)
   {
      // The following return statement should be replaced by the student's code:
	   int index = hash(key);
       ChainedHashNode<K,E> cursor = (ChainedHashNode<K,E>) table[index];
       
       if ( key == null) {
		   throw new NullPointerException("Customer not found");
	   }
       
	   if(table[index] != null) {
		   while (cursor != null) {
			   if (cursor.key.equals(key)) {
				   return cursor.element;
			   } else {
				   cursor = cursor.link;
			   }
		   }		   
	   }
	   return null;
   }


   private int hash(K key)
   // The return value is a valid index of the ChainedTable's table. The index is
   // calculated as the remainder when the absolute value of the key's
   // hash code is divided by the size of the ChainedTable's table.
   {
     return Math.abs(key.hashCode( )) % table.length;
	  // return Math.abs(key.hashCode( )) % table.length*(Math.abs(key.hashCode( )) % table.length);
   }


   /**
   * Add a new element to this ChainedTable, using the specified key.
   * @param <CODE>key</CODE>
   *   the non-null key to use for the new element
   * @param <CODE>element</CODE>
   *   the new element that's being added to this ChainedTable
   * <dt><b>Precondition:</b><dd>
   *   Neither <CODE>key</CODE> nor </CODE>element</CODE> is null.
   * <dt><b>Postcondition:</b><dd>
   *   If this ChainedTable already has an object with the specified <CODE>key</CODE>,
   *   then that object is replaced by </CODE>element</CODE>, and the return
   *   value is a reference to the replaced object. Otherwise, the new
   *   </CODE>element</CODE> is added with the specified <CODE>key</CODE>
   *   and the return value is null.
   * @exception NullPointerException
   *   Indicates that <CODE>key</CODE> or <CODE>element</CODE> is null.
   **/
   @SuppressWarnings("unchecked")
   public E put(K key, E element)
   {
	  int index = hash(key);
	  ChainedHashNode<K, E> cursor = (ChainedHashNode<K,E>) table[index];
      E answer = null;
      
      if (key == null || element == null) {
    	  throw new NullPointerException("Invalid input");
      }
      
      while(cursor != null ) {
    	  if (!cursor.key.equals(key)){
    	 cursor = cursor.link;
    	  }
     }

      if (cursor == null)
      {  // Add a new node at the front of the list of table[hash(key)].
	 int i = hash(key);
	 cursor = new ChainedHashNode<K, E>( );
	 cursor.element = element;
	 cursor.key = key;
	 cursor.link = (ChainedHashNode<K, E>) table[i];
	 table[i] = cursor;
      }
      else
      {  // The new element replaces an old node.
         answer = cursor.element;
         cursor.element = element;
      }
      return answer;
   }


   /**
   * Removes an object for a specified key.
   * @param <CODE>key</CODE>
   *   the non-null key to look for
   * <dt><b>Precondition:</b><dd>
   *   <CODE>key</CODE> cannot be null.
   * <dt><b>Postcondition:</b><dd>
   *   If an object was found with the specified </CODE>key</CODE>, then that
   *   object has been removed from this ChainedTable and a copy of the removed object
   *   is returned; otherwise, this ChainedTable is unchanged and the null reference
   *   is returned.  Note that
   *   <CODE>key.equals( )</CODE> is used to compare the <CODE>key</CODE>
   *   to the keys that are in the ChainedTable.
   * @exception NullPointerException
   *   Indicates that </CODE>key</CODE> is null.
   **/
   
@SuppressWarnings("unchecked")
public E remove(K key)
   {
	   int index = hash(key);
	   ChainedHashNode<K,E> cursor;
	   ChainedHashNode<K,E> previousCursor = null;
	   
	   if(key == null)
	   {
	        throw new NullPointerException("Key is null");
	   }
	   
	   for(cursor = (ChainedHashNode<K,E>) table[index]; cursor !=null && !cursor.key.equals(key); cursor = cursor.link){
		   previousCursor = cursor;
		   }
	   
	        if(cursor.key.equals(key)){
	        	if(previousCursor != null) {
	        	previousCursor.link = cursor.link;
	        	} else {
	        		table[index] = null;
	        	}
	        	return cursor.element;
	        	} else {	      
	      return null;
	   }
   }
}
class ChainedHashNode<K, E>
{
   K key;
   E element;
   ChainedHashNode<K, E> link;
}