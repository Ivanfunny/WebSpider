package spider;


import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2017/4/9 0009.
 */
public class thread extends runnable{
    private String url = "";

    public thread(String url) {
        super();
        this.url = url;
    }

    @Override
    public void run(){
        produceType p = new produceType();
        p.setLink(url);
        p.readUrl();
        produceTypeData data = new produceTypeData();
        data.setResult(p.getResult());
        try {
            data.readProduceTypeData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
