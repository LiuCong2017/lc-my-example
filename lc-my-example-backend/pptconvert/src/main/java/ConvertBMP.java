import com.spire.presentation.Presentation;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

//https://www.e-iceblue.cn/spirepresentationforjava/insert-and-extract-images-from-a-powerpoint-document-in-java.html
public class ConvertBMP {
    public static void main(String[] args) throws Exception {
        Presentation ppt = new Presentation();
        ppt.loadFromFile("C:\\Users\\Administrator\\Desktop\\me.pptx");
        //将PPT保存为图片格式
        for (int i = 0; i < ppt.getSlides().getCount(); i++) {
            BufferedImage image = ppt.getSlides().get(i).saveAsImage();
            String fileName = String.format("output/Topng-%d.png", i);
            ImageIO.write(image, "PNG",new File(fileName));
        }
        ppt.dispose();
    }
}