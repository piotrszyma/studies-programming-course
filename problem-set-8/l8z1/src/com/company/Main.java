package com.company;

import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Main {

    public static void main(String[] args) {


        BinarySearchTree<Integer> tree2 = new BinarySearchTree<>();

        tree2.add(87);
        tree2.add(2);
        tree2.add(3);
        tree2.add(88);

        tree2.inOrder();

        tree2.remove(87);

        tree2.inOrder();
        tree2.preOrder();
        tree2.postOrder();




        //System.out.println(tree.findParent(tree.find(4)).data);

        //System.out.println(tree.minValue(tree.find(10)).data);
        //tree.show();
        //tree.find(5);

    }
}
