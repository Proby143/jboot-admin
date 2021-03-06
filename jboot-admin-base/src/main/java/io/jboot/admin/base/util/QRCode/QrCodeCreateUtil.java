package io.jboot.admin.base.util.QRCode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * describe:
 *
 * @author 白野
 * @date 2019\7\5 0005
 */
public class QrCodeCreateUtil {
    /**
     * 生成包含字符串信息的二维码图片
     *
     * @param outputStream 文件输出流路径
     * @param content      二维码携带信息
     * @param qrCodeSize   二维码图片大小
     * @param imageFormat  二维码的格式
     * @throws WriterException
     * @throws IOException
     */
    public static boolean createQrCode(OutputStream outputStream, String content, int qrCodeSize, String imageFormat) throws WriterException, IOException {
        if (qrCodeSize < 600) {
            throw new RuntimeException("设置边距不能小于600");
        }
        //设置二维码纠错级别ＭＡＰ
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  // 矫错级别
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        //创建比特矩阵(位矩阵)的QR码编码的字符串
        BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
        // 使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth - 200, matrixWidth - 200, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // 使用比特矩阵画并保存图像
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i - 100, j - 100, 1, 1);
                }
            }
        }
        return ImageIO.write(image, imageFormat, outputStream);
    }

    /**
     * 读二维码并输出携带的信息
     */
//    public static String readQrCode(InputStream inputStream) throws IOException {
//        //从输入流中获取字符串信息
//        BufferedImage image = ImageIO.read(inputStream);
//        //将图像转换为二进制位图源
//        LuminanceSource source = new BufferedImageLuminanceSource(image);
//        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//        QRCodeReader reader = new QRCodeReader();
//        Result result = null;
//        try {
//            result = reader.decode(bitmap);
//        } catch (ReaderException e) {
//            e.printStackTrace();
//        }
//        return result.getText();
//    }

    /**
     * 测试代码
     *
     * @throws WriterException
     */
    public static void main(String[] args) throws IOException, WriterException {

        String excute = new QrCodeCreateUtil().excute(10000, 60000,true);
        System.out.println("excute-->"+excute+"msg:执行成功");

//        String s = readQrCode(new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\qrcode\\qrcode.jpg")));
//        System.out.println(s);
    }

    public static String  excute(int start, int end,boolean iscreate) {
        StringBuffer sbf=new StringBuffer();
        for (int i = start; i < end; i++) {
            boolean jpeg = false;
            if(iscreate){
                try {
                    jpeg = createQrCode(new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\qrcode\\qrcode" + i + "0.jpg")),  i+"0", 600, "JPEG");
                    System.out.println("qrcode" + i + "0.jpg");
                } catch (WriterException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!jpeg) {
                    System.out.println("创建二维码失败" + "qrcode" + i + "0.jpg");
                }
            }

        }
        return sbf.toString();
    }
}
