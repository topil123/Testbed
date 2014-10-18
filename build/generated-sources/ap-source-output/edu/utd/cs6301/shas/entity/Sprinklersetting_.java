package edu.utd.cs6301.shas.entity;

import edu.utd.cs6301.shas.entity.Sprinkler;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-18T01:29:04")
@StaticMetamodel(Sprinklersetting.class)
public class Sprinklersetting_ { 

    public static volatile SingularAttribute<Sprinklersetting, String> endtime;
    public static volatile SingularAttribute<Sprinklersetting, String> starttime;
    public static volatile SingularAttribute<Sprinklersetting, Sprinkler> sprinklerid;
    public static volatile SingularAttribute<Sprinklersetting, Integer> dayofweek;
    public static volatile SingularAttribute<Sprinklersetting, Integer> settingid;

}