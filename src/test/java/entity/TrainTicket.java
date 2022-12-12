package entity;

public class TrainTicket {

    private String checi;
    private String price;
    private String time;
    private String wangfan;

    public String getWangfan() {
        return wangfan;
    }

    public void setWangfan(String wangfan) {
        this.wangfan = wangfan;
    }



    public TrainTicket(String checi, String price, String time,String wangfan) {
        this.checi = checi;
        this.price = price;
        this.time = time;
        this.wangfan = wangfan;
    }

    public String getCheci() {
        return checi;
    }

    public void setCheci(String checi) {
        this.checi = checi;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toString(){
        return "路程类型："+getWangfan()+ " 车次："+getCheci()+" 时间："+getTime()+" 价格："+ getPrice();
    }

}
