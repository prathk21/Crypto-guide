package com.example.cryptoguide;

public class Crypto
{
    private String name;
    private String sign;
    private Double price;



    public Crypto(String name, String sign , Double price)

    {
      this.name = name;
      this.sign = sign;
      this.price = price;
    }

    public void setName(String name) { this.name = name; }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getSign() {
        return sign;
    }

    public Double getPrice() {
        return price;
    }
}
