package TwoStacksQueue_java;

import java.util.Stack;

//双栈模拟队列测试类
public class TwoStacksQueueTest {
	// 双栈队列类
	public static class TwoStacksQueue {
		// 反栈/入栈（一次反转）
		public Stack<Integer> stackPush;
		// 正栈/出栈（两次反转即为正）
		public Stack<Integer> stackPop;

		// 构造方法 初始化双栈
		public TwoStacksQueue() {
			stackPush = new Stack<Integer>();
			stackPop = new Stack<Integer>();
		}

		// 入栈，元素直接进反栈
		public void add(int pushInt) {
			// 进入反栈的元素还没有进行二次处理
			stackPush.push(pushInt);
		}

		// 返回队列头部元素，并删除
		// 这里使用双栈模拟该操作
		public int poll() {
			// 如果双栈都为空，直接报错
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			}
			// 如果正栈为空，先让反栈入正栈
			else if (stackPop.empty()) {
				// 反栈入正栈
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
			// 正栈栈顶元素出栈，并返回
			return stackPop.pop();
		}

		// 返回队列头部元素，不删除
		// 这里使用双栈模拟该操作
		public int peek() {
			// 如果双栈都为空，直接报错
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			}
			// 如果正栈为空，先让反栈入正栈
			else if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
			// 返回正栈栈顶元素
			return stackPop.peek();
		}
	}

	// 测试
	public static void main(String[] args) {
		// 创建双栈队列对象
		TwoStacksQueue test = new TwoStacksQueue();
		// 入栈1
		test.add(1);
		// 入栈2
		test.add(2);
		// 入栈3
		test.add(3);
		//分别获取队列元素，弹出队列元素 1 2 3
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
	}

}
