package org.example;

public class Request {

    String carModel;
    String preferredDate;
String location;
Installer temp;
    Product product;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void informInstaller(Installer i) {
        this.temp = i;
    }


    private String status;




/*
    public static void showTable() {
        logger.info("Product ID\tCar Model\tLocation\tService\tPrice");
        for (House house : HouseList.getHouseList()) {
            if (!house.isRejected()) {
                logger.info(house.getHouseId() + "\t\t" +
                        house.getLocation() + "\t\t" +
                        house.getHouseName() + "\t\t" +
                        house.isTypeStudnt() + "\t\t" +
                        house.getService() + "\t\t" +
                        house.getPrice() + "\t\t" +
                        house.getPhoto()
                );
            }
        }
        logger.info("");
    }
*/














/*
    public static void showTable() {
        logger.info("Product ID\tCar Model\tLocation\tService\tPrice");
        for (House house : HouseList.getHouseList()) {
            if (!house.isRejected()) {
                logger.info(house.getHouseId() + "\t\t" +
                        house.getLocation() + "\t\t" +
                        house.getHouseName() + "\t\t" +
                        house.isTypeStudnt() + "\t\t" +
                        house.getService() + "\t\t" +
                        house.getPrice() + "\t\t" +
                        house.getPhoto()
                );
            }
        }
        logger.info("");
    }
*/












}
