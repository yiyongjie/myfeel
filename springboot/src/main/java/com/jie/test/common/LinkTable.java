package com.jie.test.common;

import lombok.Data;

@Data
public class LinkTable {

    private Node first;
    private int position=0;

    @Data
    class Node{
        private Node next;
        private int data;

        public Node(int data) {
            this.data = data;
        }
    }

    public void add(int data){
        Node node=new Node(data);
        node.next=first;
        this.first=node;
        listData();
    }

    public void listData(){
        StringBuffer sb=new StringBuffer();
        sb.append(first.data).append(",");
        Node node=first.next;
        while (node!=null){
            sb.append(node.data).append(",");
            node=node.next;
        }
        System.out.println(sb.toString());
    }

    public void addToPosition(int index,int data){
        Node node=new Node(data);
        Node preNode=first;
        while (position!=index){
            preNode=preNode.next;
            position++;
        }
        Node agoNode=preNode.next;
        preNode.next=node;
        preNode.next.next=agoNode;
        listData();
    }

    public static void main(String[] args) {
        LinkTable l=new LinkTable();
        l.add(0);
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(5);
        l.add(6);
        l.addToPosition(1,4);
    }
}
