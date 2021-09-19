import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Quiz_1157 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.next();
		
		S = S.toUpperCase();
		char[] arr=  S.toCharArray();
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		for(char c : arr) {
			if(map.containsKey(c)) {
				map.put(c, map.get(c)+1);
			}
			else {
				map.put(c, 1);
			}
		}
		
		map = map.entrySet().stream().sorted(Map.Entry.comparingByValue(
				new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						return o1 > o2 ? -1 : 0;
					}
				})).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(e1,e2) -> e1, LinkedHashMap::new));
		if(map.size() <= 1) {
			System.out.println(map.keySet().toArray()[0]);
		}
		else if(map.values().toArray()[0] == map.values().toArray()[1]) {
			System.out.println("?");
		}else {
			System.out.println(map.keySet().toArray()[0]);
		}
	}
}
