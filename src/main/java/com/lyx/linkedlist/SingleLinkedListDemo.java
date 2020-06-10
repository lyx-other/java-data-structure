package com.lyx.linkedlist;

import java.util.Objects;

/**
 * 单向链表demo
 */
public class SingleLinkedListDemo
{
	public static void main(String[] args)
	{
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		singleLinkedList.addByNo(new HeroNode(1, "宋江", "及时雨"));
		singleLinkedList.addByNo(new HeroNode(2, "吴用", "智多星"));
		singleLinkedList.addByNo(new HeroNode(3, "杨志", "青面兽"));
		singleLinkedList.addByNo(new HeroNode(4, "晁盖", "托塔天王"));
		singleLinkedList.addByNo(new HeroNode(5, "烬", "戏命师"));
		singleLinkedList.addByNo(new HeroNode(6, "凯隐", "影流之镰"));
		singleLinkedList.addByNo(new HeroNode(7, "拉克丝", "光光女郎"));
		singleLinkedList.addByNo(new HeroNode(8, "慎", "暮光之眼"));
		singleLinkedList.list();

		System.out.println("节点个数：" + singleLinkedList.count());
		System.out.println("倒数第5个节点：" + singleLinkedList.reciprocalK(51));
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

	// 判断链表是否为空
	public boolean isEmpty()
	{
		return Objects.isNull(this.head.next);
	}


	/*-------面试题-------*/
	// 统计一个单链表中元素的个数 不算头结点
	public int count()
	{
		int count = 0;

		HeroNode current = this.head;
		while (!Objects.isNull(current.next))
		{
			current = current.next;
			count++;
		}

		return count;
	}

	// 找倒数第k个结点
	public HeroNode reciprocalK(int k)
	{
		int target = this.count() + 1 - k; // 倒数第k个就是正数 第 count+1-k 个（头结点不算，从1开始编号）

		int n = 0; // 记录是第几个节点
		HeroNode current = this.head;
		while (!Objects.isNull(current.next))
		{
			current = current.next;
			n++;
			if (n == target)
				return current;
		}

		return null;
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