package spider;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/9 0009.
 */
public class runnable extends Thread{
    volatile static Set<String> links = new HashSet<>(); //为static，所有线程共享
    private Set<String> linksRunnable = new HashSet<>();//做为存储此次解析到的URL

    public runnable() {
    }

    public runnable(String firstUrl){
        links.add(firstUrl);
    }

    public int getLinksSize() {
        return links.size();
    }

    public int getLinksRunnable() {
        return linksRunnable.size();
    }

    public Set<String> getLinks(){
        return links;
    }

    public void run(){
        Iterator<String> url = links.iterator();
        if(url.hasNext()){
            String readUrl = url.next();
            String ht = httpClient.Get(readUrl);//返回响应报文(html页面)
            links.remove(readUrl);//移除读过的url
            //解析网页，提取需要的内容
            linksRunnable = htmlParser.linksUrl(ht);//解析网页，提取超链接
            Iterator<String> htUrl = linksRunnable.iterator();
            while(htUrl.hasNext()){
                links.add(htUrl.next());
            }
        }

        Iterator<String> type = links.iterator();
        for(int i=0;i<1;i++){
            if(type.hasNext()) new Thread(new thread(type.next())).start();
        }
    }
}
