package com.personal.website.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 文件工具类
 * 
 * @author admin
 */
public class FileUtils {

    /**
     * <pre>
     * 将接收的字符串转换成图片保存
     * @param imgStr 二进制流转换的字符串
     * @param imgPath 图片的保存路径
     * @param imgName  图片的名称
     * @return 1：保存正常 0：保存失败
     * </pre>
     */
    public static int saveToImgByStr(String imgStr, String imgName) {

        try {
            System.out.println("===imgStr.length()====>" + imgStr.length() + "=====imgStr=====>" + imgStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int stateInt = 1;
        if (imgStr != null && imgStr.length() > 0) {
            try {

                // 将字符串转换成二进制，用于显示图片
                // 将上面生成的图片格式字符串 imgStr，还原成图片显示
                byte[] imgByte = hex2byte(imgStr);

                InputStream in = new ByteArrayInputStream(imgByte);

                File file = new File(imgName);// 可以是任何图片格式.jpg,.png等
                FileOutputStream fos = new FileOutputStream(file);

                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = in.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();
                in.close();

            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return stateInt;
    }

    /**
     * 字符串转二进制
     * 
     * @param str
     *            要转换的字符串
     * @return 转换后的二进制数组
     */
    public static byte[] hex2byte(String str) { // 字符串转二进制
        if (str == null)
            return null;
        str = str.trim();
        int len = str.length();
        if (len == 0 || len % 2 == 1)
            return null;
        byte[] b = new byte[len / 2];
        for (int i = 0; i < str.length(); i += 2) {
            b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();
        }
        return b;
    }

    /**
     * 将二进制转换成图片保存
     * 
     * @param imgStr
     *            二进制流转换的字符串
     * @param imgPath
     *            图片的保存路径
     * @param imgName
     *            图片的名称
     * @return 1：保存正常 0：保存失败
     */
    public static int saveToImgByBytes(File imgFile, String imgPath, String imgName) {

        int stateInt = 1;
        if (imgFile.length() > 0) {
            try {
                File file = new File(imgPath, imgName);// 可以是任何图片格式.jpg,.png等
                FileOutputStream fos = new FileOutputStream(file);

                FileInputStream fis = new FileInputStream(imgFile);

                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = fis.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();
                fis.close();

            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return stateInt;
    }

    /**
     * 解压到指定目录
     * 
     * @param zipPath
     * @param descDir
     * @author isea533
     */
    public static boolean unZipFiles(String zipPath, String descDir) throws Exception {
        return unZipFiles(new File(zipPath), descDir);
    }

    /**
     * 解压到创建的目录
     * 
     * @param zipPath
     * @param descDir
     * @author isea533
     */
    public static String unZipFilesV2(String zipPath, String descDir) throws Exception {
        System.out.println(zipPath + "---" + descDir);
        return unZipFilesV2(new File(zipPath), descDir);
    }

    /**
     * 解压文件到指定目录
     * 
     * @param zipFile
     * @param descDir
     * @author isea533
     */
    @SuppressWarnings("rawtypes")
    public static String unZipFilesV2(File zipFile, String descDir) throws Exception {
        ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));// 解决中文文件夹乱码
        String name = zip.getName().substring(zip.getName().lastIndexOf('\\') + 1, zip.getName().lastIndexOf('.'));
        System.out.println("name:" + name);
        // File pathFile = new File(descDir);
        // if (!pathFile.exists()) {
        // pathFile.mkdirs();
        // }
        // System.out.println(pathFile+"--------");
        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir + "/" + zipEntryName).replaceAll("\\*", "/");

            // 判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            // 输出文件路径信息
            System.out.println("outPath:" + outPath);

            FileOutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        System.out.println("******************解压完毕********************");
        return null;
    }

    /**
     * 解压文件到指定目录
     * 
     * @param zipFile
     * @param descDir
     * @author isea533
     */
    @SuppressWarnings("rawtypes")
    public static boolean unZipFiles(File zipFile, String descDir) throws Exception {
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
        for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
            ;
            // 判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            // 输出文件路径信息
            System.out.println(outPath);

            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        return true;
    }

    /**
     * 解压文件
     * 
     * @param filePath
     *            压缩文件路径
     */
    public static boolean unzip(String filePath) {
        File source = new File(filePath);
        if (source.exists()) {
            ZipInputStream zis = null;
            BufferedOutputStream bos = null;
            try {
                zis = new ZipInputStream(new FileInputStream(source));
                ZipEntry entry = null;
                System.out.println(zis.getNextEntry() + "....mmmmmmmmmmmmmnnnnnnn........");

                while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {
                    File target = new File(source.getParent(), entry.getName());
                    System.out.println(target.getParentFile() + ".............");
                    if (!target.getParentFile().exists()) {
                        // 创建文件父目录
                        target.getParentFile().mkdirs();
                    }
                    // 写入文件
                    bos = new BufferedOutputStream(new FileOutputStream(target));
                    int read = 0;
                    byte[] buffer = new byte[1024 * 10];
                    while ((read = zis.read(buffer, 0, buffer.length)) != -1) {
                        bos.write(buffer, 0, read);
                    }
                    bos.flush();
                }
                zis.closeEntry();

                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage() + "===============");
                // throw new RuntimeException(e);
                return false;
            } finally {
                closeQuietly(zis, bos);

            }
        } else {
            System.out.println("----===============--------------");

            return false;
        }
    }

    /**
     * 关闭一个或多个流对象
     * 
     * @param closeables
     *            可关闭的流对象列表
     * @throws IOException
     */
    public static void close(Closeable... closeables) throws IOException {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    closeable.close();
                }
            }
        }
    }

    /**
     * 关闭一个或多个流对象
     * 
     * @param closeables
     *            可关闭的流对象列表
     */
    public static void closeQuietly(Closeable... closeables) {
        try {
            close(closeables);
        } catch (IOException e) {
            // do nothing
        }
    }

    public static boolean packZip(List<String> paths, String zipPath, HttpServletRequest request) throws Exception {

        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        fos = new FileOutputStream(zipPath);
        zos = new ZipOutputStream(fos);

        for (int i = 0; i < paths.size(); i++) {
            File file = new File(request.getSession().getServletContext().getRealPath("/") + paths.get(i));
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                DataInputStream dis = new DataInputStream(new BufferedInputStream(fis));

                ZipEntry ze = new ZipEntry(file.getName());
                zos.putNextEntry(ze);
                byte[] content = new byte[1024];
                int len;
                while ((len = fis.read(content)) != -1) {
                    zos.write(content, 0, len);
                    zos.flush();
                }

                if (dis != null) {
                    dis.close();
                }
            }
        }

        if (zos != null) {
            zos.close();
        }

        return true;
    }

    public static boolean createZip(String sourcePath, String zipPath) {
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipPath);
            zos = new ZipOutputStream(fos);
            writeZip(new File(sourcePath), "", zos);
            if (zos != null) {
                zos.close();
            }
            return true;
        } catch (Exception e) {
            System.out.println("创建ZIP文件失败" + e.getMessage());
            return false;
        }
    }

    public static void writeZip(File file, String parentPath, ZipOutputStream zos) {
        if (file.exists()) {
            if (file.isDirectory()) {// 处理文件夹
                parentPath += file.getName() + File.separator;
                File[] files = file.listFiles();
                for (File f : files) {
                    writeZip(f, parentPath, zos);
                }
            } else {
                FileInputStream fis = null;
                DataInputStream dis = null;
                try {
                    fis = new FileInputStream(file);
                    dis = new DataInputStream(new BufferedInputStream(fis));
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte[] content = new byte[1024];
                    int len;
                    while ((len = fis.read(content)) != -1) {
                        zos.write(content, 0, len);
                        zos.flush();
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("创建ZIP文件失败" + e.getMessage());
                } catch (IOException e) {
                    System.out.println("创建ZIP文件失败" + e.getMessage());
                } finally {
                    try {
                        if (dis != null) {
                            dis.close();
                        }
                    } catch (IOException e) {
                        System.out.println("创建ZIP文件失败" + e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * 把byte[]写入文件
     * 
     * @param bytes
     * @param path
     * @param fileName
     * @return
     */
    public static boolean WriteBytesToFile(byte[] bytes, String path, String fileName) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileoutputstream = new FileOutputStream(path + "/" + fileName);// 建立文件输出流
            fileoutputstream.write(bytes);
            fileoutputstream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 文件转bytes
     * 
     * @param file
     * @return
     * @throws Exception
     */
    public static byte[] getBytesFromFile(String filePath) throws Exception {

        FileInputStream fileinputstream = new FileInputStream(filePath);// 读取模板文件
        int lenght = fileinputstream.available();
        byte bytes[] = new byte[lenght];
        fileinputstream.read(bytes);
        fileinputstream.close();
        return bytes;
    }

    /**
     * 文件或目录是否存在
     */
    public static boolean exists(String path) {
        return new File(path).exists();
    }

    /**
     * 文件是否存在
     */
    public static boolean existsFile(String path) {
        File file = new File(path);
        return file.exists() && file.isFile();
    }

    /**
     * 列出所有文件
     * 
     * @param path
     * @return
     */
    public static File[] listFiles(String path) {
        File file = new File(path);
        return file.listFiles();
    }
}
