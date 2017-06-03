package spider;



/**
 * Created by Administrator on 2017/4/9 0009.
 */
public class Main {
    public static void main(String[] args){
        runnable thread = new runnable("http://www.cnhnb.com/");//一个线程一个页面搞定
        thread.start();
    }
}
