package getMin_java;

import java.util.Stack;

public class GetMin {

    // 第一种getMin
    public static class MyStack1 {
        // 成员变量 一个用来保存数据，一个用来获取当前栈的最小值。
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        // 构造方法初始化成员变量
        public MyStack1() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        // 入栈
        public void push(int newNum) {
            // 刚开始最小值栈为空，进入新元素时，最小值栈同时入栈。
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            }
            // 入栈元素比之前的最小值还要小（当前最小值永远在栈顶），新元素同时入最小值栈。
            else if (newNum <= this.getmin()) {
                this.stackMin.push(newNum);
            }
            // 不论什么情况，新元素都要入数据栈。
            this.stackData.push(newNum);
        }

        // 出栈
        public int pop() {
            // 如果栈为空，则不能出栈。（报错提示）
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            // 栈不为空时，先出栈,同时把出栈元素赋值给valuee
            int value = this.stackData.pop();
            // 出栈元素与Min栈栈顶元素比较，

            // 如果相等，说明出栈元素即是当前栈最小值
            // 因为该元素已经出栈，所以Min栈的栈顶元素也要出栈

            // 如果不相等，只可能是出栈元素大于Min栈顶元素（入栈规则限制出栈元素不可能比Min栈栈顶元素小）
            // 此时Min栈不做任何操作即可。
            if (value == this.getmin()) {
                this.stackMin.pop();
            }
            // 正常的出栈操作，返回栈顶元素。
            return value;
        }

        // 获取Min栈栈顶元素
        // 即获取栈的最小值
        public int getmin() {
            // 如果stack栈中没有元素（stack还未初始化），报错
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            // 正常情况下，返回Min栈栈顶元素
            return this.stackMin.peek();
        }
    }

    // 第二种getMin
    public static class MyStack2 {
        // 成员变量 一个用来保存数据，一个用来获取当前栈的最小值。
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        // 构造函数，初始化成员变量
        public MyStack2() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        // 入栈
        public void push(int newNum) {
            // 如果栈为空（刚初始化），直接入Min栈
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            }
            // 如果入栈元素比Min栈栈顶小
            // 新元素入Min栈
            else if (newNum < this.getmin()) {
                this.stackMin.push(newNum);
            }
            // 否则Min栈重新入一次栈顶元素（Min栈和StackData栈元素个数一样）
            else {
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
            // 入栈元素入stackData栈
            this.stackData.push(newNum);
        }

        // 出栈
        public int pop() {
            // 空栈出栈直接报错
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            // 正常出栈时，双栈都出栈。
            // 相比于方法1少了判断
            // 代价是相比于方法1占用空间相对多了。
            this.stackMin.pop();
            return this.stackData.pop();
        }

        // 获取Min栈栈顶元素
        // 即获取栈的最小值
        public int getmin() {
            // 空栈报错
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            // 正常出栈
            return this.stackMin.peek();
        }
    }

    // 测试
    public static void main(String[] args) {
        // 方法1
        MyStack1 stack1 = new MyStack1();
        // 入栈3
        stack1.push(3);
        // 获取最小值
        System.out.println(stack1.getmin());
        // 入栈4
        stack1.push(4);
        // 获取最小值
        System.out.println(stack1.getmin());
        // 入栈1
        stack1.push(1);
        // 获取最小值
        System.out.println(stack1.getmin());
        // 出栈
        System.out.println(stack1.pop());
        // 获取最小值
        System.out.println(stack1.getmin());

        System.out.println("=============");
        // 方法2
        MyStack1 stack2 = new MyStack1();
        // 入栈3
        stack2.push(3);
        // 获取最小值
        System.out.println(stack2.getmin());
        // 入栈4
        stack2.push(4);
        // 获取最小值
        System.out.println(stack2.getmin());
        // 入栈1
        stack2.push(1);
        // 获取最小值
        System.out.println(stack2.getmin());
        // 出栈
        System.out.println(stack2.pop());
        // 获取最小值
        System.out.println(stack2.getmin());
    }

}
