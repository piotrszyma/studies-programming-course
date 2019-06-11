#include <iostream>
#include <typeinfo>
#include <algorithm>

using namespace std;

template<class T>
class BinarySearchTree {

public:

	class Node {
	public:
		T data;
		Node *left;
		Node *right;

		Node(T data) {
			this->data = data;
			this->left = nullptr;
			this->right = nullptr;
		}

		~Node() {}
	};

	Node *root;

	~BinarySearchTree() {
		removeTree();
	}

	BinarySearchTree() {
		root = nullptr;
	}

	BinarySearchTree(T object)
	{
		root = new Node(object);
	}

	void add(T object) {
		Node *newNode = new Node(object);

		if (root == nullptr) {
			root = newNode;
			return;
		}

		Node *parent = root;
		while (true) {
			if (Compare(newNode->data, parent->data) == 1) {
				if (parent->right == nullptr) {
					parent->right = newNode;
					return;
				} else {
					parent = parent->right;
				}
			} else if (Compare(newNode->data, parent->data) == -1) {
				if (parent->left == nullptr) {
					parent->left = newNode;
					return;
				} else {
					parent = parent->left;
				}
			} else {
				cout << "Ten element zostal juz dodany do drzewa. (" << newNode->data << ")" << endl;
				delete newNode;
				return;
			}
		}
	}

	void remove(T object) {
		if (root == nullptr) {
			cout << "Brak takiego obiektu w drzewie. (" << object <<")" << endl;
			return;
		}

		if (check(object) == nullptr) {
			cout << "Brak takiego elementu w drzewie. (" << object << ")"<< endl;
			return;
		}

		Node *toremove = check(object);
		Node *parent = findParent(toremove);


		if (toremove->left == nullptr && toremove->right == nullptr) {
			if (parent == nullptr) {
				delete root;
				root = nullptr;
			}
			else if (parent->left == toremove) {
				delete parent->left;
				parent->left = nullptr;

			} else {
				delete parent->right;
				parent->right = nullptr;
			}
		} else if (toremove->left != nullptr && toremove->right != nullptr) {
			Node *rightSubtree = toremove->right;
			T tmp = minValue(rightSubtree)->data;
			remove(tmp);
			toremove->data = tmp;
			delete rightSubtree;
			delete toremove;
			return;
		} else if (toremove->right != nullptr) {
			if (parent == nullptr) {
				root = toremove->right;
			}
			else if (parent->left == toremove) {
				parent->left = toremove->right;
			} else {

				parent->right = toremove->right;
			}
			delete toremove;
			return;
		} else {
			if (parent == nullptr) {
				root = toremove->left;
			}
			else if (parent->left == toremove) {
				parent->left = toremove->left;
			} else {
				parent->right = toremove->left;
			}

			delete toremove;
			return;
		}
	}

	void find(T object) {
		if (check(object) == nullptr) {
			cout << "Brak tego elementu w drzewie. (" << object << ")" << endl;
		} else {
			cout << "Znaleziono element w drzewie. (" << object << ")" << endl;
		}
	}

	void showPreOrder() {
		if (root == nullptr) {
			cout << "Drzewo jest puste!" << endl;
		} else {
			cout << "Wypisuje drzewo: " << endl;
			showRecursPreOrder(root);
		}
	}

	void showPostOrder() {
		if (root == nullptr) {
			cout << "Drzewo jest puste!" << endl;
		} else {
			cout << "Wypisuje drzewo: " << endl;
			showRecursPostOrder(root);
		}
	}

	void showInOrder() {
		if (root == nullptr) {
			cout << "Drzewo jest puste!" << endl;
		} else {
			cout << "Wypisuje drzewo: " << endl;
			showRecursInOrder(root);
		}
	}

	void removeTree() {
		if(root==nullptr) return;

		deleteRecurs(root);
		return;
	}

private:
	void deleteRecurs(Node *node) {
		if (node->left != nullptr) deleteRecurs(node->left);
		if (node->right != nullptr) deleteRecurs(node->right);
		delete node;
		return;

	}

	void showRecursPreOrder(Node *node) {
		cout << node->data << endl;
		if (node->left != nullptr) showRecursPreOrder(node->left);
		if (node->right != nullptr) showRecursPreOrder(node->right);
		return;

	}
	void showRecursPostOrder(Node *node) {
		if (node->left != nullptr) showRecursPostOrder(node->left);
		if (node->right != nullptr) showRecursPostOrder(node->right);
		cout << node->data << endl;
		return;

	}
	void showRecursInOrder(Node *node) {
		if (node->left != nullptr) showRecursInOrder(node->left);
		cout << node->data << endl;
		if (node->right != nullptr) showRecursInOrder(node->right);
		return;

	}

	int Compare(T a, T b) {

		if ((typeid(a).name() == typeid(string).name())) {

			return isFirst(a, b);

		} else {
			if (a > b) return 1;
			else if (a < b) return -1;
			else return 0;
		}

	}

	int isFirst(int a, int b) {
		return a > b ? 1 : -1;
	}

	int isFirst(string tab1, string tab2) {
		int iter = 0;

		transform(tab1.begin(), tab1.end(), tab1.begin(), ::tolower);
		transform(tab2.begin(), tab2.end(), tab2.begin(), ::tolower);

		while (tab1[iter] == tab2[iter] && tab1[iter] != '\0') {
			iter++;
		}
		if (tab1[iter] == '\0' && tab2[iter] == '\0') {
			return 0;
		}
		else if (tab1[iter] == '\0') {
			return -1;
		}
		else if (tab1[iter] < tab2[iter]) {
			return -1;
		}
		else {
			return 1;
		}

	}

	Node *check(T object) {
		Node *current = root;
		while (true) {
			if (current == nullptr) {

				return nullptr;
			} else if (Compare(object, current->data) == 0) {

				return current;
			} else if (Compare(object, current->data) == 1) {
				current = current->right;

			} else {
				current = current->left;

			}
		}
	}

	Node *minValue(Node *node) {
		while (node->left != nullptr) {
			node = node->left;
		}
		return node;
	}

	Node *findParent(Node *node) {
		Node *parent = nullptr;
		Node *current = root;
		while (true) {
			if (Compare(node->data, current->data) == 1) {
				parent = current;
				current = current->right;
			} else if (Compare(node->data, current->data) == -1) {
				parent = current;
				current = current->left;
			} else {
				return parent;
			}
		}
	}


};

int main() {



	BinarySearchTree<long> *tree2 = new BinarySearchTree<long>;


	tree2->add(1);
	tree2->add(12);
	tree2->add(13);
	tree2->add(13);
	//tree2->add(13);
	//tree2->remove(12);
	tree2->remove(1);
	//tree2->add(2);
	//tree2->add(3);
	//tree2->add(4);

	//tree2->remove(1);

	tree2->showInOrder();

	delete tree2;
	//free(tree2);


	return 0;
}