package company;

import java.io.Serializable;

/**
 * Klasa implementująca drzewo BST
 * @author Piotr Szyma
 * @version 1.0
 */

public class BinarySearchTree<T> implements Serializable {

    public Node root = null;

    /**
     * Klasa będąca strukturą drzewa binarnego
     * @param <T> Typ sparametryzowany obiektów przechowywanych w drzewie
     */

    public class Node<T> {
        T data;
        Node left;
        Node right;

        Node(T input) {
            this.data = input;
            left = null;
            right = null;
        }
    }

    /**
     * Funkcja dodająca pojedynczy obiekt do drzewa
     * @param object Obiekt do dodania.
     */

    public void add(T object) {
        Node newNode = new Node<>(object);

        if (root == null) {
            root = newNode;
            System.out.println("Dodano nowy element do drzewa (" + object + ")");
            return;
        }

        Node parent = root;

        while (true) {
            if (Compare(newNode.data, parent.data) == 1) {
                if (parent.right == null) {
                    parent.right = newNode;
                    System.out.println("Dodano nowy element do drzewa (" + object + ")");
                    return;
                } else {
                    parent = parent.right;
                }
            } else if (Compare(newNode.data, parent.data) == -1) {
                if (parent.left == null) {
                    parent.left = newNode;
                    System.out.println("Dodano nowy element do drzewa (" + object + ")");
                    return;
                } else {
                    parent = parent.left;
                }
            } else {
                System.out.println("Ten element został już dodany do drzewa. (" + newNode.data + ")");
                return;
            }
        }

    }

    /**
     * Funkcja dodająca tablicę obiektów do drzewa
     * @param object Tablica obiektów do dodania.
     */

    public void addAll(T... object) {
        for(T i : object)
        {
            add(i);
        }
    }

    /**
     * Funkcja usuwająca obiekt z drzewa.
     * @param object Obiekt do usunięcia.
     */

    public void remove(Object object){
        if (root == null) {
            System.out.println("Brak takiego obiektu w drzewie. (" + object + ")");
            return;
        }

        if (check(object) == null) {
            System.out.println("Brak takiego elementu w drzewie. (" + object + ")");
            return;
        }

        Node remove = check(object);
        Node parent = findParent(remove);



        if (remove.left == null && remove.right == null) {
            if(parent==null)
            {
                root = null;
            }
            else if (parent.left == remove) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            System.out.println("Obiekt usunięty. (" + object + ")");
            return;
        } else if (remove.left != null && remove.right != null) {
            Node rightSubtree = remove.right;
            Object tmp = minValue(rightSubtree).data;
            remove(tmp);
            remove.data = tmp;
            return;

        } else if (remove.right != null) {
            if(parent==null)
            {
                root = remove.right;
            }
            else if (parent.left == remove) {
                parent.left = remove.right;
            } else {
                parent.right = remove.right;
            }
            System.out.println("Obiekt usunięty. (" + object + ")");
            return;
        } else
        {
            if(parent==null)
            {
                root = remove.left;
            }
            else if (parent.left == remove) {
                parent.left = remove.left;
            } else {
                parent.right = remove.left;
            }


            System.out.println("Obiekt usunięty. (" + object + ")");
            return;
        }
    }

    /**
     * Funkcja szukająca danego obiektu w drzewie.
     * @param object Obiekt do odnalezienia.
     */

    public void find(Object object) {
        if (check(object) == null) {
            System.out.println("Brak tego elementu w drzewie (" + object + ")");
        } else {
            System.out.println("Znaleziono element w drzewie (" + object + ")");
        }
    }

    /**
     * Funkcja wypisująca drzewo w porządku inOrder.
     */

    public void inOrder() {

        if (root == null) {
            System.out.println("Drzewo jest puste!");
        } else {
            System.out.println("Wypisuję drzewo (inOrder): ");
            inOrderRecurs(root);
        }

    }

