package ag.ifpb.pod.ms;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		String text = "---123456---" + "aristo|nio" + "---123456---";
		String t = "---123456---";
		if (text.startsWith(t) && text.endsWith(t)){
			System.out.println(Arrays.toString(text.replaceAll(t, "").split("\\|")));
		}
		//throw new RuntimeException("Mensagem estruturada incorretamente.");
	}
}
