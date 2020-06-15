package com.lyx.linkedlist;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.time.chrono.IsoChronology;
import java.util.Objects;
import java.util.Stack;

/**
 * 单向链表demo
 */
public class SingleLinkedListDemo
{
	public static void main(String[] args)
	{
		SingleLinkedList linkedList = new SingleLinkedList();
		linkedList.addByNo(new HeroNode(1, "宋江", "及时雨"));
		linkedList.addByNo(new HeroNode(2, "吴用", "智多星"));
		linkedList.addByNo(new HeroNode(3, "杨志", "青面兽"));
		linkedList.addByNo(new HeroNode(4, "晁盖", "托塔天王"));
		linkedList.addByNo(new HeroNode(5, "烬", "戏命师"));
		linkedList.addByNo(new HeroNode(6, "凯隐", "影流之镰"));
		linkedList.addByNo(new HeroNode(7, "拉克丝", "光辉女郎"));
		linkedList.addByNo(new HeroNode(8, "慎", "暮光之眼"));

		System.out.println("单向链表：");
		linkedList.list();
		System.out.println("--------------");

		linkedList.reverse();

		System.out.println("单向链表：");
		linkedList.list();
		System.out.println("--------------");
	}

	/*-------------面试题-------------*/
	/**
	 * 获得单向链表中节点的个数(不算头结点)
	 * @param singleLinkedList 单链表对象，也可以以头结点为参数，一个头节点也可以代表一个单向链表
	 * @return
	 */
	public static int count(SingleLinkedList singleLinkedList)
	{
		int result = 0;
		HeroNode current = singleLinkedList.getHead();

		if (Objects.isNull(current.next))
			return result;

		while (true)
		{
			current = current.next;
			result++;
			if (Objects.isNull(current.next))
				break;
		}

		return result;
	}

	/**
	 * 获得单向链表的倒数第k个节点，节点从1开始编号
	 */
	public static HeroNode reciprocalK(SingleLinkedList list, int k)
	{
		int target = count(list) + 1 - k; // 倒数第k个就是正数 第 count+1-k 个（头结点不算，从1开始编号）
		int n = 0; // 记录是第几个节点
		HeroNode current = list.getHead();
		while (Objects.nonNull(current.next))
		{
			current = current.next; // current 被赋值一次 就是一个节点
			n++;
			if (n == target)
				return current;
		}

		return null;
	}

	/**
	 * 逆序打印
	 * 方式一：反转单链表，打印完后，再返回回来
	 */
	public static void reversePrint1(SingleLinkedList list)
	{
		list.reverse();
		list.list();
		list.reverse();
	}

	/**
	 * 逆序打印
	 * 方式2：使用栈，将单向链表中的节点按顺序压入栈中，利用栈先进后出的特性完成逆序打印
	 * 这里先使用Java提供的栈
	 */
	public static void reversePrint2(SingleLinkedList list)
	{
		if (list.isEmpty())
		{
			System.out.println("单向链表为空");
			return;
		}

		Stack<HeroNode> stack = new Stack<>();

		HeroNode current = list.getHead().next;
		while (Objects.nonNull(current))
		{
			stack.add(current);
			current = current.next;
		}

		while (!stack.isEmpty())
			System.out.println(stack.pop());

	}

	public static SingleLinkedList merge(SingleLinkedList l1, SingleLinkedList l2)
	{
		if (l1.isEmpty() && l2.isEmpty())
			return new SingleLinkedList();

		// 使用有序的添加方法往里添加就可以了
		SingleLinkedList linkedList = new SingleLinkedList();
		HeroNode currentL1 = l1.getHead();
		while (Objects.nonNull(currentL1.next))
		{
			currentL1 = currentL1.next;
			linkedList.addByNo(new HeroNode(currentL1.no, currentL1.name, currentL1.nickname));
		}
		HeroNode currentL2 = l2.getHead();
		while (Objects.nonNull(currentL2.next))
		{
			currentL2 = currentL2.next;
			linkedList.addByNo(new HeroNode(currentL2.no, currentL2.name, currentL2.nickname));
		}

		return linkedList;
	}
}

/**
 * 单向链表对象
 */
class SingleLinkedList
{
	// 头结点，不存放具体数据
	private HeroNode head;

	public SingleLinkedList()
	{
		this.head = new HeroNode(0, null, null);
	}

	public HeroNode getHead()
	{
		return head;
	}

	// 添加节点，添加到最后
	public void add(HeroNode heroNode)
	{
		// temp辅助变量，指向最后一个节点
		HeroNode temp = this.head; // 先让temp指向头节点
		while (!Objects.isNull(temp.next)) // 如果存在下一个节点
			temp = temp.next;

		temp.next = heroNode;
	}

