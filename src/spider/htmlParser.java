package spider;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.*;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/9 0009.
 */
public class htmlParser {
    private static String ENCODE = "uTF-8";
    public static Set<String> linksUrl(String szContent){
        Set<String> links = new HashSet<>();
        String linkUrl = "";
        try{
            //初始化parser
            Parser parser = Parser.createParser(szContent,ENCODE);

            NodeFilter filterA = new NodeClassFilter(LinkTag.class);
            NodeList nodesFA = parser.extractAllNodesThatMatch(filterA);

            if(nodesFA != null) {
                for (int i = 0; i < nodesFA.size(); i++) {
                    Node NodesFA = (Node) nodesFA.elementAt(i);
                    String name = NodesFA.toPlainTextString();
                    if(NodesFA instanceof LinkTag) {
                        LinkTag link = (LinkTag) NodesFA;
                        linkUrl = link.getLink();
                    }
                    if(linkUrl.contains("www.cnhnb.com")&&(!name.isEmpty())){
                        links.add(linkUrl+name);
                    }
                }
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
        return links;
    }
    public static Set<String> linksData(String typeUrl){
        Set<String> links = new HashSet<>();
        String linksData = "";
        try{
            Parser parser = Parser.createParser(typeUrl,ENCODE);
            NodeFilter lifilter = new TagNameFilter("li");
            NodeFilter hasfilter = new HasParentFilter(lifilter);
            NodeFilter filtera = new NodeClassFilter(LinkTag.class);
            NodeFilter towfilter = new AndFilter(hasfilter,filtera);
            NodeList nodeList = parser.extractAllNodesThatMatch(towfilter);

            if(nodeList!=null){
                for(int i=0;i<nodeList.size();i++){
                    Node nodes = (Node)nodeList.elementAt(i);
                    if(nodes instanceof LinkTag){
                        LinkTag link = (LinkTag)nodes;
                        linksData = link.getLink();
                    }
                    if(linksData.contains("http://www.cnhnb.com/gongying/")){
                        links.add(linksData);
                    }
                }
            }

        }catch (ParserException e){
            e.printStackTrace();
        }
        return links;
    }
    public static String nextPage(String result){
        Set<String> links = new HashSet<>();
        String next = "";
        try{
            Parser parser = Parser.createParser(result,ENCODE);
            NodeFilter nextfilter = new StringFilter("下一页");
            //NodeFilter filtera = new NodeClassFilter(LinkTag.class);
            //NodeFilter twofilter = new AndFilter(nextfilter,filtera);
            NodeList node = parser.extractAllNodesThatMatch(nextfilter);

            if(node!=null){
                //以防万一 for一次吧
                String a = " ";
                for(int i=0;i<node.size();i++){
                    Node nodeurl = (Node)node.elementAt(i);//下一页作为节点
                    Node nodefather = nodeurl.getParent();//下一页的标签，也是超链接所在，调试能看到
                    if(nodefather instanceof LinkTag){
                        LinkTag link = (LinkTag)nodefather;
                        a = link.getLink();
                    }
                    if(a.contains("http://www.cnhnb.com/p/")){
                        next = a;
                    }
                }
            }else{
                System.out.println("没有下一页了");
                return null;
            }
        }catch(ParserException e){
            e.printStackTrace();
        }
        return next;
    }
    public static String readdata(String result){
        String price = " ";
        String placeoforigin = " ";
        String string = " ";
        try{
            Parser parser = Parser.createParser(result,ENCODE);
            NodeFilter tag = new TagNameFilter("div");
            NodeFilter at = new HasAttributeFilter("class","txt clearfix");
            NodeFilter and = new AndFilter(tag,at);
            NodeList nodeList = parser.extractAllNodesThatMatch(and);

//            NodeFilter tag1 = new TagNameFilter("div");
//            NodeFilter at1 = new HasAttributeFilter("class","price clearfix");
//            NodeFilter and1 = new AndFilter(tag1,at1);
//            NodeList nodeList1 = parser.extractAllNodesThatMatch(and1);


            if(nodeList!=null){
                for(int i=0;i<nodeList.size();i++){
                    Node node = (Node)nodeList.elementAt(i);
                    placeoforigin = node.toPlainTextString();
                    if (placeoforigin.contains("发货地")){
                        string = placeoforigin;
                    }
                }
            }

//            if(nodeList1!=null){
//                for(int i=0;i<nodeList1.size();i++){
//                    Node node1 = (Node)nodeList1.elementAt(i);
//                    price = node1.toPlainTextString();
//                    if (price.contains("价格")){
//                        string = string+price;
//                    }
//                }
//            }

        }catch (ParserException e){
            e.printStackTrace();
        }
        return string;
    }
}
