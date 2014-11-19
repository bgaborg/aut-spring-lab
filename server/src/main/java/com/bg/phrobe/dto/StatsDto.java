package com.bg.phrobe.dto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatsDto{
    public List<Nv3Displayable> stats = new ArrayList<>();

    public static class Nv3Displayable<T>{
        public String key;
        public List<T> values = new ArrayList<>();
        public List<Date> dates = new ArrayList<>();
    }
}
