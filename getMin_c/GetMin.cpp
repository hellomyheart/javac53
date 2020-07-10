#include <iostream>
#include <stack>
#include <vector>
#include <string>
using namespace std;
//方法1
typedef struct sqstack1
{
    //两个栈，一个数据栈一个最小值栈
    stack<int> stackData;
    stack<int> stackMin;
    //入栈
    void push(int num)
    {
        // 刚开始最小值栈为空，进入新元素时，最小值栈同时入栈。
        //或者入栈元素比之前的最小值还要小（当前最小值永远在栈顶），
        // 新元素同时入最小值栈。
        if (stackData.empty() || num <= getMin())
            stackMin.push(num);
        // 不论什么情况，新元素都要入数据栈。
        stackData.push(num);
    }
    // 出栈
    int pop()
    {
        // 如果栈为空，则不能出栈。（报错提示）
        if (stackData.empty())
            throw "栈为空，不能出栈";
        // 栈不为空时，先出栈,同时把出栈元素赋值给value
        int value = stackData.top();
        // 出栈元素与Min栈栈顶元素比较，

        // 如果相等，说明出栈元素即是当前栈最小值
        // 因为该元素已经出栈，所以Min栈的栈顶元素也要出栈

        // 如果不相等，只可能是出栈元素大于Min栈顶元素（入栈规则限制出栈元素不可能比Min栈栈顶元素小）
        // 此时Min栈不做任何操作即可。
        if (value == getMin())
            stackMin.pop();
        return value;
    }
    // 获取Min栈栈顶元素
    // 即获取栈的最小值
    int getMin()
    {
        // 如果stack栈中没有元素（stack还未初始化），报错
        if (stackMin.empty())
            throw "栈为空，不能出栈";
        // 正常情况下，返回Min栈栈顶元素
        return stackMin.top();
    }
} MyStack1;

//方法2
typedef struct sqstack2
{
    //两个栈，一个数据栈一个最小值栈
    stack<int> stackData;
    stack<int> stackMin;
    //入栈
    void push(int num)
    {
        // 刚开始最小值栈为空，进入新元素时，最小值栈同时入栈。
        //或者入栈元素比之前的最小值还要小（当前最小值永远在栈顶），
        // 新元素同时入最小值栈。
        if (stackData.empty() || num <= getMin())
            stackMin.push(num);
        // 否则Min栈重新入一次栈顶元素（Min栈和StackData栈元素个数一样）
        else
            stackMin.push(getMin());
        // 不论什么情况，新元素都要入数据栈。
        stackData.push(num);
    }
    // 出栈
    int pop()
    {
        // 如果栈为空，则不能出栈。（报错提示）
        if (stackData.empty())
            throw "栈为空，不能出栈";
        // 正常出栈时，双栈都出栈。
        // 相比于方法1少了判断
        // 代价是相比于方法1占用空间相对多了。

        int top = stackData.top();
        stackMin.pop();

        stackData.pop();
        return top;
    }
    // 获取Min栈栈顶元素
    // 即获取栈的最小值
    int getMin()
    {
        // 如果stack栈中没有元素（stack还未初始化），报错
        if (stackMin.empty())
            throw "栈为空，不能出栈";
        // 正常情况下，返回Min栈栈顶元素
        return stackMin.top();
    }
} MyStack2;

//测试
int main()
{
    try
    {

        MyStack1 s1;
        // 入栈3
        s1.push(3);
        // 获取最小值
        cout << "最小值是：" << s1.getMin() << endl;
        // 入栈4
        s1.push(4);
        // 获取最小值
        cout << "最小值是：" << s1.getMin() << endl;
        // 入栈1
        s1.push(1);
        // 获取最小值
        cout << "最小值是：" << s1.getMin() << endl;
        // 出栈
        s1.pop();
        // 获取最小值
        cout << "最小值是：" << s1.getMin() << endl;

        cout << "==========" << endl;

        MyStack2 s2;
        // 入栈3
        s2.push(3);
        // 获取最小值
        cout << "最小值是：" << s2.getMin() << endl;
        // 入栈4
        s2.push(4);
        // 获取最小值
        cout << "最小值是：" << s2.getMin() << endl;
        // 入栈1
        s2.push(1);
        // 获取最小值
        cout << "最小值是：" << s2.getMin() << endl;
        // 出栈
        s2.pop();
        // 获取最小值
        cout << "最小值是：" << s2.getMin() << endl;
    }
    catch (const char* msg)
    {
        cerr << msg << endl;
    }
}