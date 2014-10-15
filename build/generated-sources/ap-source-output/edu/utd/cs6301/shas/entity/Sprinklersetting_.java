package edu.utd.cs6301.shas.entity;

import edu.utd.cs6301.shas.entity.Sprinkler;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-15T01:08:56")
@StaticMetamodel(Sprinklersetting.class)
public class Sprinklersetting_ { 

    public static volatile SingularAttribute<Sprinklersetting, String> endtime;
    public static volatile SingularAttribute<Sprinklersetting, String> starttime;
    public static volatile SingularAttribute<Sprinklersetting, Sprinkler> sprinklerid;
    public static volatile SingularAttribute<Sprinklersetting, BigInteger> dayofweek;
    public static volatile SingularAttribute<Sprinklersetting, BigDecimal> settingid;

}