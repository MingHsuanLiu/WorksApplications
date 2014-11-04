/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.worksap.global;

import java.util.NoSuchElementException;

/**
 *
 * @author ming
 */
/**
 * The Queue class represents an immutable first-in-first-out (FIFO) queue of
 * objects.
 * <p>
 * @param <E>
 */
public class ImmutableQueue<E> {

    private static class ImmutableList<E> {

	private final E front;
	private final ImmutableList<E> end;
	private int size;

	private ImmutableList()
	{
	    this.front = null;
	    this.end = null;
	    this.size = 0;
	}

	private ImmutableList(E front, ImmutableList<E> end)
	{
	    this.front = front;
	    this.end = end;
	    this.size = end.size + 1;
	}

	public static ImmutableList emptyList()
	{
	    return new ImmutableList();
	}

	public ImmutableList<E> add(E elem)
	{
	    return new ImmutableList<>(elem, this);
	}

	public boolean isEmpty()
	{
	    return this.size == 0;
	}

	private ImmutableList<E> reverse()
	{
	    ImmutableList<E> revList = new ImmutableList<>();
	    ImmutableList<E> tail = this;
	    while (!tail.isEmpty())
	    {
		revList = revList.add(tail.front);
		tail = tail.end;
	    }
	    return revList;
	}
    }

    private ImmutableList<E> head;
    private ImmutableList<E> tail;

    /**
     * requires default constructor.
     */
    public ImmutableQueue()
    {
	// modify this constructor if necessary, but do not remove default constructor
	this.head = ImmutableList.emptyList();
	this.tail = ImmutableList.emptyList();
    }

    public ImmutableQueue(ImmutableList<E> head, ImmutableList<E> tail)
    {
	this.head = head;
	this.tail = tail;
    }

    // add other constructors if necessary
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
    public ImmutableQueue<E> dequeue()
    {
	if (this.size() == 0)
	{
	    throw new NoSuchElementException();
	}

	if (!this.tail.isEmpty())
	{
	    return new ImmutableQueue<>(this.head, this.tail.end);
	}
	else
	{
	    return new ImmutableQueue<>(ImmutableList.emptyList(), this.head.reverse().end);
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

	return this.tail.front;
    }

    /**
     * Returns the number of objects in this queue.
     * <p>
     * @return
     */
    public int size()
    {
	return this.head.size + this.tail.size;
    }
}