package ReverseStackUsingRecursive_java;

import java.util.Stack;

public class ReverseStackUsingRecursive {

    //递归函数二：栈反序的递归函数
	public static void reverse(Stack<Integer> stack) {
        //1.如果栈为空，什么都不操作（空栈的反序还是空栈）
        if (stack.isEmpty()) {
			return;
        }
        //2.获取栈底元素,并删除
        int i = getAndRemoveLastElement(stack);
        //3.递归执行2（本质）
        reverse(stack);
        //4.递归后操作：反向插入栈底元素
		stack.push(i);
	}
    // 递归函数一:将栈 stack 的栈底元素返回并移除。
	public static int getAndRemoveLastElement(Stack<Integer> stack) {
        //1.栈顶元素暂时出栈
        int result = stack.pop();
        //2.如果栈顶元素出栈后栈为空，直接返回栈顶元素
		if (stack.isEmpty()) {
			return result;
        } 
        //3.否则递归
        else {
            // 4.一直执行1(本质)
            int last = getAndRemoveLastElement(stack);
            //5.递归后半段暂时出栈的栈顶元素们（除了栈底元素），反向入栈
            //6.此时的栈和初始栈没太大区别，除了删除了栈底元素
            //其他元素都是先出栈后入栈，占地元素没有出栈
            stack.push(result);
            //7. 并返回栈底元素。（初始栈栈底）
			return last;
		}
	}

    //测试
	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

}
