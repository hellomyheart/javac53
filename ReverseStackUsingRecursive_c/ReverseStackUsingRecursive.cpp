#include <iostream>
#include <stack>
#include <vector>
#include <string>
using namespace std;

static int getAndRemoveLastElement(stack<int>& rstack)
{ // 递归函数一:将栈 stack 的栈底元素返回并移除。
    int result = rstack.top();
    rstack.pop();
    if (rstack.empty())
        return result;
    //有问题
    int last = getAndRemoveLastElement(rstack);
    rstack.push(result);
    return last;
}

static void reverse(stack<int>& rstack)
{                       //栈反序
    if (rstack.empty()) //如果是空栈，直接返回
        return;
    int i = getAndRemoveLastElement(rstack);

    reverse(rstack);
    
    rstack.push(i);
}

int main() //测试
{
    stack<int> s;
    s.push(1);
    s.push(2);
    s.push(3);
    s.push(4);
    s.push(5);
    reverse(s);
    while (!s.empty())
    {
        cout << s.top() << endl;
        s.pop();
    }
}