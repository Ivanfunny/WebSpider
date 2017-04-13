package spider;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
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
                    if(linkUrl.contains("www.cnhnb.com")){
                        links.add(linkUrl+name);
                    }

                }
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
        return links;
    }
}
