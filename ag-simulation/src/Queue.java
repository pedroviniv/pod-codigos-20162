

public class Queue {
	private int count = 0;
	private People[] peoples = new People[50];
	
	private void organize(){
		for (int i = 1; i < peoples.length; i++) {
			People p = peoples[i];
			peoples[i-1] = p;
		}
		peoples[peoples.length-1] = null;
	}
	
	public boolean push(People p){
		synchronized (peoples) {
			if (isEmpty()){
				peoples[count] = p;
				count++;
				return true;
			}
			else if (!isFull()){
				peoples[count-1] = p;
				count++;
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public People pop(){
		synchronized (peoples) {
			if (!isEmpty()){
				People p = peoples[0];
				peoples[0] = null;
				organize();
				count--;
				return p;
			}
			else {
				throw new RuntimeException("A fila está vazia.");
			}
		}
	}
	
	public int size(){
		return count;
	}
	
	public boolean isFull(){
		return count == 50;
	}
	
	public boolean isEmpty(){
		return count == 0;
	}
	
}
