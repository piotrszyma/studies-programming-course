package com.company;

import java.util.List;
import java.util.Vector;

/**
 * Klasa terminal obsługuje komendy podawane do serwera od klienta
 */
public class Terminal {

    private List<BinarySearchTree> trees_list = new Vector<>();
    private List<String> trees_names = new Vector<>();
    private List<String> trees_types = new Vector<>();


    /**
     * Konstruktor obiektu klasy terminal
     */

    public Terminal() {

    }

    /**
     * Metoda obsługująca drzewo BST, interpretuje komendy podane jako parametry
     * @param command Komendy podawane przez klienta jako Stringi
     */

    public void line(String command) {

        List<String> commands_list = new Vector<>();
        while (command.length() != 0) {
            if (command.charAt(0) != ' ') {
                if (command.contains(" ")) {
                    commands_list.add(command.substring(0, command.indexOf(" ")));
                    command = command.substring(command.indexOf(" ") + 1);
                } else {
                    commands_list.add(command.toLowerCase());
                    command = "";
                }
            } else {
                command = command.substring(1);
            }
        }

        if (commands_list.get(0).equals("new")) {
            if (commands_list.size() == 1) {
                System.out.println("Podałeś za mało argumentów! Wpisz new <typ zmiennej> <nazwa drzewa>");
                return;
            } else if (commands_list.get(1).equals("int")) {

                String tree_name = commands_list.get(2);
                if (trees_names.contains(tree_name)) {
                    System.out.println("Drzewo o takiej nazwie już istnieje. Podaj inną nazwę");
                    return;
                }

                BinarySearchTree<Integer> bst = new BinarySearchTree<>();
                trees_list.add(bst);
                trees_names.add(tree_name);
                trees_types.add("Integer");
                System.out.println("Nowe drzewo BST stworzone. Nazwa: " + tree_name + " Typ: Integer");
                return;

            } else if (commands_list.get(1).equals("double")) {
                String tree_name = commands_list.get(2);
                if (trees_names.contains(tree_name)) {
                    System.out.println("Drzewo o takiej nazwie już istnieje. Podaj inną nazwę");
                    return;
                }

                BinarySearchTree<Double> bst = new BinarySearchTree<>();
                trees_list.add(bst);
                trees_names.add(tree_name);
                trees_types.add("Double");
                System.out.println("Nowe drzewo BST stworzone. Nazwa: " + tree_name + " Typ: Double");
                return;

            } else if (commands_list.get(1).equals("string")) {
                String tree_name = commands_list.get(2);
                if (trees_names.contains(tree_name)) {
                    System.out.println("Drzewo o takiej nazwie już istnieje. Podaj inną nazwę.");
                    return;
                }

                BinarySearchTree<String> bst = new BinarySearchTree<>();
                trees_list.add(bst);
                trees_names.add(tree_name);
                trees_types.add("String");
                System.out.println("Nowe drzewo BST stworzone. Nazwa: " + tree_name + " Typ: String");
                return;

            } else {
                System.out.println("Nieobsługiwany typ. (" + commands_list.get(1) + ") Dozwolone: int, double, string.");
                return;
            }
        } else {
            if (trees_names.contains(commands_list.get(0))) {
                String treename = commands_list.get(0).toString();
                BinarySearchTree tree = trees_list.get(numInList(treename));
                String treetype = trees_types.get(numInList(treename));

                if (commands_list.get(1).equals("add")) {
                    if(commands_list.size()==2)
                    {
                        System.out.println("Podałeś za mało argumentów! Wpisz new <typ zmiennej> <nazwa drzewa>");
                        return;
                    }
                    else if (treetype.equals("Integer")) {
                        for (int i = 2; i < commands_list.size(); i++) {
                            try {
                                tree.add(Integer.parseInt(commands_list.get(i)));
                            } catch (IllegalArgumentException e) {
                                System.out.println("Zły format danych! (" + commands_list.get(i) + ")");
                            }
                        }
                        return;


                    } else if (treetype.equals("Double")) {
                        for (int i = 2; i < commands_list.size(); i++) {
                            try {
                                tree.add(Double.parseDouble(commands_list.get(i)));
                            } catch (IllegalArgumentException e) {
                                System.out.println("Zły format danych! (" + commands_list.get(i) + ")");
                            }
                        }
                        return;

                    } else if (treetype.equals("String")) {
                        for (int i = 2; i < commands_list.size(); i++) {
                            try {
                                tree.add(commands_list.get(i));
                            } catch (IllegalArgumentException e) {
                                System.out.println("Zły format danych! (" + commands_list.get(i) + ")");
                            }
                        }

                        return;

                    } else {
                        System.out.println("Błąd");
                    }
                } else if (commands_list.get(1).equals("remove")) {

                    if (treetype.equals("Integer")) {
                        for (int i = 2; i < commands_list.size(); i++) {
                            try {
                                if(tree.remove(Integer.parseInt(commands_list.get(i))))
                                {
                                    System.out.println("Obiekt usunięty. (" + commands_list.get(i) + ")");
                                } else
                                {
                                    System.out.println("Brak takiego obiektu w drzewie. (" + commands_list.get(i) + ")");
                                }
                            } catch (IllegalArgumentException e) {
                                System.out.println("Zły format danych! (" + commands_list.get(i) + ")");
                            }
                        }
                        return;

                    } else if (treetype.equals("Double")) {
                        for (int i = 2; i < commands_list.size(); i++) {
                            try {
                                if(tree.remove(Double.parseDouble(commands_list.get(i))))
                                {
                                    System.out.println("Obiekt usunięty. (" + commands_list.get(i) + ")");
                                } else
                                {
                                    System.out.println("Brak takiego obiektu w drzewie. (" + commands_list.get(i) + ")");
                                }
                            } catch (IllegalArgumentException e) {
                                System.out.println("Zły format danych! (" + commands_list.get(i) + ")");
                            }
                        }
                        return;

                    } else if (treetype.equals("String")) {
                        for (int i = 2; i < commands_list.size(); i++) {
                            try {
                                if(tree.remove(commands_list.get(i)))
                                {
                                    System.out.println("Obiekt usunięty. (" + commands_list.get(i) + ")");
                                } else
                                {
                                    System.out.println("Brak takiego obiektu w drzewie. (" + commands_list.get(i) + ")");
                                }
                            } catch (IllegalArgumentException e) {
                                System.out.println("Zły format danych! (" + commands_list.get(i) + ")");
                            }
                        }
                        return;

                    } else {
                        System.out.println("Błąd");
                    }

                } else if (commands_list.get(1).equals("find")) {
                    if (treetype.equals("Integer")) {
                        try {
                            if(tree.find(Integer.parseInt(commands_list.get(2))))
                            {
                                System.out.println("Brak tego elementu w drzewie (" + commands_list.get(2) + ")");
                            } else
                            {
                                System.out.println("Znaleziono element w drzewie (" + commands_list.get(2) + ")");
                            }

                        } catch (IllegalArgumentException e) {
                            System.out.println("Zły format danych!");
                        }

                    } else if (treetype.equals("Double")) {
                        try {
                            tree.find(Double.parseDouble(commands_list.get(2)));
                        } catch (IllegalArgumentException e) {
                            System.out.println("Zły format danych!");
                        }

                    } else if (treetype.equals("String")) {
                        try {
                            tree.find(commands_list.get(2).toLowerCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Zły format danych!");
                        }

                    } else {
                        System.out.println("Błąd");
                    }

                } else if (commands_list.get(1).equals("inorder") || commands_list.get(1).equals("draw")) {
                    tree.inOrder();
                } else if (commands_list.get(1).equals("preorder")) {
                    tree.preOrder();
                } else if (commands_list.get(1).equals("postorder")) {
                    tree.postOrder();
                } else
                {
                    System.out.println("Nieznane polecenie!");
                }
            } else {
                System.out.println("Brak drzewa o takiej nazwie (" + commands_list.get(0) + ")");

            }

        }
    }


    /**
     * Metoda zwraca numer elementu na liście z drzewami drzewa o danej nazwie
     * @param name Nazwa drzewa, którego numer jest poszukiwany
     * @return Zwracana wartość w postaci liczby typu int
     */
    private int numInList(String name) {
        for (int i = 0; i < trees_names.size(); i++) {
            if (trees_names.get(i).equals(name)) {
                return i;
            }
        }

        return -1;
    }
}




/*
    new int nazwa_drzewa
    nazwa_drzewa add [obiekt]
    nazwa_drzewa remove [obiekt]
    nazwa_drzewa find [obiekt]
    nazwa_drzewa inorder
    nazwa_drzewa preorder
    nazwa_drzewa postorder




 */