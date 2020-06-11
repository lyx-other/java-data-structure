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
	}

	// 判断列表是否为空
	public boolean isEmpty()
	{
		return Objects.isNull(this.head.next);
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