package com.example.listener;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * @Description TODO
 * @Author 郝明鉴
 * Data 2020/8/2 9:45 上午
 * Version 1.0
 **/

public class MyMergeStrategy extends AbstractMergeStrategy {
//    合并坐标集合
    private List<CellRangeAddress> cellRangeAddresses;

    public MyMergeStrategy() {
        super();
    }

    public MyMergeStrategy(List<CellRangeAddress> cellRangeAddresses) {
        this.cellRangeAddresses = cellRangeAddresses;
    }


    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
        super.beforeCellCreate(writeSheetHolder, writeTableHolder, row, head, columnIndex, relativeRowIndex, isHead);
    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        super.afterCellCreate(writeSheetHolder, writeTableHolder, cell, head, relativeRowIndex, isHead);
    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        super.afterCellDispose(writeSheetHolder, writeTableHolder, cellDataList, cell, head, relativeRowIndex, isHead);
    }

    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, Integer integer) {
        if (CollectionUtils.isNotEmpty(cellRangeAddresses)) {
            if (cell.getRowIndex() == 1 && cell.getColumnIndex() == 0) {
                for (CellRangeAddress item : cellRangeAddresses) {
                    sheet.addMergedRegionUnsafe(item);
                }
            }
        }
    }
}
