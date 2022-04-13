package O2O.util;

import io.qameta.allure.ReportGenerator;
import io.qameta.allure.core.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: Sean Pan
 * @ClassName AllureReport
 * @Description TODO
 * @date 2021/11/28 20:56
 * @Version 1.0
 */
public class AllureReport {
    private static final Logger logger = LoggerFactory.getLogger(AllureReport.class);

    public static void clearTestReport(int capacity) {
        File dirFile = new File("TestReport");
        if (dirFile.exists()) {
            File[] fileList = new File("TestReport").listFiles();
            if (fileList != null && fileList.length > capacity) {
                logger.info("Begin clearing TestReport...");
                for(int i = 0; i < capacity -1 ; i++) {
                    deleteFolderOrFile(fileList[i].getAbsolutePath());
                }
                logger.info("Clearing TestReport is done.");
            }
        }
    }

    public static boolean deleteFolderOrFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile()) {
                return deleteFile(file, path);
            } else {
                return deleteDirectory(path);
            }
        }
    }

    public static boolean deleteFile(File file, String path) {
        Boolean flag = false;
        file = new File(path);
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    private static boolean deleteDirectory(String path) {
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        File dirFile = new File(path);
        if(!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for(File file : files) {
            if (file.isFile()) {
                flag = deleteFile(file, file.getAbsolutePath());
                logger.debug("Delete file : " + flag);
            } else {
                flag = deleteDirectory(file.getAbsolutePath());
                logger.debug("Delete folder: " + flag);
            }
            if (!flag) {
                break;
            }
        }
        if (!flag) {
            return false;
        }
        return dirFile.delete();
    }

    public static void generateAllureReport() throws IOException {
        Thread.currentThread().setName("AllureReport");
        Configuration configuration = new AllureConfig().useDefault().build();
        ReportGenerator reportGenerator = new ReportGenerator(configuration);
        Path outPut = Paths.get("target/allure-report");
        Path from = Paths.get("target/allure-results");
        reportGenerator.generate(outPut, from);
        logger.info("Report generate to " + Paths.get(outPut.toString(), "index.html").toAbsolutePath());
    }


}