	// 添加节点时，按英雄的编号从小到大排列，如果这个编号已存在就提示添加失败
	public void addByNo(HeroNode node)
	{
		// 如果队列为空就直接添加
		if (this.isEmpty())
		{
			this.head.next = node;
			return;
		}

		// 判断是否可以添加
		HeroNode temp = this.head; // temp指向第头结点
		while (!Objects.isNull(temp.next)) // 遍历
		{
			temp = temp.next;
			if (temp.no == node.no)
			{
				System.out.println("要添加的排名 " + node.no + " 已存在，无法添加");
				return;
			}
		}

		// 添加
		// 运行到这里，新的结点与原来的结点相比  要么大、要么小
		// 在添加要是我们要找的是 要添加位置的前一个节点
		temp = this.head; // temp指向第头结点
		while (!Objects.isNull(temp.next))
		{
			if (node.no < temp.next.no) // 如要添加节点的值 < temp下一个节点的值，那么就说明新的节点应该添加在temp后边。因为temp刚开始是指向head，所以如果是应该添加在第一个节点前边，也是可以实现的。
			{
				node.next = temp.next;
				temp.next = node;
				return;
			}
			temp = temp.next;
		}

		// 运行到这里，说明没有添加上，即新的结点比所有结点都大，此时temp指向最后一个节点，在最后一个节点添加上
		node.next = null;
		temp.next = node;
	}

	// 删除某个节点
	public void removeByNo(int no)
	{
		if (this.isEmpty())
		{
			System.out.println("链表为空，无法修改");
			return;
		}

		HeroNode current = this.head;
		while (!Objects.isNull(current.next)) // 如果有下一个节点
		{
			if (current.next.no == no) // 和下一节点比较  如果相等，当前结点的下一个就是要被删除的
			{
				// 被删除的节点没有任何引用指向它，将自动被GC回收
				current.next = current.next.next;
				return;
			}

			current = current.next;
		}

		System.out.println("节点不存在，无需删除");
	}

	// 根据no修改某个节点
	public void updateByNo(HeroNode heroNode)
	{
		if (this.isEmpty())
		{
			System.out.println("链表为空，无法修改");
			return;
		}

		HeroNode current = this.head;
		while (!Objects.isNull(current.next))
		{
			current = current.next;
			if (current.no == heroNode.no)
			{
				current.name = heroNode.name;
				current.nickname = heroNode.nickname;
				return;
			}
		}

		System.out.println("没有找到节点");
	}

	// 遍历链表
	public void list()
	{
		if (this.isEmpty())
		{
			System.out.println("链表为空");
			return;
		}

		// temp辅助变量
		HeroNode temp = this.head; // 先让temp指向头节点
		while (!Objects.isNull(temp.next)) // 如果存在下一个节点
		{
			temp = temp.next; // 移到下一个节点
			System.out.println(temp);
		}
	}

	// 反转单向链表
	public void reverse()
	{
		// 至少有两个节点（不算头节点）
		if (Objects.isNull(this.head.next) || Objects.isNull(this.head.next.next))
			return;

		HeroNode reverseHead = new HeroNode(0, null, null);

		HeroNode current = head;
		HeroNode currentNext = current.next;

		while (true)
		{
			// 两个辅助指针都往后移一位 后边紧挨着处理，后移一次 就处理一次
			current = currentNext;
			currentNext = currentNext.next;

			// 把当前节点摘下来，插入到 紧挨reverseHead的后边
			current.next = reverseHead.next;
			reverseHead.next = current;

			if (Objects.isNull(currentNext)) // currentNext 为 null ，说明current移动到了最后一个节点，就不往后再移动了
				break;
		}

		// 用原先的头节点 替换掉 reverseHead
		head.next = reverseHead.next;
	}

	// 判断链表是否为空
	public boolean isEmpty()
	{
		return Objects.isNull(this.head.next);
	}
}

/**
 * 节点对象
 * 用单链表来存储水水浒英雄
 * 一个节点就是一个水浒英雄
 */
class HeroNode
{
	// 数据区
	public int no; // 英雄的编号
	public String name; // 英雄的名字
	public String nickname; // 英雄的昵称
	// 下一个结点
	public HeroNode next;

	// 构造一个节点(一个英雄)
	public HeroNode(int no, String name, String nickname)
	{
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}

	@Override
	public String toString()
	{
		return "HeroNode{" +
				"no=" + no +
				", name='" + name + '\'' +
				", nickname='" + nickname + '\'' +
				'}';
	}
}