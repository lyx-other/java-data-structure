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
		singleLinkedList.addByNo(new HeroNode(3, "吴用", "智多星"));
		singleLinkedList.addByNo(new HeroNode(2, "杨志", "青面兽"));
		singleLinkedList.addByNo(new HeroNode(4, "晁盖", "托塔天王"));

		singleLinkedList.list();

		singleLinkedList.updateByNo(new HeroNode(4, "大晃盖","托塔大天王"));

		System.out.println("-------------------");
		singleLinkedList.list();
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