package spider;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.security.PrivateKey;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/18 0018.
 */
public class produceTypeData {
    private String result = " ";
    private Set<String> linksData = new HashSet<>();
    private int sum =0;

    public void setResult(String result) {
        this.result = result;
    }

    public void readProduceTypeData() throws FileNotFoundException {
        PrintStream out = System.out;
        PrintStream ps = new PrintStream("./data.txt");
        System.setOut(ps);
        while (htmlParser.nextPage(result) != null) {
            linksData = htmlParser.linksData(result);
            result = httpClient.Get(htmlParser.nextPage(result));
            Iterator<String> dataurl = linksData.iterator();
            while (dataurl.hasNext()) {
                System.out.println(htmlParser.readdata(httpClient.Get(dataurl.next())));
                sum++;
            }
        }
        System.setOut(out);
        System.out.println("爬取完毕！爬了"+sum+"条数据");
    }
}
