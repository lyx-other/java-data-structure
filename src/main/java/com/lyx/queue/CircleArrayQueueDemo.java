package com.lyx.queue;

import java.util.Scanner;

/**
 * 数组实现环形队列
 */
public class CircleArrayQueueDemo
{
	public static void main(String[] args)
	{
		CircleArrayQueue queue = new CircleArrayQueue(4);

		Scanner scanner = new Scanner(System.in);

		boolean loop = true;
		while (loop)
		{
			System.out.println("-----------------------");
			System.out.println("s(show)：显示队列");
			System.out.println("h(hear)：获得队列头元素");
			System.out.println("g(get)：获得队列元素，出队列");
			System.out.println("a(add)：向队列中添加元素");
			System.out.println("p(point)：打印指针位置");
			System.out.println("e(exit)：退出系统");
			System.out.println("-----------------------");


			switch (scanner.next().charAt(0))
			{
				case 's':
					System.out.println("队列：");
					queue.showQueue();
					break;
				case 'h':
					try
					{
						System.out.println("头元素：" + queue.headQueue());
					}
					catch (Exception e)
					{
						System.out.println(e.getMessage());
					}
					break;
				case 'g':
					try
					{
						System.out.println("出队列：" + queue.getQueue());
					}
					catch (Exception e)
					{
						System.out.println(e.getMessage());
					}
					break;
				case 'a':
					System.out.print("请输入添加的元素：");
					queue.addQueue(scanner.nextInt());
					System.out.println("队列：");
					queue.showQueue();
					break;
				case 'p':
					queue.printFrontRear();
					break;
				case 'e':
					scanner.close();
					loop = false;
					break;
				default:
					System.out.println("输入无效");
			}
		}

		System.out.println("程序退出");
	}
}

/**
 * 环形队列
 */
class CircleArrayQueue
{
	private int maxSize; // 数组的最大容量
	private int front; // 头指针
	private int rear; // 尾指针
	private int[] arr; // 用于存储数据，模拟的队列，就是它

	public CircleArrayQueue(int arrMaxSize)
	{
		 maxSize = arrMaxSize;
		 arr = new int[maxSize];
	}

	/**
	 * 判断队列是否满
	 * @return true-满，false-没满
	 */
	public boolean isFull()
	{
		return (rear+1) % maxSize == front;
	}

	/**
	 * 判断队列是否空
	 * @return true-空，false-未空
	 */
	public boolean isEmpty()
	{
		return front == rear;
	}

	/**
	 * 添加数据到队列
	 */
	public void addQueue(int n)
	{
		if (isFull())
		{
			System.out.println("队列满，不能加入数据");
			return;
		}

		arr[rear] = n;
		rear = (rear+1) % maxSize; // rear后移必须考虑取模
	}

	/**
	 * 出队列
	 */
	public int getQueue()
	{
		if (isEmpty())
		{
			throw new RuntimeException("队列为空，没法取数据");
		}

		int value = arr[front];
		front = (front+1) % maxSize;

		return value;
	}

	/**
	 * 显示队列的所有数据
	 */
	public void showQueue()
	{
		if (isEmpty())
		{
			System.out.println("队列为空");
			return;
		}

		int count = (rear-front+maxSize) % maxSize; // 队列中元素的个数

		for (int i = front; i <= front+count-1; i++)
		{
			System.out.println("arr["+(i%maxSize)+"]" + " = " + arr[i%maxSize]);
		}
	}

	/**
	 * 获得队列的头元素，注意，不是出队列
	 */
	public int headQueue()
	{
		if (isEmpty())
		{
			throw new RuntimeException("队列空");
		}

		return arr[front];
	}

	/**
	 * 输出指针
	 */
	public void printFrontRear()
	{
		System.out.println("front：" + front);
		System.out.println("rear：" + rear);
	}
}