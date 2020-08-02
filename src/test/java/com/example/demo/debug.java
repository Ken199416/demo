package com.example.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.example.data.ExcelData;
import com.example.data.SingleData;
import com.example.listener.DemoListener;
import com.example.listener.MyCellWriteHandler;
import com.example.listener.MyMergeStrategy;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.omg.CORBA.OBJ_ADAPTER;
import org.omg.CORBA.UNKNOWN;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import sun.jvm.hotspot.runtime.Threads;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class debug {

    private Logger logger = LoggerFactory.getLogger(debug.class);

    @Test
    public void test111() throws IOException, InterruptedException {
        String path = System.getProperty("user.dir");
        String driverPath = path + "/driver/chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get("https://tmdsbjclyh.login.aliyunidaas.com/frontend/login#/login");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        WebElement codeImage = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div[1]/div[2]/div[3]/form/div[3]/div/div/div/span/img"));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(path + "/111.png"));
        // Get entire page screenshot
        BufferedImage fullImg = ImageIO.read(screenshot);
        // Get the location of element on the page
        org.openqa.selenium.Point point = codeImage.getLocation();
        // Get width and height of the element
        int eleWidth = codeImage.getSize().getWidth();
        int eleHeight = codeImage.getSize().getHeight();
        // Crop the entire page screenshot to get only element screenshot
        System.out.println("图片开始位置：" + point.getX() + " : " + point.getY());
//        BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth * 20, eleHeight * 20);
        BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth * 20, eleHeight * 20);

        ImageIO.write(eleScreenshot, "png", screenshot);
        // Copy the element screenshot to disk
        File screenshotLocation = new File(path + "/verifyCode/test.png");
        FileUtils.copyFile(screenshot, screenshotLocation);
//        WebElement username = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div[1]/div[2]/div[3]/form/div[1]/div/div[1]/input"));
//        username.sendKeys("13072468072");
//        WebElement password = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div[1]/div[2]/div[3]/form/div[2]/div/div[1]/input"));
//        password.sendKeys("Quanwai123456");
//        WebElement verifyCode = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div[1]/div[2]/div[3]/form/div[3]/div/div/input"));
//        verifyCode.sendKeys("test");
    }


    @Test
    public void t222() throws InterruptedException {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "test1");
        map.put("2", "test1");
        map.put("3", "test3");
        map.put("4", "test4");
        map.put("5", "test5");
        map.put("6", "test6");
        map.put("7", "test7");
        map.put("8", "test8");
        map.put("9", "test9");
        map.put("10", "test10");
        long start1 = System.currentTimeMillis();
        Thread.sleep(500);
        System.out.println("map中的 '" + getMapHaveTwo(map) + "' 重复");
        long end1 = System.currentTimeMillis();
        map.put("1", "test1");
        map.put("2", "test1");
        map.put("3", "test3");
        map.put("4", "test4");
        map.put("5", "test5");
        map.put("6", "test6");
        map.put("7", "test7");
        map.put("8", "test8");
        map.put("9", "test9");
        map.put("10", "test10");
        long start2 = System.currentTimeMillis();
        Thread.sleep(500);
        System.out.println("map中的 '" + getMapHaveTwo2(map) + "' 重复");
        long end2 = System.currentTimeMillis();
        System.out.println("第一种执行消耗：" + (end1 - start1));
        System.out.println("第二种执行消耗：" + (end2 - start2));
    }


    public String getMapHaveTwo(HashMap<String, String> map) {
        String value = null;
        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            value = map.get(it.next());
            it.remove();
            if (map.containsValue(value)) {
                return value;
            }
        }
        return null;
    }


    public String getMapHaveTwo2(HashMap<String, String> map) {
        List<String> sameList = new ArrayList<>();
        for (String key : map.keySet()) {
            if (sameList.contains(map.get(key))) {
                return map.get(key);
            } else {
                sameList.add(map.get(key));
            }
        }
        return "没有";
    }


    @Test
    public void whyException() {

        HashMap<Integer, String> map = new HashMap<>();
        String s = "";
        map.put(1, "1");
        map.put(2, "8");
        map.put(3, "7");
        map.put(4, "6");
        map.put(5, "hehe");
        map.put(6, "4");
        map.put(7, "3");
        map.put(8, "hehe");
        map.put(9, "2");
        map.put(10, "10");
        Set<Integer> integers = map.keySet();
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            Integer currentIterator = iterator.next();
            String remove = map.remove(currentIterator);
            if (map.containsValue(remove)) {
                s = remove + "出现了两次";
                break;
            }
        }
        s = s.equals("") ? "null" : s;
        System.out.println(s);
    }


