package com.example.androidgroupprojectf22;

import java.sql.Time;
import java.util.List;

public class WeatherApiObj implements java.io.Serializable {

    class location_dtype {
        public String name;
        public String region;
        public String Country;
        public double lat;
        public double lon;
        public String tz_id;
        public long localtime_epoch;
        public String localtime;
    }

    class current_dtype {
        public Long last_updated_epoch;
        public String last_updated;
        public double temp_c;
        public double temp_f;
        public int is_day;

        class condition_dtype {
            public String text;
            public String icon;
            public int code;
        }

        // fill in rest of columns
        // add another data type like i did with condition
        public condition_dtype condition;
        public double wind_mph;
        public double wind_kph;
        public int wind_degree;
        public String wind_dir;
        public int pressure_mb;
        public double pressure_in;
        public int precip_mm;
        public int precip_in;
        public int humidity;
        public int cloud;
        public double feels_like_c;
        public double feels_like_f;
        public double vis_km;
        public double vis_miles;
        public int uv;
        public double gust_mph;
        public double gust_kph;
    }

    class forecast_dtype {
        class forecastday_dtype{
            public String date;
            public Long date_epoch;
            class day_dtype{
                public double maxtemp_c;
                public double maxtemp_f;
                public double mintemp_c;
                public double mintemp_f;
                public double avgtemp_c;
                public double avgtemp_f;
                public double maxwind_mph;
                public double maxwind_kph;
                public double totalprecip_mm;
                public double totalprecip_in;
                public double avgvis_km;
                public double avgvis_miles;
                public int daily_will_it_rain;
                public int daily_chance_of_rain;
                public int daily_will_it_snow;
                public int daily_chance_of_snow;
                class condition_dtype{
                    public String text;
                    public String icon;
                    public int code;
                }
                public condition_dtype condition;
                public int uv;

            }
            public day_dtype day;
            class astro_dtype{
                public String sunrise;
                public String  sunset;
                public String moonrise;
                public String moonset;
                public String moon_phase;
                public String moon_illumination;
            }
            public astro_dtype astro;
            class hour_dtype {
                public long time_epoch;
                public String time;
                public double temp_c;
                public double temp_f;
                public int is_day;

                class condition_dtype {
                    public String text;
                    public String icon;
                    public int code;
                }
                public condition_dtype condition;
                public double wind_mph;
                public double wind_kph;
                public int wind_degree;
                public String wind_dir;
                public int pressure_mb;
                public double pressure_in;
                public double precip_mm;
                public double precip_in;
                public int humidity;
                public int cloud;
                public double feels_like_c;
                public double feels_like_f;
                public double windchill_c;
                public double windchill_f;
                public double heatindex_c;
                public double heatindex_f;
                public double dewpoint_c;
                public double dewpoint_f;
                public int will_it_rain;
                public int chance_of_rain;
                public int will_it_snow;
                public int chance_of_snow;
                public double vis_km;
                public double vis_miles;
                public double gust_mph;
                public double gust_kph;
                public int uv;
            }
            List<hour_dtype> hour;
        }
        public List<forecastday_dtype> forecastday;
    }

    public location_dtype location;
    public current_dtype current;
    public forecast_dtype forecast;
}