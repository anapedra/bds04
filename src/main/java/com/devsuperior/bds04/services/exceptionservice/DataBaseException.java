package com.devsuperior.bds04.services.exceptionservice;


    public class DataBaseException extends RuntimeException{
        private static final long serialVersionUID=1L;
        public DataBaseException(String msg){
            super(msg);
        }
    }