    /**
     * Funkcja wypisująca drzewo w porządku preOrder.
     */

    public void preOrder() {

        if (root == null) {
            System.out.println("Drzewo jest puste!");
        } else {
            System.out.println("Wypisuję drzewo (preOrder): ");
            preOrderRecurs(root);
        }

    }

    /**
     * Funkcja wypisująca drzewo w porządku postOrder.
     */

    public void postOrder() {

        if (root == null) {
            System.out.println("Drzewo jest puste!");
        } else {
            System.out.println("Wypisuję drzewo (postOrder): ");
            postOrderRecurs(root);
        }

    }

    /**
     * Funkcja pomocnicza do rekursywnego pokazania drzewa w porządku inOrder
     * @param node Korzeń od którego zaczynamy
     */

    private void inOrderRecurs(Node node) {
        if (node.left != null) inOrderRecurs(node.left);
        System.out.println(node.data);
        if (node.right != null) inOrderRecurs(node.right);
        return;
    }

    /**
     * Funkcja pomocnicza do rekursywnego pokazania drzewa w porządku preOrder
     * @param node Korzeń od którego zaczynamy
     */

    private void preOrderRecurs(Node node) {
        System.out.println(node.data);
        if (node.left != null) preOrderRecurs(node.left);
        if (node.right != null) preOrderRecurs(node.right);
        return;
    }

    /**
     * Funkcja pomocnicza do rekursywnego pokazania drzewa w porządku postOrder
     * @param node Korzeń od którego zaczynamy
     */

    private void postOrderRecurs(Node node) {
        if (node.left != null) preOrderRecurs(node.left);
        if (node.right != null) preOrderRecurs(node.right);
        System.out.println(node.data);
        return;
    }

    /**
     * Funkcja pomocnicza sprawdzająca czy dany obiekt występuje w drzewie
     * @param object Obiekt do znalezienia
     * @return Zwraca null jeśli go nie ma, zwraca ten obiekt jeśli występuje.
     */

    private Node check(Object object) {
        Node current = root;
        while (true) {
            if (current == null) {
                return null;
            } else if (Compare(object, current.data) == 0) {
                return current;
            } else if (Compare(object, current.data) == 1) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
    }

    /**
     * Funkcja odnajdująca minimalna wartość w danym poddrzewie
     * @param node Parametr to poczatęk poddrzewa
     * @return Zwraca gałąź najmniejszą
     */

    private Node minValue(Node node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
        //System.out.println(node.data);
    }

    /**
     * Funkcja do odnajdywania rodzica danej gałęzi
     * @param node Gałąź, której rodzica należy znaleźć
     * @return Zwraca adres rodzica
     */

    private Node findParent(Node node) {
        Node parent = null;
        Node current = root;
        while (true) {
            if (Compare(node.data, current.data) == 1) {
                parent = current;
                current = current.right;
            } else if (Compare(node.data, current.data) == -1) {
                parent = current;
                current = current.left;
            } else {
                return parent;
            }
        }
    }

    /**
     * Funkcja porównuje dwa obiekty
     *
     * @param a Pierwszy z obiektów do porównania
     * @param b Drugi z obiektów do porównania
     * @return Zwraca 1 jeśli obiekt a jest większy od b
     */

    private int Compare(Object a, Object b) {
        if (a.getClass().getName().equals("java.lang.String")) {
            if (((String) b).compareTo((String) a) < 0) {
                return 1;
            } else if (((String) b).compareTo((String) a) == 0) {
                return 0;
            } else return -1;

        } else if (a.getClass().getName().equals("java.lang.Integer")) {
            if ((int) a - (int) b > 0) {
                return 1;
            } else if ((int) a - (int) b == 0) {
                return 0;
            } else return -1;
        } else {
            if ((double) a - (double) b > 0) {
                return 1;
            } else if ((double) a - (double) b == 0) {
                return 0;
            } else return -1;
        }
    }
}
