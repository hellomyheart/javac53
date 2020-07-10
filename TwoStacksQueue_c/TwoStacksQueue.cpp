#include <iostream>
#include <stack>
#include <vector>
#include <string>
using namespace std;

typedef struct qstack // 双栈队列结构体
{
    stack<int> stackPush; //入栈
    stack<int> stackPop;  //出栈

    void add(int num) //队列入元素
    {
        stackPush.push(num);
    }
    int poll() //队列出元素
    {
        if (stackPop.empty() && stackPush.empty())
            throw "队列为空";
        else if (stackPop.empty())
            while (!stackPush.empty())
            { //std中的pop只删除，不返回栈顶元素
                stackPop.push(stackPush.top());
                stackPush.pop();
            }
        int value = stackPop.top();
        stackPop.pop();
        return value;
    }

    int peek()
    { // 返回队列头部元素，不删除,这里使用双栈模拟该操作
        if (stackPop.empty() && stackPush.empty())
            throw "队列为空";
        else if (stackPop.empty())
            while (!stackPush.empty())
            { //std中的pop只删除，不返回栈顶元素
                stackPop.push(stackPush.top());
                stackPush.pop();
            }
        return stackPop.top();
    }
} QS;

int main() //测试
{
    try
    {
        QS qs;
        qs.add(1);
        qs.add(2);
        qs.add(3);
        cout << qs.peek() << endl;
        cout << qs.poll() << endl;
        cout << qs.peek() << endl;
        cout << qs.poll() << endl;
        cout << qs.peek() << endl;
        cout << qs.poll() << endl;
    }
    catch (const char *msg)
    {
        cerr << msg << endl;
    }
}