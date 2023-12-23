package org.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Order {


    //set of products the customer buys at once

        private Customer customer;

        private List<Product> products;
        String date2;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar date1;


        public Order() {
            super();
            customer = new Customer();
            products =new ArrayList<>();
            date1 = Calendar.getInstance();
            date1.add(Calendar.DAY_OF_MONTH, 7);
            date2=  sdf.format(date1.getTime());
        }



        public Order(Customer customer, List<Product> invoice, String date2, String status) {
            super();
            this.customer = customer;
            this.products =  invoice;
            this.date2=  date2;
            this.status = status;

        }

        public String getDate2() {
            return date2;
        }

        private String status;

        public Customer getCustomer() {
            return customer;
        }



        public List<Product> getProducts() {
            return products;
        }


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


        public void makeOrder(Customer customer, List<Product> getcard, String date2){
            this.customer = customer;
            this.date2=  date2;
            //this.status = status;

            Collections.copy( this.products, getcard);
        }




}
