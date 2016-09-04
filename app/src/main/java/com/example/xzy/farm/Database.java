package com.example.xzy.farm;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xzy on 2016/8/19.
 */
public class Database implements Serializable{
    private static final long serialVersionUID = 1L;
}

class User extends Database implements Serializable{
    private static final long serialVersionUID = 1L;
    String id_;
    String password;
    String mobile;

    public User(ResultSet resultSet) {
        try {
            this.id_ = resultSet.getString("id");
            this.password = resultSet.getString("password");
            this.mobile = resultSet.getString("mobile");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getId_() {
        return id_;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }
}

class Farm extends Database implements Serializable{
    private static final long serialVersionUID = 1L;
    String farmID;
    String location;
    String user_ID;
    String category_breed;
    Integer temperature_min;
    Integer temperature_max;
    Float humidity_min;
    Float humidity_max;
    Float wind_min;
    Float wind_max;
    Float light_min;
    Float light_max;

    public Farm(ResultSet resultSet) {
        try {
            this.farmID = resultSet.getString("farmID");
            this.location = resultSet.getString("location");
            this.user_ID = resultSet.getString("user_ID");
            this.category_breed = resultSet.getString("category_breed");
            this.temperature_min = resultSet.getInt("temperature_min");
            this.temperature_max = resultSet.getInt("temperature_max");
            this.humidity_min = resultSet.getFloat("humidity_min");
            this.humidity_max = resultSet.getFloat("humidity_max");
            this.wind_min = resultSet.getFloat("wind_min");
            this.wind_max = resultSet.getFloat("wind_max");
            this.light_min = resultSet.getFloat("light_min");
            this.light_max = resultSet.getFloat("light_max");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getFarmID() {
        return farmID;
    }

    public String getLocation() {
        return location;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public String getCategory_breed() {
        return category_breed;
    }

    public Integer getTemperature_min() {
        return temperature_min;
    }

    public Integer getTemperature_max() {
        return temperature_max;
    }

    public Float getHumidity_min() {
        return humidity_min;
    }

    public Float getHumidity_max() {
        return humidity_max;
    }

    public Float getWind_min() {
        return wind_min;
    }

    public Float getWind_max() {
        return wind_max;
    }

    public Float getLight_min() {
        return light_min;
    }

    public Float getLight_max() {
        return light_max;
    }
}

class Category extends Database implements Serializable{
    private static final long serialVersionUID = 1L;
    String breed;
    Integer suitable_temperature_min;
    Integer suitable_temperature_max;
    Float suitable_humidity_min;
    Float suitable_humidity_max;
    Float suitable_wind_min;
    Float suitable_wind_max;
    Float suitable_light_min;
    Float suitable_light_max;
    Float suitable_body_temperature_min;
    Float suitable_body_temperature_max;

    public Category(ResultSet resultSet) {
        try {
            this.breed = resultSet.getString("breed");
            this.suitable_temperature_min = resultSet.getInt("suitable_temperature_min");
            this.suitable_temperature_max = resultSet.getInt("suitable_temperature_max");
            this.suitable_humidity_min = resultSet.getFloat("suitable_humidity_min");
            this.suitable_humidity_max = resultSet.getFloat("suitable_humidity_max");
            this.suitable_wind_min = resultSet.getFloat("suitable_wind_min");
            this.suitable_wind_max = resultSet.getFloat("suitable_wind_max");
            this.suitable_light_min = resultSet.getFloat("suitable_light_min");
            this.suitable_light_max = resultSet.getFloat("suitable_light_max");
            this.suitable_body_temperature_min = resultSet.getFloat("suitable_body_temperature_min");
            this.suitable_body_temperature_max = resultSet.getFloat("suitable_body_temperature_max");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getBreed() {
        return breed;
    }

    public Integer getSuitable_temperature_min() {
        return suitable_temperature_min;
    }

    public Integer getSuitable_temperature_max() {
        return suitable_temperature_max;
    }

    public Float getSuitable_humidity_min() {
        return suitable_humidity_min;
    }

    public Float getSuitable_humidity_max() {
        return suitable_humidity_max;
    }

    public Float getSuitable_wind_min() {
        return suitable_wind_min;
    }

    public Float getSuitable_wind_max() {
        return suitable_wind_max;
    }

    public Float getSuitable_light_min() {
        return suitable_light_min;
    }

    public Float getSuitable_light_max() {
        return suitable_light_max;
    }

    public Float getSuitable_body_temperature_min() {
        return suitable_body_temperature_min;
    }

    public Float getSuitable_body_temperature_max() {
        return suitable_body_temperature_max;
    }
}

class Daily extends Database implements Serializable{
    private static final long serialVersionUID = 1L;
    String type;
    Date date;
    String details;
    String farm_farmID;
    Integer completion;

    public Daily(ResultSet resultSet) {
        try {
            this.type = resultSet.getString("type");
            this.date = resultSet.getDate("date");
            this.details = resultSet.getString("details");
            this.farm_farmID = resultSet.getString("farm_farmID");
            this.completion = resultSet.getInt("completion");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public String getFarm_farmID() {
        return farm_farmID;
    }

    public Integer getCompletion() {
        return completion;
    }
}

class Help extends Database implements Serializable{
    private static final long serialVersionUID = 1L;
    String content_;
    String category_breed;

    public Help(ResultSet resultSet) {
        try {
            this.content_ = resultSet.getString("content");
            this.category_breed = resultSet.getString("category_breed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getContent_() {
        return content_;
    }

    public String getCategory_breed() {
        return category_breed;
    }
}

class Individual extends Database implements Serializable{
    private static final long serialVersionUID = 1L;
    String individualID;
    Integer body_temperature;
    String category_breed;
    String farm_farmID;

    public Individual(ResultSet resultSet) {
        try {
            this.individualID = resultSet.getString("individualID");
            this.body_temperature = resultSet.getInt("bodytemperature");
            this.category_breed = resultSet.getString("category_breed");
            this.farm_farmID = resultSet.getString("farm_farmID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getIndividualID() {
        return individualID;
    }

    public Integer getBody_temperature() {
        return body_temperature;
    }

    public String getCategory_breed() {
        return category_breed;
    }

    public String getFarm_farmID() {
        return farm_farmID;
    }
}