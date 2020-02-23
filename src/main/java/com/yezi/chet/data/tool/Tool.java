package com.yezi.chet.data.tool;

import java.io.*;
import java.sql.Blob;
import java.util.Random;

public class Tool {


    /**
     * 随机获得一个游客名
     * @return 返回一个游客名
     */
    public static String getTrandeName(){
        Random random = new Random();
        long code = System.currentTimeMillis()/random.nextInt(5892);
        return "游客"+code;
    }

    public static byte[] subBytes(int start,int end,byte[] bytes){
        byte[] value = new byte[end-start];
        int j = 0;
        for(int i = start; i < end ; i++,j++){
            value[j] = bytes[i];
        }
        return value;
    }

    public static byte[] blobToBytes(Blob blob) {
        BufferedInputStream is = null;

            try {

                is = new BufferedInputStream(blob.getBinaryStream());

                byte[] bytes = new byte[(int) blob.length()];

                int len = bytes.length;

                int offset = 0;

                int read = 0;

            while (offset < len && (read = is.read(bytes, offset, len - offset)) >= 0) {

                offset += read;

            }

                return bytes;

            } catch (Exception e) {

                return null;

            } finally {

                try {

                is.close();

                is = null;

                } catch (IOException e) {

                return null;

                }

        }

    }

    //将byte[] 转换成文件
    public static void byteToFile(byte[] bytes,String path){
        if(bytes.length>0) {
            path += ".jpg";
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
//            try {
//                BufferedImage bfimg = ImageIO.read(byteArrayInputStream);
//                File file = new File(path);
//                ImageIO.write(bfimg, "jpg", file);
//            } catch (IOException e) {
//                e.printStackTrace();FileOutputStream
//            }
            File file = new File(path);

            OutputStream output = null;
            try {
                output = new FileOutputStream(file);


            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);

            bufferedOutput.write(bytes);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("存储图片失败!");
        }
    }

    //ByteArrayOutputStream
    public static byte[] fileToBytes(String account){
        byte[] bytes = null;
        String path = account+".jpg";
        System.out.println("地址:"+path);
        File img = new File(path);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            InputStream input = new FileInputStream(img);

            bytes = new byte[input.available()];

            input.read(bytes);
//            BufferedImage bi;
//            bi = ImageIO.read(img);
//            ImageIO.write(bi, "jpg", baos);
//            bytes = baos.toByteArray();
//            System.out.println(path+":"+bytes.length+ Arrays.toString(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
