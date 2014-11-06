package jp.co.worksap.global;

import java.util.NoSuchElementException;

/**
 * @author ming
 * @version 1
 * @date Tue Nov 4 19:10:04 PST 2014
 */
/**
 * The Queue class represents an immutable first-in-first-out (FIFO) queue of
 * objects.
 * <p>
 * @param <E>
 */
public class ImmutableQueue<E> {

    private ImmutableList<E> head;
    private ImmutableList<E> tail;

    /**
     * requires default constructor.
     */
    @SuppressWarnings ("unchecked")
    public ImmutableQueue()
    {
	// modify this constructor if necessary, but do not remove default constructor
	this.head = ImmutableList.emptyList();
	this.tail = ImmutableList.emptyList();
    }

    // add other constructors if necessary
    /**
     * Initialize both head and tail of ImmutableQueue to be the generic
     * ImmutableList elements.
     * <p>
     * @param head
     * @param tail
     */
    public ImmutableQueue(ImmutableList<E> head, ImmutableList<E> tail)
    {
	this.head = head;
	this.tail = tail;
    }

    /**
     * Returns the queue that adds an item into the tail of this queue without
     * modifying this queue.
     * <pre>
     * e.g.
     * When this queue represents the queue (2, 1, 2, 2, 6) and we enqueue the value 4 into this queue,
     * this method returns a new queue (2, 1, 2, 2, 6, 4)
     * and this object still represents the queue (2, 1, 2, 2, 6) .
     * </pre> If the element e is null, throws IllegalArgumentException.
     * <p>
     * @param e <p>
     * @return <p>
     * @throws IllegalArgumentException
     */
    public ImmutableQueue<E> enqueue(E e)
    {
	if (e == null)
	{
	    throw new IllegalArgumentException();
	}

	return new ImmutableQueue<>(this.head.add(e), this.tail);
    }

    /**
     * Returns the queue that removes the object at the head of this queue
     * without modifying this queue.
     * <pre>
     * e.g.
     * When this queue represents the queue (7, 1, 3, 3, 5, 1) ,
     * this method returns a new queue (1, 3, 3, 5, 1)
     * and this object still represents the queue (7, 1, 3, 3, 5, 1) .
     * </pre> If this queue is empty, throws java.util.NoSuchElementException.
     * <p>
     * @return <p>
     * @throws java.util.NoSuchElementException
     */
    @SuppressWarnings ("unchecked")
    public ImmutableQueue<E> dequeue()
    {
	if (this.size() == 0)
	{
	    throw new NoSuchElementException();
	}

	if (!this.tail.isEmpty())
	{
	    return new ImmutableQueue<>(this.head, this.tail.endNode);
	}
	else
	{
	    return new ImmutableQueue<>(ImmutableList.emptyList(), this.head.reverse().endNode);
	}

    }

    /**
     * Looks at the object which is the head of this queue without removing it
     * from the queue.
     * <pre>
     * e.g.
     * When this queue represents the queue (7, 1, 3, 3, 5, 1),
     * this method returns 7 and this object still represents the queue (7, 1, 3, 3, 5, 1)
     * </pre> If the queue is empty, throws java.util.NoSuchElementException.
     * <p>
     * @return <p>
     * @throws java.util.NoSuchElementException
     */
    @SuppressWarnings ("unchecked")
    public E peek()
    {
	if (this.size() == 0)
	{
	    throw new NoSuchElementException();
	}

	if (this.tail.isEmpty())
	{
	    this.tail = this.head.reverse();
	    this.head = ImmutableList.emptyList();
	}

	return this.tail.frontNode;
    }

    /**
     * Returns the number of objects in this queue.
     * <p>
     * @return
     */
    public int size()
    {
	return this.head.lstSize + this.tail.lstSize;
    }

    /**
     * Inner helper ImmutableList class to be represent the generic elements of
     * ImmutableQueue object.
     * <p>
     * @param <E>
     */
    private static class ImmutableList<E> {

	private final E frontNode;
	private final ImmutableList<E> endNode;
	private final int lstSize;

	/**
	 * Default constructor.
	 */
	private ImmutableList()
	{
	    this.frontNode = null;
	    this.endNode = null;
	    this.lstSize = 0;
	}

	/**
	 * The constructor that insert the generic object to the first
	 * element(node) of the ImmutabList and append the original to be the
	 * tail of generic ImmutableList object and increase the size of the
	 * ImmutableList by one.
	 * <p>
	 * @param obj
	 * @param tail
	 */
	private ImmutableList(E obj, ImmutableList<E> tail)
	{
	    this.frontNode = obj;
	    this.endNode = tail;
	    this.lstSize = 1 + tail.lstSize;
	}

	/**
	 * Return the new ImmutableList and initialize to null.
	 * <p>
	 * @return
	 */
	public static ImmutableList emptyList()
	{
	    return new ImmutableList();
	}

	/**
	 * Return the new ImmutableList object that with the new generic element
	 * that insert into the first element of the new ImmutableList.
	 * <p>
	 * @param elem
	 * <p>
	 * @return
	 */
	public ImmutableList<E> add(E elem)
	{
	    return new ImmutableList<>(elem, this);
	}

	/**
	 * Check if the ImmutableList object is empty or not.
	 * <p>
	 * @return true if the ImmutableList is empty. Otherwise, return false.
	 */
	public boolean isEmpty()
	{
	    return (this.lstSize == 0);
	}

	/**
	 * Return the reverse of original generic ImmutableList object.
	 */
	@SuppressWarnings ("unchecked")
	private ImmutableList<E> reverse()
	{
	    ImmutableList<E> revList = emptyList();
	    ImmutableList<E> tail = this;
	    while (!tail.isEmpty())
	    {
		revList = revList.add(tail.frontNode);
		tail = tail.endNode;
	    }
	    return revList;
	}
    }
}
