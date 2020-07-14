package DogCatQueue_java;

import java.util.LinkedList;
import java.util.Queue;

//猫狗队列
public class DogCatQueues {
	// 宠物类
	public static class Pet {
		// 类型字段
		private String type;

		// 构造方法
		public Pet(String type) {
			this.type = type;
		}

		public String getPetType() {
			return this.type;
		}
	}

	// 狗类，继承自宠物类
	public static class Dog extends Pet {
		public Dog() {
			super("dog");
		}
	}

	// 猫类，继承自宠物类
	public static class Cat extends Pet {
		public Cat() {
			super("cat");
		}
	}

	// 猫狗类
	public static class PetEnterQueue {
		// 宠物(或猫或狗)
		private Pet pet;
		// 时间戳
		private long count;

		// 构造方法
		public PetEnterQueue(Pet pet, long count) {
			this.pet = pet;
			this.count = count;
		}

		public Pet getPet() {
			return this.pet;
		}

		// 取时间戳
		public long getCount() {
			return this.count;
		}

		// 获取宠物类型
		public String getEnterPetType() {
			return this.pet.getPetType();
		}
	}

	// 猫狗队列
	public static class DogCatQueue {
		// 狗队列
		private Queue<PetEnterQueue> dogQ;
		// 猫队列
		private Queue<PetEnterQueue> catQ;
		// 总个数
		private long count;

		// 构造方法
		public DogCatQueue() {
			this.dogQ = new LinkedList<PetEnterQueue>();
			this.catQ = new LinkedList<PetEnterQueue>();
			// 当前队列宠物序号
			this.count = 0;
		}

		// 队列中添加元素
		public void add(Pet pet) {
			if (pet.getPetType().equals("dog")) {
				this.dogQ.add(new PetEnterQueue(pet, this.count++));
			} else if (pet.getPetType().equals("cat")) {
				this.catQ.add(new PetEnterQueue(pet, this.count++));
			} else {
				throw new RuntimeException("err, not dog or cat");
			}
		}

		// 按顺序输出宠物
		public Pet pollAll() {
			if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
				if (this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {
					return this.dogQ.poll().getPet();
				} else {
					return this.catQ.poll().getPet();
				}
			} else if (!this.dogQ.isEmpty()) {
				return this.dogQ.poll().getPet();
			} else if (!this.catQ.isEmpty()) {
				return this.catQ.poll().getPet();
			} else {
				throw new RuntimeException("err, queue is empty!");
			}
		}

		// 按顺序输出狗
		public Dog pollDog() {
			if (!this.isDogQueueEmpty()) {
				return (Dog) this.dogQ.poll().getPet();
			} else {
				throw new RuntimeException("Dog queue is empty!");
			}
		}

		// 按顺序输出猫
		public Cat pollCat() {
			if (!this.isCatQueueEmpty()) {
				return (Cat) this.catQ.poll().getPet();
			} else
				throw new RuntimeException("Cat queue is empty!");
		}

		// 判空
		public boolean isEmpty() {
			return this.dogQ.isEmpty() && this.catQ.isEmpty();
		}

		// 判狗空
		public boolean isDogQueueEmpty() {
			return this.dogQ.isEmpty();
		}

		// 判猫空
		public boolean isCatQueueEmpty() {
			return this.catQ.isEmpty();
		}

	}

	// 第二种猫狗类
	// 使用单链表实现
	public static class PetEnterQueue2 {
		// 宠物(或猫或狗)
		private Pet pet;
		// nextPet
		// 猫的nextPet是猫
		// 狗的nextPet是狗
		private PetEnterQueue2 nextPet;

		// 构造方法
		public PetEnterQueue2(Pet pet) {
			this.pet = pet;
		}

		// 获取宠物
		public Pet getPet() {
			return this.pet;
		}

		// 取下一个同类型的宠物
		public PetEnterQueue2 getNextPet() {
			return this.nextPet;
		}

		// 设置宠物
		public void setNextPet(PetEnterQueue2 nextPet) {
			this.nextPet = nextPet;
		}

		// 获取宠物类型
		public String getEnterPetType() {
			return this.pet.getPetType();
		}
	}

	// 猫狗队列
	// 单链表实现
	public static class DogCatQueue2 {
		// 猫狗队列
		private Queue<PetEnterQueue2> petQ;

		// 一个用来指向猫队列头,一个用来指向狗队列头
		private PetEnterQueue2 catHead;
		private PetEnterQueue2 dogHead;

		// 一个用来指向猫队列尾,一个用来指向狗队列尾
		// 方便追加元素
		private PetEnterQueue2 catFoot;
		private PetEnterQueue2 dogFoot;

		// 构造方法
		public DogCatQueue2() {
			// 初始化队列
			this.petQ = new LinkedList<PetEnterQueue2>();
			// 初始化四个头尾,置空
			this.catHead = this.catFoot = this.dogHead = this.dogFoot = null;
		}

		// 队列中添加元素
		public void add(Pet pet) {
			PetEnterQueue2 temp = new PetEnterQueue2(pet);
			// 直接添加
			this.petQ.add(temp);
			// 宠物是狗
			if (pet.getPetType().equals("dog")) {
				// 狗队列追加队首&队尾元素
				if (this.dogHead == null)
					this.dogHead = this.dogFoot = temp;
				this.dogFoot.setNextPet(temp);
				this.dogFoot = temp;
			}
			// 宠物是猫
			else if (pet.getPetType().equals("cat")) {
				// 猫队列追加队首&队尾元素
				if (this.catHead == null)
					this.catHead = this.catFoot = temp;
				this.catFoot.setNextPet(temp);
				this.catFoot = temp;
			} else {
				throw new RuntimeException("err, not dog or cat");
			}
		}

		// 按顺序出队(宠物)
		// 单链表速度很快
		public Pet pollAll() {
			if (!this.petQ.isEmpty()) {
				return this.petQ.poll().getPet();
			}
			throw new RuntimeException("err, queue is empty!");
		}

		// 按顺序出队(狗)
		public Dog pollDog() {
			if (this.dogHead != null) {
				Dog temp = (Dog) this.dogHead.getPet();
				this.dogHead = this.dogHead.getNextPet();
				return temp;
			} else {
				throw new RuntimeException("Dog queue is empty!");
			}
		}

		// 按顺序出队(猫)
		public Cat pollCat() {
			if (this.catHead != null) {
				Cat temp = (Cat) this.catHead.getPet();
				this.catHead = this.catHead.getNextPet();
				return temp;
			} else {
				throw new RuntimeException("Cat queue is empty!");
			}
		}

		// 判空
		public boolean isEmpty() {
			return this.petQ.isEmpty();
		}

		// 判狗空
		public boolean isDogQueueEmpty() {
			return this.dogHead == null ? true : false;
		}

		// 判猫空
		public boolean isCatQueueEmpty() {
			return this.catHead == null ? true : false;
		}

	}

	public static void main(String[] args) {

		// 我的单队列实现
		DogCatQueue2 test = new DogCatQueue2();

		// DogCatQueue test = new DogCatQueue();
		final int MAX = 10000000;
		for (int i = 0; i < MAX; i++)
			if (Math.random() > 0.5)
				test.add(new Dog());
			else
				test.add(new Cat());
		while (!test.isDogQueueEmpty()) 
			System.out.print(test.pollDog().getPetType());
		
		while (!test.isEmpty()) 
			System.out.println(test.pollAll());
	}

}
