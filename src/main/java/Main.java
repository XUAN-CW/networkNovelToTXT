import DownloadNovel.XiaoYao;

/**
 * @author XUAN
 * @date 2020/9/25 - 13:05
 * @references
 * @purpose
 * @errors
 */
public class Main {
    public static void main(String[] args) {
        XiaoYao foBenShiDao = new XiaoYao("https://www.amxsw.com/am/66/66602/46129074.html");
        foBenShiDao.getNovelHTMLPages("fiction");

//        GetHTMLByURL.getHTML("https://www.baidu.com/");
    }
}
