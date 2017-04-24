package spider;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/9 0009.
 */
public class Main {
    public static void main(String[] args){
        String u= "";
        Set<String> links = new HashSet<>();

//        String  ht = httpClient.Get("http://www.cnhnb.com/gongying/2320615/");
//        u = htmlParser.readdata(ht);
//        System.out.println(u);




        runnable thread = new runnable("http://www.cnhnb.com/");//一个线程一个页面搞定
        thread.start();
    }
}
