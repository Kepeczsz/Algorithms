#include <iostream>
#include <chrono>

template<typename T>
class LinkedList {
private:
	struct Node {
		T data;
		Node* nextNode = NULL;
	};

public:
	Node* head;
	Node* tail;
	int size;
public:
	LinkedList(T data) {
		head = new Node;
		tail = new Node;
		size = 0;
		head->data = data;
	}
	LinkedList() {
		head = new Node;
		tail = new Node;
		size = 0;
	}

	~LinkedList() {
		delete head;
	}

public:

	void pushFront(T data) {
			if (size == 0) {
				head->data = data;
				tail = head;
				size++;
			}
			else {
				Node* node = new Node;
				node->data = data;
				node->nextNode = head;
				head = node;
				size++;
			}
	}

	void pushBack(T data) {
		if (size == 0) {
			head = new Node;
			tail = new Node;
			head->data = data;
			tail = head;
			size++;
		}
		else {
			Node* node = new Node;
			node->data = data;
			tail->nextNode = node;
			tail = node;
			size++;
		}
	}

	void deleteFirstNode() {
		head = head->nextNode;
		size--;
	}

	void deleteLastNode() {
		Node* last = head;
		Node* secondLast = NULL;

		while (last->nextNode != NULL) {
			secondLast = last;
			last = last->nextNode;
		}
		size--;
		secondLast->nextNode = NULL;
	}

	void print() {
		if (size == 0) {
			std::cout << "There is nothing in Linked List!" << std::endl;
			return;
		}
		Node* last = head;
		while (last) {
			std::cout << last->data << ", ";
			last = last->nextNode;
		}
		std::cout << std::endl;
	}

	void deleteNthNode(int index) {
		int counter = 0;
		Node* prev = NULL;
		Node* next = NULL;
		Node* temp = head;
		Node* iterator = head;

		while (iterator) {
			if (counter == index && counter == 0) {
				head = head->nextNode;
				size--;
				break;
			}
			else if(counter == index) {
				std::cout << "WESZLISMY?! " << next->data << std::endl;
				prev->nextNode = next;
				size--;
				break;
			}
			else if ((temp->nextNode) == NULL) {
				prev->nextNode = NULL;
				size--;
				break;
			}

			prev = temp;
			temp = temp->nextNode;
			if (temp->nextNode != NULL)
				next = temp->nextNode;
		
			iterator = iterator->nextNode;
			counter++;
		}
	}
	void deleteLinkedList() {
		Node* temp = NULL;
		while(head) {
			temp = head;
			head = head->nextNode;
			delete temp;
			size--;
		}
	}
};

int main() {
	
	LinkedList<float>* linkedList = new LinkedList<float>();
	std::chrono::steady_clock sc;
	auto start = sc.now();
	
	for (int i = 0; i < 5; i++) {
		/*gqlist1.push_back(i);*/
		linkedList->pushBack(i + 0.232f);
	}
	linkedList->print();
	linkedList->deleteLinkedList();
	linkedList->print();
	auto end = sc.now();      
	auto time_span = static_cast<std::chrono::duration<double>>(end - start);  
	std::cout << "Operation took: " << time_span.count() << " seconds !!!";

	std::cin.get();
	delete linkedList;

}