//    /Users/edz/IdeaProjects/demo/driver


    @Test
    public void test111222() throws Exception {
        String name = null;

        String fileName = "/Users/edz/IdeaProjects/demo/driver/职工缴费.xls";
        List<Object> list = EasyExcelFactory.read(new FileInputStream(new File(fileName)), new Sheet(1, 0));
//        List<List<String>> list = EasyExcel.read(fileName,)
//        用来计数和统计的
        HashMap<String, List<List<Object>>> excelMap = new HashMap<>();
        List<List<Object>> historyDataPre = new ArrayList<>();
        List<List<Object>> historyDataEnd = new ArrayList<>();
        List<List<Object>> rowDataByName = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i < 6) {
                List<Object> rowData = (List<Object>) list.get(i);
                historyDataPre.add(rowData);
            } else {
                List<Object> rowData = (List<Object>) list.get(i);
                if (rowData.size() != 8) {
                    historyDataEnd.add(rowData);
                    break;
                }
                name = rowData.get(7).toString();
//                    如果excel中有这个姓名的数据了
                if (excelMap.containsKey(name)) {
                    rowDataByName = excelMap.get(name);
                } else {
//                        如果没有这个名字的数据
                    rowDataByName = new ArrayList<>();
                }
                rowDataByName.add(rowData);
                excelMap.put(name, rowDataByName);
            }

        }
