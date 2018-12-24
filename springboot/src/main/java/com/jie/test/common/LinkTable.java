package com.jie.test.common;

import lombok.Data;

@Data
public class LinkTable {

    private Node first;
    private Node last;

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
        this.last=first;
        this.first=node;
        this.first.next=last;
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

    public static void main(String[] args) {
        LinkTable l=new LinkTable();
        l.add(0);
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.listData();
    }
}
