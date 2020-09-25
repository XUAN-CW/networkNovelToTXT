package DownloadNovel;

import utils.GetHTMLByURL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author XUAN
 * @date 2020/9/24 - 23:17
 * @references
 * @purpose 下载在线小说的 HTML 页面
 * @errors
 */
public abstract class DownloadNovel {

    String firstPageURL;
    boolean isEnd;
    private String website;

    DownloadNovel(String firstPageURL){
        setFirstPageURL(firstPageURL);
        setEnd(false);
        setWebsite(firstPageURL);
    }

    public void getNovelHTMLPages(String saveDir){
        String currentPageURL = firstPageURL;
        for (;!isEnd();){
            String currentHTMLFile = GetHTMLByURL.getHTML(currentPageURL);//获取当前 HTML 页面
            saveCurrentHTMLFile(saveDir,currentHTMLFile);
            String nextPageURL = getNextPageURL(currentHTMLFile);
            currentPageURL = nextPageURL;//爬取下一个页面
        }
    }

    /**
     *
     * @param saveDir 在这里设置保存的目录
     * @param currentHTMLFile 要保存的 HTML 页面
     */
    public abstract void saveCurrentHTMLFile(String saveDir,String currentHTMLFile);

    /**
     *
     * @param currentHTMLFile 当前 HTML 文件，需要通过它来获取下一个页面
     * @return 下一个页面的 URL
     */
    public abstract String getNextPageURL(String currentHTMLFile);

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public void setFirstPageURL(String firstPageURL) {
        this.firstPageURL = firstPageURL;
    }

    private void setWebsite(String firstPageURL) {
        Pattern pWebsite = Pattern.compile("(https?|ftp|file)://[^/]+");
        Matcher mFirst = pWebsite.matcher(firstPageURL);
        if (mFirst.find()) {
            this.website = mFirst.group();
        }
    }

    public String getWebsite() {
        return website;
    }
}
