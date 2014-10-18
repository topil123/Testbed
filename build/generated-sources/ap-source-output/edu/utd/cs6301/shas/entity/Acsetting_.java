package edu.utd.cs6301.shas.entity;

import edu.utd.cs6301.shas.entity.Airconditioning;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-18T01:29:04")
@StaticMetamodel(Acsetting.class)
public class Acsetting_ { 

    public static volatile SingularAttribute<Acsetting, String> endtime;
    public static volatile SingularAttribute<Acsetting, String> starttime;
    public static volatile SingularAttribute<Acsetting, Airconditioning> acid;
    public static volatile SingularAttribute<Acsetting, Integer> dayofweek;
    public static volatile SingularAttribute<Acsetting, Integer> settingid;
    public static volatile SingularAttribute<Acsetting, Integer> temperature;

}