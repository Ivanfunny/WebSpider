package spider;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/9 0009.
 */
public class thread extends runnable{
    volatile static Set<String> linksData = new HashSet<>();
    volatile static String nextpage = " ";
    public thread(){

    }

    @Override
    public void run(){

    }
}
