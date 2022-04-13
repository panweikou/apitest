package O2O.util;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * @Author: Sean Pan
 * @ClassName csvUtil
 * @Description TODO
 * @date 2021/11/28 22:21
 * @Version 1.0
 */
public class CsvUtil<T> {
    private static Logger logger = LoggerFactory.getLogger(CsvUtil.class);

    public List<T> csvToBean(String file, Class<T> clz) throws FileNotFoundException {
        URL resource = ClassLoader.getSystemResource(file);
        System.out.println("-----------------" + resource);
        CSVReader reader = new CSVReader(new FileReader(resource.getFile()));
        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(clz);
        CsvToBean<T> csvToBean = new CsvToBean<>();
        return csvToBean.parse(strategy, reader);
    }

    public List<T> csvToBean(File file, Class<T> clz) throws FileNotFoundException {
        CSVReader reader = new CSVReader(new FileReader(file));
        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(clz);
        CsvToBean<T> csvToBean = new CsvToBean<>();
        return csvToBean.parse(strategy, reader);
    }

    public void beanToCsv(String file, List<T> trades) {
        try {
            logger.info("persisting {} bean to {}", trades.size(), file);
            logger.info("" + trades);
            HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
            Class clz = trades.get(0).getClass();
            strategy.setType(clz);
            FileWriter writer = new FileWriter(new File(file)) ;
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withMappingStrategy(strategy)
                    .withSeparator(',')
                    .withThrowExceptions(true)
                    .build();
            beanToCsv.write(trades);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            logger.error("Failed to persist trades", e);
            throw new RuntimeException("Failed to persist trades", e);
        }
    }

}
