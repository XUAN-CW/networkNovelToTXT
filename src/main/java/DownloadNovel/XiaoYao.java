package DownloadNovel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import utils.SaveAndRead;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author XUAN
 * @date 2020/9/25 - 12:59
 * @references
 * @purpose 下载逍遥小散仙
 * @errors
 */
public class XiaoYao extends DownloadNovel {

    public XiaoYao(String firstPageURL) {
        super(firstPageURL);
    }

    public void saveCurrentHTMLFile(String saveDir, String currentHTMLFile) {
        File dir = new File(saveDir);
        if (!dir.exists()){
            dir.mkdir();
        }
        Document document = Jsoup.parse(currentHTMLFile, "utf-8");
        for (int i=1;;i++){
            File saveTo = new File(dir.getAbsolutePath()+File.separator+document.title()+ "-" + i +".html");
            if (saveTo.exists()){
                continue;
            }else {
                SaveAndRead.save(saveTo.getAbsolutePath(),currentHTMLFile);
                break;
            }
        }
    }

    public String getNextPageURL(String currentHTMLFile) {
        String nextPageURL=null;
        Pattern ar = Pattern.compile("<\\s*a[^>]*>(下一页|下一章)(.*?)<\\s*/\\s*a>");//匹配含有 "下一页" 或 "下一章" 的 a 标签
        Matcher am = ar.matcher(currentHTMLFile);
        if (am.find()) {
            String a = am.group();//获得 a 标签及里面的内容
            Pattern rhref = Pattern.compile("(?<=href=\").+?(?=\")");
            Matcher mhref = rhref.matcher(a);
            if (mhref.find()) {
                String href = mhref.group();
                nextPageURL = getWebsite() + href;
                System.out.println("nextPageURL:"+nextPageURL);
            }
        } else {
            setEnd(true);
        }
        return nextPageURL;
    }
}
