package spider;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/18 0018.
 */
public class produceTypeData {
    private String result =" ";
    private Set<String> linksData = new HashSet<>();

    public void setResult(String result){
        this.result = result;
    }

    public void readProduceTypeData(){

        linksData = htmlParser.linksData(result);
        Iterator<String> dataurl = linksData.iterator();
        while (dataurl.hasNext()){
            System.out.println(htmlParser.readdata(httpClient.Get(dataurl.next())));
        }
    }
}
