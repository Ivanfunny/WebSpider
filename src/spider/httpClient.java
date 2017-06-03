package spider;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/4/9 0009.
 */
public class httpClient {
    public static String Get(String url) {
        String result = "";
        try{
            //创建HttpClient实例
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //获取地址
            HttpGet httpGet = new HttpGet(url);
            //设置请求头
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
            //得到http服务器响应
            CloseableHttpResponse response = httpClient.execute(httpGet);

            try{
                //返回http状态码，判断是否成功
                if((response != null) && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    //输出状态行
                    //System.out.println(response.getStatusLine());
                    //获取响应实体
                    HttpEntity entity = response.getEntity();
                    //解析实体
                    result = readResponse(entity,"utf-8");
                }
            }finally {
                //关闭
                httpClient.close();
                response.close();
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    public static String readResponse(HttpEntity resEntity, String charset){
        StringBuffer res = new StringBuffer();
        BufferedReader reader = null;
        try{
            if(resEntity == null){
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(resEntity.getContent(),charset));
            String line = null;

            while ((line = reader.readLine())!= null){
                res.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (reader != null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return res.toString();
    }
}
