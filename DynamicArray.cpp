#include <iostream>

template<typename T>
class DynamicArray {
public:
    T*array;
    int actualSize=0;
    int maxSize=6;
    int extendRatio = 2;

    DynamicArray(){
        array = new T[maxSize];
    }
    ~DynamicArray(){
        delete[] array;
    }

    void expand(){
        maxSize = maxSize *2;
        T* tempArray = new T[maxSize];
        for(int i =0;i<actualSize;i++){
            tempArray[i] = array[i];
        }
        array = tempArray;
        delete tempArray;
    }

    void insert(T data){
        if (actualSize == maxSize)
		{
			expand();
		}
		array[actualSize] = data;
		actualSize++;
            
    }
    void print(){
        for(int i =0; i<actualSize;i++){
            std::cout << array[i];
        }
    }
};

int main(){
    DynamicArray<int>* dynamicArray = new DynamicArray<int>;

    dynamicArray->insert(0);
    dynamicArray->insert(2);
    dynamicArray->insert(3);
    dynamicArray->print();


    return 0;
}