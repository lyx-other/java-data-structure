package com.lyx.linkedlist;

import java.util.Objects;

public class DoubleLinkedListDemo
{
	public static void main(String[] args)
	{
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
		doubleLinkedList.add(new LOLHero(1,"拉莫斯","披甲龙龟"));
		doubleLinkedList.add(new LOLHero(2,"巨魔之王","特朗德尔"));
		doubleLinkedList.add(new LOLHero(3,"战争之影","赫卡里姆"));

		doubleLinkedList.list();

		doubleLinkedList.delete(3);

		doubleLinkedList.list();
	}
}

/**
 * 双向链表对象
 */
class DoubleLinkedList
{
	private LOLHero head;

	public DoubleLinkedList()
	{
		this.head = new LOLHero(0,null, null);
	}

	// 在双向链表的最后添加一个节点
	public void add(LOLHero node)
	{
		LOLHero current = this.head;
		while (Objects.nonNull(current.next))
			current = current.next;

		current.next = node;
		node.pre = current;
	}

	// 根据 no 删除某个节点，返回删除的节点
	public LOLHero delete(int no)
	{
		if (this.isEmpty())
		{
			System.out.println("链表为空");
			return null;
		}

		LOLHero curr = head;
		while (true)
		{
			curr = curr.next;

			if (curr.no == no)
			{
				if (Objects.isNull(curr.next)) // 删除的是最后一个节点
				{
					curr.pre.next = null; // curr.net

					curr.pre = null;
					return curr;
				}
				else // 删除的不是最后一个节点
				{
					curr.pre.next = curr.next;
					curr.next.pre = curr.pre;

					curr.next = null;
					curr.pre = null;
					return curr;
				}
			}

			if (Objects.isNull(curr.next))
				break;
		}

		System.out.println("节点不存在");
		return null;
	}

	// 修改某个节点 根据节点的no值，会修改第1个
	public void change(LOLHero node)
	{
		if (isEmpty())
		{
			System.out.println("链表为空，无法修改");
			return;
		}

		LOLHero curr = head;
		while (true)
		{
			curr = curr.next;
			if (curr.no == node.no)
			{
				curr.name = node.name;
				curr.nickname = node.nickname;
			}

			if (Objects.isNull(curr.next)) // 最后一个节点
				break;
		}
	}

	// 双向链表的遍历
	public void list()
	{
		if (this.isEmpty())
		{
			System.out.println("双向链表为空");
			return;
		}

		LOLHero current = this.head;
		while (Objects.nonNull(current.next))
		{
			current = current.next;
			System.out.println(current);
		}
		System.out.println("------------------------");
	}

	// 判断列表是否为空
	public boolean isEmpty()
	{
		return Objects.isNull(head.next);
	}
}

/**
 * 节点对象
 * 用单链表来存储LOL浒英雄
 * 一个节点就是一个LOL英雄
 */
class LOLHero
{
	// 数据区
	public int no; // 英雄的编号
	public String name; // 英雄的名字
	public String nickname; // 英雄的昵称

	public LOLHero pre; // 上一个节点
	public LOLHero next; // 下一个节点

	// 构造一个节点(一个英雄)
	public LOLHero(int no, String name, String nickname)
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