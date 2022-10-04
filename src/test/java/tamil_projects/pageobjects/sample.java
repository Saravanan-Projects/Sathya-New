package tamil_projects.pageobjects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class sample {

    public static void call(){
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            while ((line = br.readLine()) != null) {
                System.out.println("Line =>"+line);
            }
        } catch (IOException e) {
            System.out.println("IOException in try block =>" + e.getMessage());
            Throwable[] suppressedExceptions = e.getSuppressed();
            for (int i=0; i<suppressedExceptions.length; i++) {
                System.out.println("Suppressed exception=>" + suppressedExceptions[i]);
            }
        }
    }

    public static void main(String... a){
        call();
        //noinspection unchecked
//        List<Map<String,String>> allFXDetails = new ArrayList()
//       {{
//            add(new HashMap(){{
//                put("ORDR_ID","23625");
//                put("BUY_SELL_FLAG","1");
//            }});
//           add(new HashMap(){{
//               put("ORDR_ID","23626");
//               put("BUY_SELL_FLAG","2");
//           }});
//           add(new HashMap(){{
//               put("ORDR_ID","23627");
//               put("BUY_SELL_FLAG","1");
//           }});
//           add(new HashMap(){{
//               put("ORDR_ID","23628");
//               put("BUY_SELL_FLAG","2");
//           }});
//       }};
//
//        HashMap<String,String> mp = new HashMap(){{
//            put("ORDR_ID","23628");
//            put("BUY_SELL_FLAG","2");
//        }};

//        List<String> fxIDs = allFXDetails.stream().flatMap(e->e.entrySet().stream().filter(t ->t.getKey().equals("BUY_SELL_FLAG")&&t.getValue().equals("2"))).collect(Collectors.toList());
//        System.out.println(fxIDs);

        //mp.entrySet().stream().filter(r->r.getKey().equals("BUY_SELL_FLAG")&&r.getValue().equals("2")).map(mp.entrySet().stream().filter(m->m.getKey().equals("ORDR_ID"))).forEach(System.out::println);
    }
}