//        excelMap.forEach((key,value) -> {
//            System.out.println( key + ":");
//            value.forEach(rowByName ->{
//                rowByName.forEach(row -> {
//                    System.out.print(row + ",");
//                });
//            });
//            System.out.println();
//        });

        for (String flagName : excelMap.keySet()) {
//            FileOutputStream fileOutputStream = new FileOutputStream("/Users/edz/IdeaProjects/demo/driver/out/职工缴费_" + flagName + ".xls");
            String outFileName = "/Users/edz/IdeaProjects/demo/driver/out/职工缴费_" + flagName + ".xls";
            List<List<Object>> excelData = new ArrayList<>();
            excelData.addAll(historyDataPre);
            excelData.addAll(excelMap.get(flagName));
            excelData.addAll(historyDataEnd);
            EasyExcel.write(outFileName).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet(flagName).doWrite(excelData);
        }


    }


    @Test
    public void test3344() throws Exception {
        String fileName = "/Users/edz/IdeaProjects/demo/driver/out/demo.xls";
//        FileOutputStream out = new FileOutputStream(fileName);
//        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLS,false);
//        Sheet sheet = new Sheet(1,0);
        List<List<String>> dataList = new ArrayList<>();
        List<String> data1 = new ArrayList<>();
        data1.add("111为什么没写进去");
        data1.add("222为什么没写进去");
        List<String> data2 = new ArrayList<>();
        data2.add("333为什么没写进去");
        data2.add("444为什么没写进去");
        List<String> data3 = new ArrayList<>();
        data3.add("555为什么没写进去");
        data3.add("666为什么没写进去");
        dataList.add(data1);
        dataList.add(data2);
        dataList.add(data3);


        CellRangeAddress cellRangeAddress = new CellRangeAddress(1,2,0,1);
        List<CellRangeAddress> cellRangeAddresses = new ArrayList<>();
        cellRangeAddresses.add(cellRangeAddress);
        MyMergeStrategy myMergeStrategy = new MyMergeStrategy(cellRangeAddresses);
//        writer.write(data,sheet);
//        writer.finish();
//        out.close();
        EasyExcel.write(fileName).sheet("test").registerWriteHandler(myMergeStrategy).doWrite(dataList);
    }

    public List<CellRangeAddress> getMergeCellRangeAddresses(int rowCount){
//        头部
        CellRangeAddress cellRangeAddress1 = new CellRangeAddress(0,0,0,1);
        CellRangeAddress cellRangeAddress2 = new CellRangeAddress(1,1,0,1);
        CellRangeAddress cellRangeAddress3 = new CellRangeAddress(2,2,0,7);
        CellRangeAddress cellRangeAddress4 = new CellRangeAddress(5,5,1,3);

//        尾部
//        CellRangeAddress cellRangeAddress5 = new CellRangeAddress(rowCount-12,rowCount-12,1,4);
//        CellRangeAddress cellRangeAddress6 = new CellRangeAddress(rowCount-9,rowCount-9,0,6);
//        CellRangeAddress cellRangeAddress7 = new CellRangeAddress(rowCount-8,rowCount-8,0,7);
//        CellRangeAddress cellRangeAddress8 = new CellRangeAddress(rowCount-7,rowCount-7,0,7);
//        CellRangeAddress cellRangeAddress9 = new CellRangeAddress(rowCount-5,rowCount-5,0,7);
//        CellRangeAddress cellRangeAddress10 = new CellRangeAddress(rowCount-4,rowCount-4,0,7);
//        CellRangeAddress cellRangeAddress11 = new CellRangeAddress(rowCount-3,rowCount-3,0,7);
//        CellRangeAddress cellRangeAddress12 = new CellRangeAddress(rowCount-2,rowCount-2,0,7);
//        CellRangeAddress cellRangeAddress13 = new CellRangeAddress(rowCount-1,rowCount-1,0,10);
        List<CellRangeAddress> cellRangeAddresses = new ArrayList<>();
        cellRangeAddresses.add(cellRangeAddress1);
        cellRangeAddresses.add(cellRangeAddress2);
        cellRangeAddresses.add(cellRangeAddress3);
        cellRangeAddresses.add(cellRangeAddress4);
//        cellRangeAddresses.add(cellRangeAddress5);
//        cellRangeAddresses.add(cellRangeAddress6);
//        cellRangeAddresses.add(cellRangeAddress7);
//        cellRangeAddresses.add(cellRangeAddress8);
//        cellRangeAddresses.add(cellRangeAddress9);
//        cellRangeAddresses.add(cellRangeAddress10);
//        cellRangeAddresses.add(cellRangeAddress11);
//        cellRangeAddresses.add(cellRangeAddress12);
//        cellRangeAddresses.add(cellRangeAddress13);



        return cellRangeAddresses;
    }


    public Map<Integer,List<Integer>> getStyleCellAddress(){
        Map<Integer,List<Integer>> map = new HashMap<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(2);
        row1.add(4);
        row1.add(6);
        row1.add(7);
        map.put(0,row1);

        List<Integer> row2 = new ArrayList<>();
        row2.add(2);
        row2.add(4);
        row2.add(5);
        row2.add(6);
        map.put(1,row2);

        List<Integer> row5 = new ArrayList<>();
        row5.add(7);
        map.put(4,row5);



        return map;
    }

    @Test
    public void testToExcel() {
//        设置单元格的背景颜色
        MyCellWriteHandler myCellWriteHandler = new MyCellWriteHandler(getStyleCellAddress());
        String fileName = "/Users/edz/IdeaProjects/demo/driver/职工缴费.xls";
//        读初始文件
        EasyExcel.read(fileName, ExcelData.class, new DemoListener()).sheet().headRowNumber(0).doRead();
//        获取原始文件中的List
        List<ExcelData> excelDataList = SingleData.getExcelData();
//        遍历，然后封装模板头，模板尾，并把中间的数据存储到excelMap中，但是对合计的地方要单独处理
        excelDataList.forEach(excelData -> {
            Map<String,List<ExcelData>> excelMap = SingleData.getExcelMap();
//            所属机构
            String organization = excelData.getOrganization();
//            如果已经有个这个机构的数据,就去替换他
            if(SingleData.getExcelMap().containsKey(organization)){
                excelMap.get(organization).add(excelData);
                SingleData.setExcelMap(organization,excelMap.get(organization));
            }else {
//                如果没有，就新增，也是一样
                List<ExcelData> organizationData = new ArrayList<>();
                organizationData.add(excelData);
                SingleData.setExcelMap(organization,organizationData);
            }
        });
        SingleData.getExcelMap().forEach((key,value) -> {
//            封装合计
            ExcelData totalData = new ExcelData();
            totalData.setNo("合计");
            totalData.setName("（小写）：");
            Double companyPrice = 0.0;
            Double personPrice = 0.0;
            Double amountTotal = 0.0;
            DecimalFormat   df   =   new DecimalFormat("#####0.00");
            for (ExcelData excelData : value) {
                companyPrice += Double.parseDouble(excelData.getCompanyPrice());
                personPrice += Double.parseDouble(excelData.getPersonPrice());
                amountTotal += Double.parseDouble(excelData.getAmountTotal());
//                处理其他数据
                excelData.setCompanyPrice(df.format(Double.parseDouble(excelData.getCompanyPrice())));
                excelData.setPersonPrice(df.format(Double.parseDouble(excelData.getPersonPrice())));
                excelData.setAmountTotal(df.format(Double.parseDouble(excelData.getAmountTotal())));
            }
            totalData.setCompanyPrice(df.format(companyPrice));
            totalData.setPersonPrice(df.format(personPrice));
            totalData.setAmountTotal(df.format(amountTotal));
            List<ExcelData> totalDataList = new ArrayList<>();
            totalDataList.add(totalData);
//          拼接数据
            List<ExcelData> excelData = new ArrayList<>();
            excelData.addAll(SingleData.getPreData());
            excelData.addAll(totalDataList);
            excelData.addAll(value);

            excelData.addAll(SingleData.getPostData());
            String outFileName = "/Users/edz/IdeaProjects/demo/driver/out/职工缴费_"+ key + ".xls";
            //        获取单元格合并策略
            MyMergeStrategy myMergeStrategy = new MyMergeStrategy(getMergeCellRangeAddresses(excelData.size()));
            EasyExcel.write(outFileName,ExcelData.class).sheet("data").needHead(false).
                    registerWriteHandler(myMergeStrategy).
                    doWrite(excelData);
        });
    }


}
