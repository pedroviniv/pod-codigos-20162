package ag.ifpb.pod.ms;

public class Main {

	public static void main(String[] args) {
		String id = "aristofanio@hotmail.com";
		Publisher publisher = new Publisher(id);
		publisher.publish("aristofanio-subscriber@hotmail.com", "Hello World");
	}
}
