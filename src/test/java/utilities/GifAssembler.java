package utilities;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static runners.Hooks.ScenarioName;
import static runners.Hooks.ScreenshotPath;

public class GifAssembler{
    private static final int IMAGE_PADDING_SIZE = 20;
    private static final int TEXT_PADDING_SIZE = 5;
    public static ArrayList<String> details = new ArrayList<>();
    private static List<Frame> frames = new ArrayList<Frame>();
    private static Logger logger = Logger.getLogger(GifAssembler.class);

    public void addFrame(String description, byte[] image){
        Frame frame = new Frame();
        frame.details = description;
        frame.screenshot = image;
        details.add(description);
        GifAssembler.frames.add(frame);
        logger.info("Frame is created for screenshot and named as "+description);
    }

    private static class Frame{
        public String details;
        public byte[] screenshot;
    }

    public void generate_screenshotWithPDF() throws IOException{
        putScreenshotIntoPDF(GifAssembler.frames);
        logger.info("PDF File Creation Begins");
    }

    private static BufferedImage toBufferedImage(byte[] bytes) throws IOException{
        return ImageIO.read(new ByteArrayInputStream(bytes));
    }

    private static BufferedImage addPadding(BufferedImage bufferedImage, Color color, int paddingSize){
        BufferedImage newImage = new BufferedImage(bufferedImage.getWidth()+paddingSize,bufferedImage.getHeight()+paddingSize,bufferedImage.getType());
        Graphics g = newImage.getGraphics();
        g.setColor(color);
        g.fillRect(0,0,newImage.getWidth(),newImage.getHeight());
        g.drawImage(bufferedImage,paddingSize/2,paddingSize/2,null);
        g.dispose();
        logger.info("Resizing the image");
        return newImage;
    }

    private static BufferedImage addHeaderText(BufferedImage bufferedImage, String text, float size, int textBottomPadding){
        BufferedImage newImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight()+(int)Math.ceil(size)+textBottomPadding,bufferedImage.getType());
        Graphics g = newImage.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,bufferedImage.getWidth(),bufferedImage.getHeight()+(int)Math.ceil(size)+textBottomPadding);
        g.drawImage(bufferedImage,0,(int)Math.ceil(size)+textBottomPadding,null);
        g.setFont(g.getFont().deriveFont(size));
        g.setColor(Color.BLACK);
        g.drawString(text,0,(int)Math.ceil(size));
        g.dispose();
        logger.info("Added Header Text in image");
        return newImage;
    }

    private void putScreenshotIntoPDF(List<Frame> frames){
        try{
            int maxWidth = 0;
            int maxHeight = 0;
            int i = 0;
            List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();
            Document doc = new Document();
            String Formatted_string = Random_Data.getUniqueDate("yyMMddHHmmss");
            PdfWriter.getInstance(doc,new FileOutputStream(System.getProperty("user.dir")+"/output/"+ScenarioName+Formatted_string+".pdf"));
            doc.open();

            Iterator<Frame> iterator = frames.iterator();
            while(iterator.hasNext()){
                Frame frame = iterator.next();
                Color paddingColor = iterator.hasNext()? Color.WHITE:Color.WHITE;
                BufferedImage image = addPadding(addHeaderText(toBufferedImage(frame.screenshot),frame.details,30F,TEXT_PADDING_SIZE), paddingColor, IMAGE_PADDING_SIZE);
                bufferedImages.add(image);
            }
            logger.info("Padding and Resizing image is done");

            for (BufferedImage bufferedImage : bufferedImages) {
                if (maxWidth < bufferedImage.getWidth()) maxWidth = bufferedImage.getWidth();
                if (maxHeight < bufferedImage.getHeight()) maxHeight = bufferedImage.getHeight();
            }
            logger.info("Found the image has heighest width and height for resizing ("+maxWidth+","+maxHeight+")");

            for (BufferedImage bufferedImage : bufferedImages) {
                BufferedImage normalizedImage = new BufferedImage(maxWidth, maxHeight, bufferedImage.getType());
                Graphics g = normalizedImage.getGraphics();
                g.drawImage(bufferedImage,0,0,null);
                g.dispose();
                Thread.sleep(50);
                String name = details.get(i);
                ImageIO.write(normalizedImage,"png",new File(ScreenshotPath+"/"+name+".png"));
                logger.info("Image created: "+name+".png");
                normalizedImage.flush();
                Image imagePdf = Image.getInstance(ScreenshotPath+"/"+name+".png");
                doc.setPageSize(new Rectangle(imagePdf.getScaledWidth(),imagePdf.getScaledHeight()));
                doc.newPage();
                imagePdf.setAbsolutePosition(0,0);
                doc.add(imagePdf);
                logger.info("Image added to PDF file: "+name+".png");
                i++;
            }
            doc.close();
            bufferedImages.clear();
        }catch (Exception e){
            logger.info("Unable to create Image file");
            logger.fatal("Exception: "+e.getMessage());
            throw new RuntimeException("Unable to create Image file");
        }
    }
}