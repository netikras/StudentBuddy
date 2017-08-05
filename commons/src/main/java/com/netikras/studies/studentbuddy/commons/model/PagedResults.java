package com.netikras.studies.studentbuddy.commons.model;

import java.util.List;

public class PagedResults<T> {

        private long pageSize;
        private long pageNo;

        public PagedResults() {
        }

        public PagedResults(long pageSize, long pageNo) {
            this.pageSize = pageSize;
            this.pageNo = pageNo;
        }

        List<T> data;

        public long getPageSize() {
            return pageSize;
        }

        public void setPageSize(long pageSize) {
            this.pageSize = pageSize;
        }

        public long getPageNo() {
            return pageNo;
        }

        public void setPageNo(long pageNo) {
            this.pageNo = pageNo;
        }

        public List<T> getData() {
            return data;
        }

        public void setData(List<T> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "PagedResults{" +
                    "pageSize=" + pageSize +
                    ", pageNo=" + pageNo +
                    ", data=" + data +
                    '}';
        }
    }