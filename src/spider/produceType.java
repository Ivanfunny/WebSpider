package spider;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
public class produceType {
    private Set<String> links = new HashSet<>();
    private String[] resultString = { };
    private String result = "";

    public produceType() {

    }

    public void setLinks(Set<String> links){
        this.links = links;
    }

    public Set<String> getLinks(){
        return links;
    }
    public void readUrl(){
        Iterator<String> url = links.iterator();
        while (url.hasNext()){
            readType(url.next());
            System.out.println(" ");
        }
    }

    public void readType(String type){
        String name = "";
        //匹配出名称
        String charset = "[^u4e00-u9fa5/.a-z%&]";
        Pattern p = Pattern.compile(charset);
        Matcher m = p.matcher(type);
        //接收产品名字
        while(m.find()){
            name+=m.group(0);
        }
        resultString = type.split(name);//分解url和类型名称
        //取第一组，为url
        httpClient.Get(resultString[0]);
        System.out.println(name);
        //此处应该输出类型


        //解析类型，爬取具体的信息




    }
}
